import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildingSampleVariantsTest : StringSpec({

    "duplication" {
        buildGitSamples(description().name) {
            createDir("base.aufgabe") {
                val baseDir = this
                createFile("base-file")

                createSampleVariant("loesung") {
                    baseName shouldBe "base.loesung"
                    rootDir.name shouldBe "base.loesung"
                    rootDir.parent shouldBe baseDir.rootDir.parent

                    list() should containExactly("base-file")
                }
            }
        }
    }
})