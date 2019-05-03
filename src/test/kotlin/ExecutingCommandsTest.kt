import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ExecutingCommandsTest : StringSpec({

    "executing commands in directories" {
        buildGitSamples(description().name) {
            execute("ls -1") should beEmpty()
            execute("touch hallo welt")
            execute("ls -1 .") shouldContainExactly listOf("hallo", "welt")

            directory("sub") {
                execute("pwd")[0] shouldBe rootDir.absolutePath
                execute("touch created-in-subdir")
            }
            execute("ls -1 sub") shouldBe listOf("created-in-subdir")
        }
    }

})