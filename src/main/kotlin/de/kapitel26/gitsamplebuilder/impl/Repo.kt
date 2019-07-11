package de.kapitel26.gitsamplebuilder.impl

import java.io.File

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
        val builder = Dir(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        return Repo(targetDir, log)
                .apply(function)
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