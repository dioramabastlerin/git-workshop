import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.specs.StringSpec

class WritingDocsTest : StringSpec({

    "logging"  {
        buildGitSamples(description().name) {

            logAsMarkdown() should beEmpty()

            createDir("dir1")

            logAsMarkdown() should containExactly(
                    "    $ mkdir dir1"
            )

            clearLog()

            createDir("sub") {
                createDir("subsub")
            }

            logAsMarkdown() should containExactly(
                    "    $ mkdir sub",
                    "    $ cd sub",
                    "    $ mkdir subsub",
                    "    $ cd .."
            )

            clearLog()

            createFile("my-new-file") {}

            logAsMarkdown() should containExactly(
                    "    $ # create file 'my-new-file'"
            )

        }
    }
})