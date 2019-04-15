import io.kotlintest.TestContext
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File
import kotlin.streams.toList

class GitSampleBuilderTest : StringSpec({

    "creating repositorys"  {
        Directory(testDir()).run {
            cleanDirectory()

            val repo0 = createRepository()
            val repo1 = createRepository("repo1")
            val repo2 = createRepository("repo2") {
                execute("touch wurst")
            }

            listOf(repo0, repo1, repo2).forAll { it.ls() shouldContain ".git" }

            repo0.rootDir.name shouldBe "repo"
            repo1.rootDir.name shouldBe "repo1"
            repo2.rootDir.name shouldBe "repo2"

            repo2.ls() shouldContain "wurst"
        }
    }

    "sandbox"  {
        Directory(testDir()).run {
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
        Directory(testDir()).run {
            cleanDirectory()
            execute("ls -1").toList() should beEmpty()
            execute("touch hallo welt")
            execute("ls -1 .").toList() shouldContainExactly listOf("hallo", "welt")
        }

    }

})


private fun TestContext.testDir() = File("build/tests/${this.description().name}")

open class Directory(val rootDir: File = File("build/gitsamples")) {


    init {
        rootDir.mkdirs()
    }


    fun cleanDirectory() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }


    fun execute(command: String): List<String> = executeRaw(command, false).inputStream.bufferedReader().lines().toList()

    fun show(command: String): Process = executeRaw(command, true)


    private fun executeRaw(command: String, inheritStdout: Boolean): Process {
        val processBuilder = ProcessBuilder(command.split("""\s+""".toRegex()))

        processBuilder.directory(rootDir)

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

    fun dir(relativePath: String): File = File(rootDir, relativePath).absoluteFile

    fun inDir(dir: File, function: Directory.() -> Unit) = Directory(dir).run(function)

    fun git(gitCommand: String): List<String> = execute("git $gitCommand")


    fun createRepository(newRepoName: String = "repo", commands: GitRepo.() -> Unit = {}): GitRepo {
        git("init $newRepoName")
        return GitRepo(File(rootDir, newRepoName).absoluteFile, commands)
    }


    fun bareRepo(newRepBasename: String = "server", function: Directory.() -> Unit): GitRepo {
        val tmpDirName = ".$newRepBasename"
        inDir(dir(tmpDirName)) {
            git("init")
            function()
        }

        val serverRepoName = "$newRepBasename.git"
        git("clone --bare $tmpDirName $serverRepoName")
        return GitRepo(File(rootDir, serverRepoName).absoluteFile)
    }

    fun inRepo(repo: GitRepo, function: Directory.() -> Unit) {
        inDir(repo.rootDir, function)
    }

    fun ls(): List<String> = execute("ls -A")



}

class GitRepo (rootDir: File, commands: GitRepo.() -> Unit = {}) : Directory(rootDir) {

    init {
        commands();
    }

    val name: String get() = rootDir.name

    fun cloneTo(targetDir: File, function: Directory.() -> Unit): GitRepo {
        val builder = Directory(rootDir)
        builder.git("clone . ${targetDir.absolutePath}")
        builder.inDir(targetDir, function)
        return GitRepo(targetDir)
    }

}

