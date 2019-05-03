package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.Directory
import java.io.File

class GitRepo(rootDir: File, commands: GitRepo.() -> Unit = {}) : Directory<GitRepo>(rootDir) {

    init {
        commands()
    }

    val name: String get() = rootDir.name


    override fun duplicatedSample(suffix: String, function: GitRepo.() -> Unit): GitRepo =
            GitRepo(
                    PlainDirectory(rootDir).duplicatedSample(suffix, {}).rootDir,
                    function)


    //    override fun duplicatedSample(suffix: String, function: Directory<GitRepo>.() -> Unit): Directory<GitRepo> {
//        return GitRepo(super.duplicatedSample(suffix, {}).rootDir)
//                .apply(function)
//    }
//
    fun cloneTo(targetDir: File, function: Directory<GitRepo>.() -> Unit): GitRepo {
        val builder = PlainDirectory(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        return GitRepo(targetDir)
                .apply(function)
    }

    fun commit(file: SampleFile, message: String = "Dummy commit message") {
        // TODO check if quoting of locations is necessary
        git("add", file.location.toString())
        git("commit", "-m", message, file.location.toString())

    }

    fun editAndCommit(file: SampleFile, line: Int, message: String = defaultMessage()) = editAndCommit(file, line..line, message)

    fun editAndCommit(file: SampleFile, lines: IntRange, message: String = defaultMessage()) {
        file.edit(lines, message)
        commit(file, "`${file.location.name}`: $message")
    }

    private fun defaultMessage(): String = "edited on `${currentBranch()}`"

    // TODO does not work in new repo
    private fun currentBranch(): String {
        val lines = git("symbolic-ref", "--short", "HEAD")
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