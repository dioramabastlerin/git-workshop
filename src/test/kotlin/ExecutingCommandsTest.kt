import de.kapitel26.gitsamplebuilder.CommandLineException
import de.kapitel26.gitsamplebuilder.buildGitSamples
import de.kapitel26.gitsamplebuilder.impl.readLines
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.containAll
import io.kotlintest.matchers.match
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import java.io.IOException
import java.lang.ProcessBuilder.Redirect.INHERIT

class ExecutingCommandsTest : StringSpec({

    "exit codes and error handling" {
        buildGitSamples(description().name) {
            executeProcess("echo", "welt")
                    .exitValue() shouldBe 0

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

    "capturing or inheriting output" {
        buildGitSamples(description().name) {

            executeProcess("echo", "welt")
                    .inputStream.readLines() shouldBe listOf("welt")

            executeProcess("/bin/bash", "-c", "echo written-stdout >&2")
                    .errorStream.readLines() shouldBe listOf("written-stdout")

            executeProcess("echo", "hallo", stdoutRedirect = INHERIT)
                    .inputStream.readLines() should beEmpty()

            executeProcess("ls", "gipsnich", validateOutcome = {}, errorRedirect = INHERIT)
                    .errorStream.readLines() should beEmpty()
        }
    }

    "executing commans with bash shell" {
        buildGitSamples(description().name) {
            bash("ls -a") should
                    containAll(".", "..")

            bash("""echo $(date -I)""").single() should
                    match("""\d\d\d\d-\d\d-\d\d""")

            bash("""echo -e "a\nb\\"""") should
                    containExactly("""a""", """b\""")
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


