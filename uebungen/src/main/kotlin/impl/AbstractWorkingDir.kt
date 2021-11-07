package impl

import de.kapitel26.gitsamplebuilder.CommandLineException
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.lang.ProcessBuilder.Redirect.PIPE
import java.nio.file.Paths

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
        File(java.io.File(rootDir, name).absoluteFile, log, solutionCollector)
            .apply { if (location.exists()) throw IllegalStateException("File $location is not expected to exist!") }
            .apply { log.createFile(name, currentDirname()) }
            .apply { location.writeText(content ?: createSampleFileContent()) }
            .apply(commands)

    fun inFile(name: String = "file", commands: File.() -> Unit = {}) =
        File(java.io.File(rootDir, name), log, solutionCollector)
            .apply { if (!location.exists()) throw IllegalStateException("File $location is expected to exist!") }
            .run(commands)

    fun git(commands: GitContext<T>.() -> Unit = {}) =
        GitContext<T>(this).commands()

    fun git(gitCommand: String? = null, acceptableExitCodes: Set<Int> = setOf(0)) =
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


        val outputLines = process.inputStream.bufferedReader().readLines()
        val errorLines = process.errorStream.bufferedReader().readLines()
        log.shell(command, rootDir.name, outputLines, errorLines)

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

        processBuilder.environment()["GIT_AUTHOR_DATE"] = "2021-07-29T00:00:00"
        processBuilder.environment()["GIT_COMMITTER_DATE"] = "2021-07-29T00:00:00"

        processBuilder.environment()["GIT_AUTHOR_NAME"] = "bjoern"
        processBuilder.environment()["GIT_AUTHOR_EMAIL"] = "kapitel26blog@gmail.com"
        processBuilder.environment()["GIT_COMMITTER_NAME"] = "bjoern"
        processBuilder.environment()["GIT_COMMITTER_EMAIL"] = "kapitel26blog@gmail.com"

        val process = processBuilder.start()
        process.waitFor()
        validateOutcome(process)
        return process
    }

    private fun assertExitCode(p: Process, expectedExits: Set<Int>, command: String) {
        if (p.exitValue() !in expectedExits)
            throw CommandLineException(
                p,
                "Failed with exit code ${p.exitValue()}: $command (not in ${expectedExits.joinToString(", ")})" +
                        " and message: \n${p.errorStream.readLines().joinToString("\n")}" +
                        "\n and output: \n${p.inputStream.readLines().joinToString("\n")}" +
                        "\n."
            )
    }

    private fun assertExitCode(p: Process, expectedExits: Set<Int>, splittedCommandLineArguments: Array<out String>) {
        if (p.exitValue() !in expectedExits)
            throw CommandLineException(
                p,
                "Failed with exit code ${p.exitValue()}: ${splittedCommandLineArguments.joinToString(" ")}"
            )
    }


    fun createRepo(newRepoName: String = "repo", vararg args: String, commands: (Repo.() -> Unit)? = null) {
        git("init $newRepoName ${args.joinToString(" ")}")
        Repo(java.io.File(rootDir, newRepoName).absoluteFile, log, solutionCollector, commands)
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
    fun createIntro(title: String, description: String = "", setup: T.() -> Unit = {}) {
        solutionCollector.registerAufgabe(title)
        markdown("# Übung - $title")
        markdown(description)
        supressLogging(setup)
    }

    @Suppress("UNCHECKED_CAST")
    fun createAufgabe(title: String, description: String = "", solution: T.() -> Unit = {}) {
        val header = "Schritt ${solutionCollector.collectedCommands.size + 1} - $title"
        solutionCollector.registerSchritt(header)
        solutionCollector.collectedCommands.add(
            header to {
                markdown(description)
                (this as T).solution()
            }
        )
        val pathInUebungsverzeichnis =
            Paths.get(rootDir.parentFile.parentFile.canonicalPath).relativize(Paths.get(rootDir.canonicalPath))
        markdown("<!--UEB-${solutionCollector.aufgabenName()}--><h2>${header}</h2>")
        markdown("Starte im Verze{}ichnis `aufgaben/${pathInUebungsverzeichnis}`.")
        markdown(description)
    }

    fun editAndCommit(
        fileName: String,
        line: Int,
        message: String = "Edit file $fileName at line $line on branch ${currentBranch()} by ${currentUser()}."
    ) = editAndCommit(fileName, line..line, message)

    fun editAndCommit(
        fileName: String,
        lines: IntRange,
        message: String = "Edit file $fileName at lines $lines on branch ${currentBranch()} by ${currentUser()}."
    ) {
        inFile(fileName) { edit(lines, message) }
        git { commit(fileName, "`$fileName`: $message") }
    }

    fun editAndCommit(
        fileName: String,
        commitMesssage: String = "Edited file $fileName on branch ${currentBranch()} by ${currentUser()}.",
        commands: File.() -> Unit = {}
    ) {
        inFile(fileName, commands)
        git("""add $fileName""")
        git { commit(fileName, commitMesssage ) }
    }


    fun createFileAndCommit(
        fileName: String,
        message: String = "Created file $fileName on branch ${currentBranch()} by ${currentUser()}.",
        commands: File.() -> Unit = {}
    ) {
        createFile(fileName)
        inFile(fileName, commands)
        git("""add $fileName""")
        git { commit(fileName, message) }
    }

    //    fun aufgabenFilename(name: String) = "aufgabe-${name.toLowerCase()}.md"
    fun aufgabenFilename(name: String) = "index.md"

    private fun formatNr(nr: Int): String = "%02d".format(nr)

    // TODO does not work in new repo
    fun currentBranch(): String {
        val lines = executeProcess(
            "git", "symbolic-ref", "--short", "HEAD"
        ).inputStream.bufferedReader().readLines()
        return lines.singleOrNull() ?: "MASTER"
    }

    fun currentUser(): String =
        executeProcess("git", "config", "user.name")
            .inputStream
            .bufferedReader()
            .readLines()
            .single()

    fun applyLoesungen(fullName: String) =
        logTo("loesung-${fullName}.md") {
            solutionCollector.collectedCommands.forEach { (header, command) ->
                markdown("## Lösung zu $header")
                command()
            }
            markdown("[Zur Aufgabe](aufgabe-$fullName.html){:style=\"position: fixed; right: 10px; top:60px\" .btn .btn-purple}")
            markdown("[Zum Überblick](../../ueberblick.html){:style=\"visibility: hidden\"}")
        }
}

fun InputStream.readLines(): List<String> = bufferedReader().readLines()
