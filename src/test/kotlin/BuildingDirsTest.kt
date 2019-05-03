import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildingDirsTest : StringSpec({

    "ensureDir creates new or uses existing ensureDir"  {
        buildGitSamples(description().name) {

            ensureDir("sub1")
            ensureDir("sub1") // ok, if it exists already
            ensureDir("sub2/subsub")

            list() shouldContainAll listOf("sub1", "sub2")
            ensureDir("sub2") {
                list() shouldBe listOf("subsub")
            }
        }
    }

})