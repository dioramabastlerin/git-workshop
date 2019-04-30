package de.kapitel26.gitsamplebuilder

import java.io.File
import kotlin.streams.toList

open class Directory(val rootDir: File = File("build/gitsamples")) {


    init {
        rootDir.mkdirs()
    }


    fun cleanDirectory() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }


    fun execute(command: String): List<String> = executeRaw(command, false).inputStream.bufferedReader().lines().toList()
    fun executeSplitted(vararg command: String): List<String> = exeuteSplittedRaw(false, *command).inputStream.bufferedReader().lines().toList()

    fun show(command: String): Process = executeRaw(command, true)


    fun executeRaw(command: String, inheritStdout: Boolean): Process {
        val splittedCommandLineArguments = command.split("""\s+""".toRegex()).toTypedArray()
        return exeuteSplittedRaw(inheritStdout, *splittedCommandLineArguments)
    }

    fun exeuteSplittedRaw(inheritStdout: Boolean, vararg splittedCommandLineArguments: String): Process {
        val processBuilder = ProcessBuilder(*splittedCommandLineArguments)

        processBuilder.directory(rootDir)

        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
        if (inheritStdout) {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        }

        val process = processBuilder.start()

        val exitCode = process.waitFor()
        if (exitCode != 0)
            throw Exception("Failed with exit code $exitCode: ${splittedCommandLineArguments.joinToString(" ")}")
        return process
    }

    fun dir(relativePath: String): File = File(rootDir, relativePath).absoluteFile

    fun directory(dirName: String, commands: Directory.() -> Unit = {}): Directory = directory(File(rootDir, dirName), commands)

    fun directory(dir: File, commands: Directory.() -> Unit): Directory = Directory(dir)
            .also(commands)

    fun git(gitCommand: String): List<String> = execute("git $gitCommand")

    fun git(vararg commandLineArguments: String): List<String> = executeSplitted(*(listOf("git") + commandLineArguments).toTypedArray())

    fun createRepository(newRepoName: String = "repo", commands: GitRepo.() -> Unit = {}): GitRepo {
        git("init $newRepoName")
        return GitRepo(File(rootDir, newRepoName).absoluteFile, commands)
    }


    fun bareRepo(newRepBasename: String = "server", function: Directory.() -> Unit): GitRepo {
        val tmpDirName = ".$newRepBasename"
        directory(dir(tmpDirName)) {
            git("init")
            function()
        }

        val serverRepoName = "$newRepBasename.git"
        git("clone --bare $tmpDirName $serverRepoName")
        return GitRepo(File(rootDir, serverRepoName).absoluteFile)
    }

    fun inRepo(repo: GitRepo, function: Directory.() -> Unit) {
        directory(repo.rootDir, function)
    }

    fun list(): List<String> = execute("ls -A")

    fun createFile(name: String = "file", content: String? = null): SampleFile = SampleFile(File(rootDir, name))
            .apply { location.writeText(content ?: createSampleFileContent()) }


}

class SampleFile(val location: File) {

    fun createSampleFileContent(): String =
            (0..11).map { "NEW - created as line $it of ${location.name}." }.joinToString("\n")

    fun edit(line: Int, message: String) = edit(line..line, message)

    fun edit(linesToEdit: IntRange, message: String = "EDITED") {
        location
                .readLines()
                .mapIndexed { index, s ->
                    if (index in linesToEdit)
                        "$message at $index / $s"
                    else
                        s
                }
                .joinToString("\n")
                .also { location.writeText(it) }

    }


}

class GitRepo(rootDir: File, commands: GitRepo.() -> Unit = {}) : Directory(rootDir) {

    init {
        commands()
    }

    val name: String get() = rootDir.name

    fun cloneTo(targetDir: File, function: Directory.() -> Unit): GitRepo {
        val builder = Directory(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        builder.directory(targetDir, function)
        return GitRepo(targetDir)
    }

    fun commit(file: SampleFile, message: String = "Dummy commit message") {
        // TODO check if quoting of locations is necessary
        git("add", file.location.toString())
        git("commit", "-m", message, file.location.toString())

    }

    fun editAndCommit(file: SampleFile, line: Int, message: String = defaultMessage()) = editAndCommit(file, line..line, message)

    fun editAndCommit(file: SampleFile, lines: IntRange, message: String = defaultMessage()) {
        file.edit(lines, message)
        commit(file)
    }

    private fun defaultMessage(): String = "on branch ${currentBranch()}"

    private fun currentBranch(): String = git("rev-parse", "--abbrev-ref", "HEAD").single()
}