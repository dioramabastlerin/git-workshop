import io.kotlintest.TestContext
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.should
import io.kotlintest.specs.StringSpec
import java.io.File
import java.util.stream.Stream
import kotlin.streams.toList

class GitSampleBuilderTest : StringSpec( {

    "sandbox"  {
        GitSamplesBuilder(testDir()).run {
            clear()
            inDir("base") {
                show("ls -lah ..")
            }
        }
    }

    "execute commands" {
        GitSamplesBuilder(testDir()).run {
            clear()
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


    fun clear() {
        workingDir.deleteRecursively()
        workingDir.mkdirs()
    }


    fun execute(command: String): Stream<String> = executeRaw(command, false).inputStream.bufferedReader().lines()

    fun git(gitCommand: String): Stream<String> = execute("git $gitCommand")

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


    fun inDir(subDir: String, function: GitSamplesBuilder.() -> Unit) {
        GitSamplesBuilder(File(workingDir, subDir)).run(function)
    }


}