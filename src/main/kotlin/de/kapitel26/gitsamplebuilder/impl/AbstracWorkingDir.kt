package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.CommandLineException
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.lang.ProcessBuilder.Redirect.INHERIT
import java.lang.ProcessBuilder.Redirect.PIPE
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

    fun newGit(gitCommand: String, acceptableExitCodes: Set<Int> = setOf(0)) =
            bash("git $gitCommand")

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


    fun git(vararg commandLineArguments: String): List<String> = newGit(commandLineArguments.joinToString(" "))

    fun createRepo(newRepoName: String = "repo", vararg args: String, commands: (Repo.() -> Unit)? = null) {
        git("init", newRepoName, *args)
        Repo(java.io.File(rootDir, newRepoName).absoluteFile, log, commands)
    }

    fun createClonedRepo(originalRepo: String, clonedRepo: String = "repo", commands: (Repo.() -> Unit)? = {}) {
        newGit("clone $originalRepo $clonedRepo")
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

    fun createAufgabe(title: String, description: String = "", commands: T.() -> Unit = {}) {
        loesungsCommands.add(commands)
        doc(markdownFilename(loesungsCommands.size)) {
            markdown("# Aufgabe ${loesungsCommands.size} - $title")
            markdown(description)
        }
    }

    fun commit(fileName: String, message: String = "Dummy commit message") {
        newGit("add $fileName")
        newGit("""commit $fileName -m "$message"""")
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
        val lines = executeProcess(
                "git", "symbolic-ref", "--short", "HEAD",
                stdoutRedirect = if (false) INHERIT else PIPE,
                errorRedirect = PIPE
        ).inputStream.bufferedReader().lines().toList()
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

fun InputStream.readLines(): List<String> = bufferedReader().readLines()
