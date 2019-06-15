import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildingSampleVariantsTest : StringSpec({

    "duplication" {
        createCollectionOfSamples(description().name) {

            var expectedDir: String? = null

            createSample("base.aufgabe") {
                createFile("base-file")
                expectedDir = rootDir.parent
            }

            copySample("base.aufgabe", "base.loesung") {
                baseName shouldBe "base.loesung"
                rootDir.name shouldBe "base.loesung"
                rootDir.parent shouldBe expectedDir

                listFilenames() should containExactly("base-file")
            }
        }
    }
})