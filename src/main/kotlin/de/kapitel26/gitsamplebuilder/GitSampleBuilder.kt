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

    fun show(command: String): Process = executeRaw(command, true)


    private fun executeRaw(command: String, inheritStdout: Boolean): Process {
        val processBuilder = ProcessBuilder(command.split("""\s+""".toRegex()))

        processBuilder.directory(rootDir)

        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
        if (inheritStdout) {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        }

        val process = processBuilder.start()

        val exitCode = process.waitFor()
        if (exitCode != 0)
            throw Exception("Failed with exit code $exitCode: $command")
        return process
    }

    fun dir(relativePath: String): File = File(rootDir, relativePath).absoluteFile

    fun directory(dirName: String, commands: Directory.() -> Unit = {}): Directory = directory(File(rootDir, dirName), commands)

    fun directory(dir: File, commands: Directory.() -> Unit): Directory = Directory(dir)
            .also(commands)

    fun git(gitCommand: String): List<String> = execute("git $gitCommand")


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

}