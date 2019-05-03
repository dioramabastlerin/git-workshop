import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildingDirsTest : StringSpec({

    "dir creates new or uses existing dir"  {
        buildGitSamples(description().name) {

            dir("sub1")
            dir("sub1") // ok, if it exists already
            dir("sub2/subsub")

            list() shouldContainAll listOf("sub1", "sub2")
            dir("sub2") {
                list() shouldBe listOf("subsub")
            }
        }
    }

})