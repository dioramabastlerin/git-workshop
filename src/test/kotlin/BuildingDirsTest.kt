import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.contain
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class BuildingDirsTest : StringSpec({

    "creating dirs"  {
        buildGitSamples(description().name) {

            createDir("sub1")
            createDir("sub2")
            list() should containExactly("sub1", "sub2")

            createDir("sub2/subsub2")
            dir("sub2") { list() should containExactly("subsub2") }

            createDir("sub3/subsub3")
            list() should contain("sub3")
            dir("sub3") { list() should containExactly("subsub3") }

            createDir("sub4")
            shouldThrow<IllegalStateException> {
                createDir("sub4")
            }
        }
    }

    "appying commands to new created dir"  {
        buildGitSamples(description().name) {

            createDir("sub") {
                executeSplitted("touch", "wurst")
                executeSplitted("touch", "kaese")

                list() should containExactly("kaese", "wurst")
            }
        }
    }

    "applying commands to dirs"  {
        buildGitSamples(description().name) {

            shouldThrow<IllegalStateException> {
                dir("sub") {}
            }

            createDir("sub")

            dir("sub") {
                executeSplitted("touch", "mandelbrot")
                list() should containExactly("mandelbrot")
            }

        }
    }
})