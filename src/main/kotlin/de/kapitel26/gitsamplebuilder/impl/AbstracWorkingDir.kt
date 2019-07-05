package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.CommandlineException
import kotlin.streams.toList

abstract class AbstracWorkingDir<T>(
        rootDir: java.io.File,
        log: LogBuilder,
        val loesungsCommands: MutableList<T.() -> Unit> = mutableListOf()
) : AbstracDir<T>(rootDir, log = log) {

    fun inDir(dirName: String, commands: Dir.() -> Unit) =
            java.io.File(rootDir, dirName)
                    .apply { if (!exists()) throw IllegalStateException("Dir $this is expected to exist!") }
                    .also { log.cd(dirName, currentDirname()) }
                    .run { Dir(this, log = log) }
                    .run {
                        commands()
                        log.cd("..", currentDirname())
                    }

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createFile(name: String, content: String? = null, commands: File.() -> Unit = {}) =
            File(java.io.File(rootDir, name).absoluteFile, log)
                    .apply { if (location.exists()) throw IllegalStateException("File $this is not expected to exist!") }
                    .apply { log.createFile(name, currentDirname()) }
                    .apply { location.writeText(content ?: createSampleFileContent()) }
                    .apply(commands)

    fun inFile(name: String = "file", commands: File.() -> Unit = {}) =
            File(java.io.File(rootDir, name), log)
                    .apply { if (!location.exists()) throw IllegalStateException("File $this is expected to exist!") }
                    .run(commands)


    fun execute(command: String): List<String> {
        val process = executeRaw(command, false)
        val outputLines = process.inputStream.bufferedReader().lines().toList()
        outputLines.forEach { log.addRawLine("    $it") }
        val errorLines = process.errorStream.bufferedReader().lines().toList()
        errorLines.forEach { log.addRawLine("    $it") }
        return outputLines
    }

    fun executeSplitted(vararg command: String): List<String> = executeWithLog(false, *command).inputStream.bufferedReader().lines().toList()

    fun show(command: String): Process = executeRaw(command, true)


    fun executeRaw(command: String, inheritStdout: Boolean): Process {
        val splittedCommandLineArguments = command.split("""\s+""".toRegex()).toTypedArray()
        return executeWithLog(inheritStdout, *splittedCommandLineArguments)
    }

    fun executeWithLog(inheritStdout: Boolean, vararg splittedCommandLineArguments: String): Process {
        log.shell(splittedCommandLineArguments.joinToString(" "), rootDir.name)
        return justExecute(inheritStdout, *splittedCommandLineArguments)
    }

    fun justExecute(inheritStdout: Boolean, vararg splittedCommandLineArguments: String): Process {

        val checkExitCode = { p: Process -> assertExitCode(p, setOf(0), splittedCommandLineArguments) }

        val errorRedirect = ProcessBuilder.Redirect.PIPE
        val stdoutRedirect = if (inheritStdout) ProcessBuilder.Redirect.INHERIT else ProcessBuilder.Redirect.PIPE

        val processBuilder = ProcessBuilder(*splittedCommandLineArguments)
        processBuilder.directory(rootDir)
        processBuilder.redirectOutput(stdoutRedirect)
        processBuilder.redirectError(errorRedirect)

        val process = processBuilder.start()
        process.waitFor()
        checkExitCode(process)
        return process
    }

    private fun assertExitCode(p: Process, expectedExits: Set<Int>, splittedCommandLineArguments: Array<out String>) {
        if (!(p.exitValue() in expectedExits))
            throw CommandlineException(p, "Failed with exit code ${p.exitValue()}: ${splittedCommandLineArguments.joinToString(" ")}")
    }


    fun git(gitCommand: String): List<String> = execute("git $gitCommand")

    fun git(vararg commandLineArguments: String): List<String> = executeSplitted(*(listOf("git") + commandLineArguments).toTypedArray())

    fun createRepo(newRepoName: String = "repo", vararg args: String, commands: (Repo.() -> Unit)? = null): Unit {
        git("init", newRepoName, *args)
        Repo(java.io.File(rootDir, newRepoName).absoluteFile, log, commands)
    }

    fun createClonedRepo(originalRepo: String, clonedRepo: String = "repo", commands: (Repo.() -> Unit)? = {}) {
        git("clone $originalRepo $clonedRepo")
        inRepo(clonedRepo, commands)
    }


    fun inRepo(repoName: String = "repo", commands: (Repo.() -> Unit)? = null): Unit =
            java.io.File(rootDir, repoName).absoluteFile
                    .apply { if (!exists()) throw IllegalStateException("Repo $this does not exist!") }
                    .run {
                        if (commands != null)
                            Repo(this, log, commands)
                    }


    fun inRepo(repo: Repo, function: Repo.() -> Unit) {
        Repo(repo.rootDir, log, function)
    }

    fun listFilenames(): List<String> = execute("ls -A")

    fun edit(filename: String, lineNumber: Int, message: String = "edited") =
            inFile(filename) { edit(lineNumber..lineNumber, message) }

    fun doc(name: String, commands: T.() -> Unit) {
        log.enableDoc(name)
        (this as T).commands()
        log.disableDoc(name)
    }

    fun writeDocs() {
        log.writeFiles(rootDir)
    }

    fun createAufgabe(title: String, description: String = "", commands: T.() -> Unit = {}) {
        loesungsCommands.add(commands)
        doc(markdownFilename(loesungsCommands.size)) {
            markdown("# Aufgabe ${loesungsCommands.size} - $title")
            markdown(description)
        }
    }

    fun commit(fileName: String, message: String = "Dummy commit message") {
        // TODO check if quoting of locations is necessary
        git("add", fileName)
        git("commit", "-m", message, fileName)

    }

    fun editAndCommit(fileName: String, line: Int, message: String = defaultMessage()) = editAndCommit(fileName, line..line, message)

    fun editAndCommit(fileName: String, lines: IntRange, message: String = defaultMessage()) {
        inFile(fileName) { edit(lines, message) }
        commit(fileName, "`$fileName`: $message")
    }

    fun createFileAndCommit(fileName: String, message: String = defaultMessage()) {
        createFile(fileName)
        commit(fileName, "`$fileName`: $message")
    }

    private fun defaultMessage(): String = "edited on `${currentBranch()}`"

    private fun markdownFilename(nr: Int) = "${formatNr(nr)}-aufgabe.md"

    private fun formatNr(nr: Int): String = "%02d".format(nr)

    // TODO does not work in new repo
    protected fun currentBranch(): String {
        val lines = justExecute(false, "git", "symbolic-ref", "--short", "HEAD").inputStream.bufferedReader().lines().toList()
        println("rev-parse ${lines}")
        if (lines.size == 1)
            return lines.first()
        else
            return "master"
    }

    fun applyLoesungen() =
            loesungsCommands.forEachIndexed { index, commands ->
                doc(markdownFilename(index + 1)) {
                    markdown("## Lösung")
                    commands()
                }
            }
}