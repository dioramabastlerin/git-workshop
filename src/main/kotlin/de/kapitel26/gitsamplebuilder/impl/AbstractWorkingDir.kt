package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.CommandLineException
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.lang.ProcessBuilder.Redirect.PIPE
import kotlin.streams.toList

abstract class AbstractWorkingDir<T>(
        rootDir: java.io.File,
        log: LogBuilder,
        solutionCollector: SolutionCollector
) : AbstractDir<T>(rootDir, log = log, solutionCollector = solutionCollector) {

    fun inDir(dirName: String, commands: Dir.() -> Unit) =
            java.io.File(rootDir, dirName)
                    .apply { if (!exists()) throw IllegalStateException("Dir $this is expected to exist!") }
                    .also { log.cd(dirName, currentDirname()) }
                    .run { Dir(this, log = log, solutionCollector = solutionCollector) }
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

    fun git(gitCommand: String, acceptableExitCodes: Set<Int> = setOf(0)) =
            bash("git $gitCommand", acceptableExitCodes)

    fun bash(command: String, acceptableExitCodes: Set<Int> = setOf(0)): List<String> {
        val process = executeProcess(
                "/bin/bash",
                "-c",
                command,
                stdoutRedirect = PIPE,
                errorRedirect = PIPE,
                validateOutcome = { p: Process -> assertExitCode(p, acceptableExitCodes, command) }
        )

        log.shell(command, rootDir.name)

        val outputLines = process.inputStream.bufferedReader().lines().toList()
        outputLines.forEach { log.addRawLine("    $it") }
        val errorLines = process.errorStream.bufferedReader().lines().toList()
        errorLines.forEach { log.addRawLine("    $it") }

        return outputLines
    }

    fun executeProcess(
            vararg arguments: String,
            stdoutRedirect: Redirect = PIPE,
            errorRedirect: Redirect = PIPE,
            validateOutcome: (Process) -> Unit = { p: Process -> assertExitCode(p, setOf(0), arguments) }
    ): Process {
        val processBuilder = ProcessBuilder(*arguments)
        processBuilder.directory(rootDir)
        processBuilder.redirectOutput(stdoutRedirect)
        processBuilder.redirectError(errorRedirect)

        val process = processBuilder.start()
        process.waitFor()
        validateOutcome(process)
        return process
    }

    fun assertExitCode(p: Process, expectedExits: Set<Int>, command: String) {
        if (!(p.exitValue() in expectedExits))
            throw CommandLineException(p, "Failed with exit code ${p.exitValue()}: $command")
    }

    fun assertExitCode(p: Process, expectedExits: Set<Int>, splittedCommandLineArguments: Array<out String>) {
        if (!(p.exitValue() in expectedExits))
            throw CommandLineException(p, "Failed with exit code ${p.exitValue()}: ${splittedCommandLineArguments.joinToString(" ")}")
    }


    fun git(vararg commandLineArguments: String): List<String> = git(commandLineArguments.joinToString(" "))

    fun createRepo(newRepoName: String = "repo", vararg args: String, commands: (Repo.() -> Unit)? = null) {
        git("init", newRepoName, *args)
        Repo(java.io.File(rootDir, newRepoName).absoluteFile, log, solutionCollector, commands)
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
                            Repo(this, log, solutionCollector, commands)
                    }


    fun inRepo(repo: Repo, function: Repo.() -> Unit) {
        Repo(repo.rootDir, log, solutionCollector, function)
    }

    fun listFilenames(): List<String> = bash("ls -A")

    fun edit(filename: String, lineNumber: Int, message: String = "edited") =
            inFile(filename) { edit(lineNumber..lineNumber, message) }

    @Suppress("UNCHECKED_CAST")
    fun doc(name: String, commands: T.() -> Unit) {
        log.enableDoc(name)
        (this as T).commands()
        log.disableDoc(name)
    }

    fun writeDocs() {
        log.writeFiles(rootDir)
    }

    @Suppress("UNCHECKED_CAST")
    fun createAufgabe(title: String, description: String = "", commands: T.() -> Unit = {}) {
        solutionCollector.collectedCommands.add({ (this as T).commands() })
        doc(markdownFilename(solutionCollector.collectedCommands.size)) {
            markdown("# Aufgabe ${solutionCollector.collectedCommands.size} - $title")
            markdown(description)
        }
    }

    fun commit(fileName: String, message: String = "Commited file $fileName on branch ${currentBranch()} by ${currentUser()}") {
        git("add $fileName")
        git("""commit $fileName -m "$message"""")
    }

    fun editAndCommit(fileName: String, line: Int, message: String = "Edited file $fileName at line $line on branch ${currentBranch()} by ${currentUser()}.") = editAndCommit(fileName, line..line, message)

    fun editAndCommit(fileName: String, lines: IntRange, message: String = "Edited file $fileName at lines $lines on branch ${currentBranch()} by ${currentUser()}.") {
        inFile(fileName) { edit(lines, message) }
        commit(fileName, "`$fileName`: $message")
    }

    fun createFileAndCommit(fileName: String, message: String = "Created file $fileName on branch ${currentBranch()} by ${currentUser()}.") {
        createFile(fileName)
        commit(fileName, "`$fileName`: $message")
    }

    private fun markdownFilename(nr: Int) = "${formatNr(nr)}-aufgabe.md"

    private fun formatNr(nr: Int): String = "%02d".format(nr)

    // TODO does not work in new repo
    protected fun currentBranch(): String {
        val lines = executeProcess(
                "git", "symbolic-ref", "--short", "HEAD"
        ).inputStream.bufferedReader().lines().toList()
        return lines.singleOrNull() ?: "MASTER"
    }

    protected fun currentUser() =
            executeProcess("git", "config", "user.name")
                    .inputStream
                    .bufferedReader()
                    .lines()
                    .toList()
                    .single()

    fun applyLoesungen() =
            solutionCollector.collectedCommands.forEachIndexed { index, command ->
                doc(markdownFilename(index + 1)) {
                    markdown("## Lösung")
                    command()
                }
            }
}

fun InputStream.readLines(): List<String> = bufferedReader().readLines()
