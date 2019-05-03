import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildingDirsTest : StringSpec({

    "creating directories and listing content"  {
        buildGitSamples(description().name) {

            directory("sub1")
            directory("sub1") // ok, if it exists already
            directory("sub2/subsub")

            list() shouldContainAll listOf("sub1", "sub2")
            directory("sub2").list() shouldBe listOf("subsub")
        }
    }

})