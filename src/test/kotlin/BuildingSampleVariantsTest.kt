import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import io.kotlintest.specs.StringSpec

class BuildingSampleVariantsTest : StringSpec({

    "duplication" {
        createCollectionOfSamples(description().name, ) {

            var expectedDir: String? = null

            createSample("base.aufgabe") {
                createFile("base-file")
                expectedDir = rootDir.parent
            }
        }
    }
})