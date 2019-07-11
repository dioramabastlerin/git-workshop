import de.kapitel26.gitsamplebuilder.CommandLineException
import de.kapitel26.gitsamplebuilder.buildGitSamples
import de.kapitel26.gitsamplebuilder.impl.readLines
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.containAll
import io.kotlintest.matchers.match
import io.kotlintest.matchers.startWith
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

    "executing commands with bash shell" {
        buildGitSamples(description().name) {
            bash("ls -a") should
                    containAll(".", "..")

            bash("""echo $(date -I)""").single() should
                    match("""\d\d\d\d-\d\d-\d\d""")

            bash("""echo -e "a\nb\\"""") should
                    containExactly("""a""", """b\""")
        }
    }

    "logging bash shell commands with output" {
        buildGitSamples(description().name) {
            createDir("test") {
                doc("shell-log") {
                    bash("echo moin")
                }
            }

            log.of("shell-log") should
                    containExactly("    test$ echo moin", "    moin")
        }
    }

    "executing git commands" {
        buildGitSamples(description().name) {
            git("init").single() should
                    startWith("Initialized empty Git repository")
        }
    }


    "executing commands in directories" {
        buildGitSamples(description().name) {
            bash("ls -1") should beEmpty()
            bash("touch hallo welt")
            bash("ls -1 .") shouldContainExactly listOf("hallo", "welt")

            createDir("sub") {
                bash("pwd")[0] shouldBe rootDir.absolutePath
                bash("touch created-in-subdir")
            }
            bash("ls -1 sub") shouldBe listOf("created-in-subdir")
        }
    }


})


