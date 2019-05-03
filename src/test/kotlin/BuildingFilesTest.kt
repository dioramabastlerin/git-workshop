import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.containAll
import io.kotlintest.matchers.endWith
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class BuildingFilesTest : StringSpec({
    "creating files" {
        buildGitSamples(description().name) {
            val file1 = createFile()

            with(file1.location) {
                name shouldBe "file"
                exists() shouldBe true
                readLines()
                        .apply { size shouldBe 12 }
                        .forAll { it should endWith(" created") }
            }

            createFile("hans", "wurst")
            File(rootDir, "hans").readText() shouldBe "wurst"

        }
    }

    "editing files" {
        buildGitSamples(description().name) {
            val file1 = createFile()
            file1.edit(2..3)
            file1.edit(5..5, "BETA")

            File(rootDir, "file").readLines() should containAll(
                    "line 2 edited / line 2 created",
                    "line 3 edited / line 3 created",
                    "line 5 BETA / line 5 created"
            )


        }
    }
})