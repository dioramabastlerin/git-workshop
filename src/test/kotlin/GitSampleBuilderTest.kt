import io.kotlintest.TestContext
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.should
import io.kotlintest.specs.StringSpec
import java.io.File
import java.util.stream.Stream
import kotlin.streams.toList

class GitSampleBuilderTest : StringSpec({

    "sandbox"  {
        GitSamplesBuilder(testDir()).run {
            cleanDirectory()

            val serverRepo = bareRepo("myproject") {
                execute("touch myfile")
                git("add myfile")
                git("commit -m add-my-file")
            }


            val alice = serverRepo.cloneTo(dir("alice")) {
                execute("touch fileofalice")
                git("add fileofalice")
                git("commit -m fileofalice")
                git("push")
            }

            val bob = serverRepo.cloneTo(dir("bob")) {
                git("pull")
                execute("touch bobsfile")
                git("add bobsfile")
                git("commit -m bobsfile")
                git("push")
            }

            inRepo(alice) {
                show("git pull")
                show("ls -lah")
            }
        }
    }

    "execute commands" {
        GitSamplesBuilder(testDir()).run {
            cleanDirectory()
            execute("ls -1").toList() should beEmpty()
            execute("touch hallo welt")
            execute("ls -1 .").toList() shouldContainExactly listOf("hallo", "welt")
        }

    }

})


private fun TestContext.testDir() = File("build/tests/${this.description().name}")

class GitSamplesBuilder(val workingDir: File = File("build/gitsamples")) {


    init {
        workingDir.mkdirs()
    }


    fun cleanDirectory() {
        workingDir.deleteRecursively()
        workingDir.mkdirs()
    }


    fun execute(command: String): Stream<String> = executeRaw(command, false).inputStream.bufferedReader().lines()

    fun show(command: String): Process = executeRaw(command, true)


    private fun executeRaw(command: String, inheritStdout: Boolean): Process {
        val processBuilder = ProcessBuilder(command.split("""\s+""".toRegex()))

        processBuilder.directory(workingDir)

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

    fun dir(relativePath: String): File = File(workingDir, relativePath).absoluteFile

    fun inDir(dir: File, function: GitSamplesBuilder.() -> Unit) = GitSamplesBuilder(dir).run(function)

    fun git(gitCommand: String): Stream<String> = execute("git $gitCommand")


    fun bareRepo(newRepBasename: String = "server", function: GitSamplesBuilder.() -> Unit): GitRepo {
        val tmpDirName = ".$newRepBasename"
        inDir(dir(tmpDirName)) {
            git("init")
            function()
        }

        val serverRepoName = "$newRepBasename.git"
        git("clone --bare $tmpDirName $serverRepoName")
        return GitRepo(File(workingDir, serverRepoName).absoluteFile)
    }

    fun inRepo(repo: GitRepo, function: GitSamplesBuilder.() -> Unit) {
        inDir(repo.rootDir, function)
    }



}

data class GitRepo(val rootDir: File) {

    val name: String get() = rootDir.name

    fun cloneTo(targetDir: File, function: GitSamplesBuilder.() -> Unit): GitRepo {
        val builder = GitSamplesBuilder(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        builder.inDir(targetDir, function)
        return GitRepo(targetDir)
    }

}

