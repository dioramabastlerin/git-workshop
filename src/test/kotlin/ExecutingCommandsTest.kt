import de.kapitel26.gitsamplebuilder.CommandLineException
import de.kapitel26.gitsamplebuilder.buildGitSamples
import de.kapitel26.gitsamplebuilder.impl.readLines
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import java.io.IOException
import java.lang.ProcessBuilder.Redirect.INHERIT

class ExecutingCommandsTest : StringSpec({

    "execute process" {
        buildGitSamples(description().name) {
            executeProcess("echo", "welt")
                    .exitValue() shouldBe 0

            executeProcess("echo", "welt")
                    .inputStream.readLines() shouldBe listOf("welt")

            executeProcess("echo", "hallo", stdoutRedirect = INHERIT)
                    .inputStream.readLines() should beEmpty()

            executeProcess("ls", "gipsnich", validateOutcome = {}, errorRedirect = INHERIT)
                    .errorStream.readLines() should beEmpty()

            executeProcess("ls", "gipsnich", validateOutcome = { p -> p.exitValue() shouldBe 2 })
                    .exitValue() shouldBe 2

            shouldThrow<CommandLineException> { executeProcess("ls", "gipsnich") }
                    .failedProcess
                    .apply {
                        exitValue() shouldBe 2
                        errorStream.readLines() shouldBe
                                listOf("ls: cannot access 'gipsnich': No such file or directory")
                    }

            shouldThrow<IOException> { executeProcess("gipsnich", "welt") }
        }
    }

    "executing commands in directories" {
        buildGitSamples(description().name) {
            execute("ls -1") should beEmpty()
            execute("touch hallo welt")
            execute("ls -1 .") shouldContainExactly listOf("hallo", "welt")

            createDir("sub") {
                execute("pwd")[0] shouldBe rootDir.absolutePath
                execute("touch created-in-subdir")
            }
            execute("ls -1 sub") shouldBe listOf("created-in-subdir")
        }
    }


})


