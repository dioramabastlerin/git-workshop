import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.beEmpty
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
            listFilenames() should containExactly("sub1", "sub2")

            createDir("sub2/subsub2")
            inDir("sub2") { listFilenames() should containExactly("subsub2") }

            createDir("sub3/subsub3")
            listFilenames() should contain("sub3")
            inDir("sub3") { listFilenames() should containExactly("subsub3") }

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

                listFilenames() should containExactly("kaese", "wurst")
            }
        }
    }

    "applying commands to dirs"  {
        buildGitSamples(description().name) {

            shouldThrow<IllegalStateException> {
                inDir("sub") {}
            }

            createDir("sub")

            inDir("sub") {
                executeSplitted("touch", "mandelbrot")
                listFilenames() should containExactly("mandelbrot")
            }

        }
    }

    "clearing dirs"  {
        buildGitSamples(description().name) {

            executeSplitted("touch", "file1")
            createDir("sub") { executeSplitted("touch", "file2") }

            clear()

            listFilenames() should beEmpty()
        }
    }

})