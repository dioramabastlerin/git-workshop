package de.kapitel26.gitsamplebuilder

import de.kapitel26.gitsamplebuilder.impl.Dir
import de.kapitel26.gitsamplebuilder.impl.Repo
import java.io.File
import kotlin.streams.toList

abstract class AbstractDir<T>(val rootDir: File = File("buildGitSamples/gitsamples"), val baseName: String = rootDir.name) {

    fun createDir(dirName: String, commands: Dir.() -> Unit = {}) =
            File(rootDir, dirName)
                    .apply { if (exists()) throw IllegalStateException("Dir $this not expected to exist!") }
                    .apply { mkdirs() }
                    .run { Dir(this) }
                    .apply(commands)

    fun dir(dirName: String, commands: Dir.() -> Unit) =
            File(rootDir, dirName)
                    .apply { if (!exists()) throw IllegalStateException("Dir $this is expected to exist!") }
                    .run { Dir(this) }
                    .apply(commands)

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createFile(name: String = "file", content: String? = null): de.kapitel26.gitsamplebuilder.impl.File =
            file(name)
                    .apply { if (location.exists()) throw IllegalStateException("Dir $this not expected to exist!") }
                    .apply { location.writeText(content ?: createSampleFileContent()) }


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


    fun git(gitCommand: String): List<String> = execute("git $gitCommand")

    fun git(vararg commandLineArguments: String): List<String> = executeSplitted(*(listOf("git") + commandLineArguments).toTypedArray())

    fun createRepository(newRepoName: String = "repo", commands: Repo.() -> Unit = {}): Repo {
        git("init $newRepoName")
        return Repo(File(rootDir, newRepoName).absoluteFile, commands)
    }


    fun bareRepo(newRepBasename: String = "server", function: de.kapitel26.gitsamplebuilder.AbstractDir<T>.() -> Unit): Repo {
        val tmpDirName = ".$newRepBasename"
        createDir(tmpDirName) {
            git("init")
            function()
        }

        val serverRepoName = "$newRepBasename.git"
        git("clone --bare $tmpDirName $serverRepoName")
        return Repo(File(rootDir, serverRepoName).absoluteFile)
    }

    fun inRepo(repo: Repo, function: Repo.() -> Unit) {
        Repo(repo.rootDir, function)
    }

    fun list(): List<String> = execute("ls -A")

    fun file(name: String = "file"): de.kapitel26.gitsamplebuilder.impl.File = de.kapitel26.gitsamplebuilder.impl.File(File(rootDir, name))

    abstract fun duplicatedSample(suffix: String, function: T.() -> Unit): T


}

class CommandlineException(val failedProcess: Process, message: String) : RuntimeException(message)


fun buildGitSamples(sampleName: String, sampleDir: String = "build/git-samples", commands: Dir.() -> Unit) =
        buildGitSamples(File(sampleDir, sampleName), commands)


fun buildGitSamples(rootDir: File, commands: Dir.() -> Unit) {
    Dir(rootDir)
            .apply { clear() }
            .run(commands)
}