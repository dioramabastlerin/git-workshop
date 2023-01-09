package impl

import java.io.File
import java.nio.file.Paths

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
        val previousBranch = currentBranch()
        git("switch -c $branchName $startingAt")
        function()
        git("switch $previousBranch")    
    }

    fun onBranch(branchName: String, function: () -> Unit) {
        val previousBranch = currentBranch()
        if (previousBranch == branchName) {
            function()
        } else {
            git("switch $branchName")
            function()
            git("switch $previousBranch")

        }
    }

    fun user(user: String) {
        git("config user.name $user")
        git("config user.email $user@beispieladresse.de")
        git("config pull.rebase false")
    }

    fun createClone(cloneLocation: String, vararg args: String, commands: (Repo.() -> Unit)? = null) {
        git("clone . $cloneLocation ${args.joinToString(" ")}")
        val originDir = rootDir
        inRepo(cloneLocation) {
            val relativeOrigin = Paths.get(rootDir.canonicalPath).relativize(Paths.get(originDir.canonicalPath))
            git("remote set-url origin $relativeOrigin")
            if (commands != null)
                commands()
        }
    }
}