package de.kapitel26.gitsamplebuilder.impl

import java.io.File
import kotlin.streams.toList

class Repo(rootDir: File, log: LogBuilder, commands: (Repo.() -> Unit)? = null) : AbstracWorkingDir<Repo>(rootDir, log = log) {

    init {
        if (commands != null) {
            log.cd(rootDir.name, rootDir.parentFile.name)
            commands()
            log.cd("..", rootDir.name)
        }
    }

    val name: String get() = rootDir.name

    fun cloneTo(targetDir: File, function: AbstracWorkingDir<Repo>.() -> Unit): Repo {
        val builder = de.kapitel26.gitsamplebuilder.impl.Dir(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        return Repo(targetDir, log)
                .apply(function)
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

    // TODO does not work in new repo
    private fun currentBranch(): String {
        val lines = executeNoLog(arrayOf("git", "symbolic-ref", "--short", "HEAD"), false).inputStream.bufferedReader().lines().toList()
        println("rev-parse ${lines}")
        if (lines.size == 1)
            return lines.first()
        else
            return "master"
    }


    fun startBranch(branchName: String, startingAt: String = "HEAD", function: () -> Unit) {
        git("branch", branchName, startingAt)
        onBranch(branchName, function)
    }

    fun onBranch(branchName: String, function: () -> Unit) {
        val previousBranch = currentBranch()
        if (previousBranch == branchName) {
            function()
        } else {
            git("checkout", branchName)
            function()
            git("checkout", previousBranch)

        }
    }

}