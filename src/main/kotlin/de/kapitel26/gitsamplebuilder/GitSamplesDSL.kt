package de.kapitel26.gitsamplebuilder

import de.kapitel26.gitsamplebuilder.impl.GitRepo
import de.kapitel26.gitsamplebuilder.impl.PlainDirectory
import de.kapitel26.gitsamplebuilder.impl.SampleFile
import java.io.File
import kotlin.streams.toList

abstract class Directory<T>(val rootDir: File = File("buildGitSamples/gitsamples"), val baseName: String = rootDir.name) {


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
            throw CommandlineException(process, "Failed with exit code $exitCode: ${splittedCommandLineArguments.joinToString(" ")}")
        return process
    }

    fun dir(relativePath: String): File = File(rootDir, relativePath).absoluteFile

    fun directory(dirName: String, commands: PlainDirectory.() -> Unit = {}): PlainDirectory = directory(File(rootDir, dirName), commands)

    fun directory(dir: File, commands: PlainDirectory.() -> Unit): PlainDirectory = PlainDirectory(dir)
            .also(commands)

    fun git(gitCommand: String): List<String> = execute("git $gitCommand")

    fun git(vararg commandLineArguments: String): List<String> = executeSplitted(*(listOf("git") + commandLineArguments).toTypedArray())

    fun createRepository(newRepoName: String = "repo", commands: GitRepo.() -> Unit = {}): GitRepo {
        git("init $newRepoName")
        return GitRepo(File(rootDir, newRepoName).absoluteFile, commands)
    }


    fun bareRepo(newRepBasename: String = "server", function: Directory<T>.() -> Unit): GitRepo {
        val tmpDirName = ".$newRepBasename"
        directory(dir(tmpDirName)) {
            git("init")
            function()
        }

        val serverRepoName = "$newRepBasename.git"
        git("clone --bare $tmpDirName $serverRepoName")
        return GitRepo(File(rootDir, serverRepoName).absoluteFile)
    }

    fun inRepo(repo: GitRepo, function: GitRepo.() -> Unit) {
        GitRepo(repo.rootDir, function)
    }

    fun list(): List<String> = execute("ls -A")

    fun createFile(name: String = "file", content: String? = null): SampleFile =
            file(name).apply { location.writeText(content ?: createSampleFileContent()) }

    fun file(name: String = "file"): SampleFile = SampleFile(File(rootDir, name))

    abstract fun duplicatedSample(suffix: String, function: T.() -> Unit): T


}

class CommandlineException(val failedProcess: Process, message: String) : RuntimeException(message)


fun buildGitSamples(sampleName: String, sampleDir: String = "build/git-samples", commands: PlainDirectory.() -> Unit) =
        buildGitSamples(File(sampleDir, sampleName), commands)


fun buildGitSamples(rootDir: File, commands: PlainDirectory.() -> Unit) {
    PlainDirectory(rootDir)
            .apply { cleanDirectory() }
            .run(commands)
}