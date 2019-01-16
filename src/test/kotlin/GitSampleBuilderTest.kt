import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.haveSize
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File
import java.util.stream.Stream
import kotlin.streams.toList

class GitSampleBuilderTest : StringSpec( {

    "sandbox"  {
        GitSamplesBuilder().run {
            execute("ls -1").toList() should beEmpty()
            execute("touch hallo welt")
            execute("ls -1 .").toList() shouldContainExactly listOf("hallo", "welt")
        }
    }

})


class GitSamplesBuilder(val workingDir: File = File("build/gitsamples")) {

    init {
        if(workingDir.exists())
            workingDir.deleteRecursively()
        workingDir.mkdirs()
    }

    fun execute(command: String): Stream<String> {
        val processBuilder = ProcessBuilder(command.split("""\s+""".toRegex()))
        processBuilder.directory(workingDir)
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
        val process = processBuilder.start()
        val exitCode = process.waitFor()
        if (exitCode != 0)
            throw Exception("Failed with exit code $exitCode: $command")
        return process.inputStream.bufferedReader().lines()
    }


}