package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.AbstractDir
import java.io.File

class Repo(rootDir: File, commands: Repo.() -> Unit = {}) : AbstractDir<Repo>(rootDir) {

    init {
        commands()
    }

    val name: String get() = rootDir.name


    override fun duplicatedSample(suffix: String, function: Repo.() -> Unit): Repo =
            Repo(
                    de.kapitel26.gitsamplebuilder.impl.Dir(rootDir).duplicatedSample(suffix, {}).rootDir,
                    function)


    //    override fun duplicatedSample(suffix: String, function: AbstractDir<Repo>.() -> Unit): AbstractDir<Repo> {
//        return Repo(super.duplicatedSample(suffix, {}).rootDir)
//                .apply(function)
//    }
//
    fun cloneTo(targetDir: File, function: AbstractDir<Repo>.() -> Unit): Repo {
        val builder = de.kapitel26.gitsamplebuilder.impl.Dir(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        return Repo(targetDir)
                .apply(function)
    }

    fun commit(file: de.kapitel26.gitsamplebuilder.impl.File, message: String = "Dummy commit message") {
        // TODO check if quoting of locations is necessary
        git("add", file.location.toString())
        git("commit", "-m", message, file.location.toString())

    }

    fun editAndCommit(file: de.kapitel26.gitsamplebuilder.impl.File, line: Int, message: String = defaultMessage()) = editAndCommit(file, line..line, message)

    fun editAndCommit(file: de.kapitel26.gitsamplebuilder.impl.File, lines: IntRange, message: String = defaultMessage()) {
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