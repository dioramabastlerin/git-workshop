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
                    .apply { log.createFile(name, content, currentDirname()) }
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

    fun executeSplitted(vararg command: String): List<String> = exeuteSplittedRaw(false, *command).inputStream.bufferedReader().lines().toList()

    fun show(command: String): Process = executeRaw(command, true)


    fun executeRaw(command: String, inheritStdout: Boolean): Process {
        val splittedCommandLineArguments = command.split("""\s+""".toRegex()).toTypedArray()
        return exeuteSplittedRaw(inheritStdout, *splittedCommandLineArguments)
    }

    fun exeuteSplittedRaw(inheritStdout: Boolean, vararg splittedCommandLineArguments: String): Process {
        log.shell(splittedCommandLineArguments.joinToString(" "), rootDir.name)
        val processBuilder = ProcessBuilder(*splittedCommandLineArguments)

        processBuilder.directory(rootDir)

        processBuilder.redirectError(ProcessBuilder.Redirect.PIPE)
        if (inheritStdout) {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        }

        val process = processBuilder.start()

        val exitCode = process.waitFor()
        if (exitCode != 0)
            throw CommandlineException(process, "Failed with exit code $exitCode: ${splittedCommandLineArguments.joinToString(" ")}")
        return process
    }


    fun git(gitCommand: String): List<String> = execute("git $gitCommand")

    fun git(vararg commandLineArguments: String): List<String> = executeSplitted(*(listOf("git") + commandLineArguments).toTypedArray())

    fun createRepo(newRepoName: String = "repo", vararg args: String, commands: (Repo.() -> Unit)? = null): Unit {
        git("init", newRepoName, *args)
        Repo(java.io.File(rootDir, newRepoName).absoluteFile, log, commands)
    }

    fun createClonedRepo(originalRepo: String, clonedRepo: String, commands: (Repo.() -> Unit)? = {}) {
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
            markdown("# Aufgabe ${loesungsCommands.size}\n\n$description")
        }
    }

    private fun markdownFilename(nr: Int) = "${formatNr(nr)}-aufgabe.md"

    private fun formatNr(nr: Int): String = "%02d".format(nr)

    fun applyLoesungen() =
            loesungsCommands.forEachIndexed { index, commands ->
                doc(markdownFilename(index + 1)) {
                    markdown("## Lösung")
                    commands()
                }
            }
}