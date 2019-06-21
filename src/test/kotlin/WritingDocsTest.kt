import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class WritingDocsTest : StringSpec({

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

            File(rootDir, ".full-log.md").readLines() shouldBe listOf("1", "2", "3", "4", "5")
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

            File(rootDir, ".full-log.md").readLines() shouldBe listOf("1", "2", "3")
            File(rootDir, "A").readLines() shouldBe listOf("2")
        }
    }

})