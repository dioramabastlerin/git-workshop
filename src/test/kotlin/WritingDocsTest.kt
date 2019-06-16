import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class WritingDocsTest : StringSpec({

    "collecting markdown docs"  {
        buildGitSamples(description().name) {

            logAsMarkdown() shouldBe emptyList()

            markdown("helloWorld")
            logAsMarkdown() should containExactly("helloWorld", "")

            clearLog()
            val text = """
                line1
                line2
            """
            markdown(text)
            logAsMarkdown() should containExactly(text.trimIndent().lines() + "")

            clearLog()
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
            inDir("sub") {
                inDir("subsub") {
                    execute("echo moin")
                }
            }

            logAsMarkdown() should containExactly(
                    "    $ cd sub",
                    "    $ cd subsub",
                    "    $ echo moin",
                    "    moin",
                    "    $ cd ..",
                    "    $ cd .."
            )

            clearLog()
            createFile("my-new-file") {
                edit(1)
                edit(2..3, "MOIN")
            }
            logAsMarkdown() should containExactly(
                    "    $ # created file 'my-new-file'",
                    "    $ # edited file 'my-new-file' at 1..1",
                    "    $ # MOIN file 'my-new-file' at 2..3"
            )

            clearLog()
            createRepo {
                git("status")
            }
            logAsMarkdown() should containExactly(
                    "    $ git init repo",
                    "    $ cd repo",
                    "    $ git status",
                    "    On branch master",
                    "    ",
                    "    No commits yet",
                    "    ",
                    "    nothing to commit (create/copy files and use \"git add\" to track)",
                    "    $ cd .."
            )

            clearLog()
            inRepo {
                git("branch")
            }
            logAsMarkdown() should containExactly(
                    "    $ cd repo",
                    "    $ git branch",
                    "    $ cd .."
            )

        }
    }

    "doc to file"  {
        buildGitSamples(description().name) {

            log.addRawLine("1")
            log.enableDoc("A")
            log.addRawLine("2")
            log.enableDoc("B")
            log.addRawLine("3")
            log.disableDoc("A")
            log.addRawLine("4")
            log.disableDoc("B")
            log.addRawLine("5")

            log.writeFiles(rootDir)

            File(rootDir, "_setup").readLines() shouldBe listOf("1", "2", "3", "4", "5")
            File(rootDir, "A").readLines() shouldBe listOf("2", "3")
            File(rootDir, "B").readLines() shouldBe listOf("3", "4")
        }
    }

    "doc to file block"  {
        buildGitSamples(description().name) {

            log.addRawLine("1")
            doc("A") {
                log.addRawLine("2")
            }
            log.addRawLine("3")

            writeDocs()

            File(rootDir, "_setup").readLines() shouldBe listOf("1", "2", "3")
            File(rootDir, "A").readLines() shouldBe listOf("2")
        }
    }


    "redirect docs to files"  {
        buildGitSamples(description().name) {

            log.shell("echo foo")
            val mydoc = createFile("sample.md", "")

            log.startWritingTo(mydoc)
            log.addRawLine("hallo")
            log.addRawLine("welt")
            log.stopWritingTo(mydoc)

            mydoc.location.readLines() shouldBe listOf("hallo", "welt")
        }
    }

    "logging to files"  {
        buildGitSamples(description().name) {

            log.shell("echo foo")

            flushLogToMarkdownFile("wurst.md")

            File(rootDir, "wurst.md").readLines() should containExactly(
                    "    $ echo foo"
            )

            log.shell("echo bar")

            flushLogToMarkdownFile("kaese.md")

            File(rootDir, "kaese.md").readLines() should containExactly(
                    "    $ echo bar"
            )
        }
    }
})