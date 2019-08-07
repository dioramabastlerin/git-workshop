package impl

import java.io.File

class Repo(rootDir: File, log: LogBuilder, solutionCollector: SolutionCollector, commands: (Repo.() -> Unit)? = null) : AbstractWorkingDir<Repo>(rootDir, log = log, solutionCollector = solutionCollector) {

    init {
        if (commands != null) {
            log.cd(rootDir.name, rootDir.parentFile.name)
            commands()
            log.cd("..", rootDir.name)
        }
    }

    val name: String get() = rootDir.name

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

    fun user(user: String) {
        git("config user.name $user")
        git("config user.email $user@beispieladresse.de")
    }

    fun createClone(cloneLocation: String, vararg args: String, commands: (Repo.() -> Unit)? = null) {
        git("clone", ".", cloneLocation, *args)
        inRepo(cloneLocation, commands)
    }
}