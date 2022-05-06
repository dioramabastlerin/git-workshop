import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import io.kotlintest.specs.StringSpec

class BuildingSampleVariantsTest : StringSpec({

    "duplication" {
        createCollectionOfSamples(description().name) {

            createSample("base.aufgabe") {
                createFile("base-file")
            }
        }
    }
})