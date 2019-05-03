import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.containAll
import io.kotlintest.matchers.endWith
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import java.io.File

class BuildingFilesTest : StringSpec({

    "creating files" {
        buildGitSamples(description().name) {

            createFile("file")

            with(File(rootDir, "file")) {
                name shouldBe "file"
                exists() shouldBe true
                readLines()
                        .apply { size shouldBe 12 }
                        .apply { this[2] shouldBe "line 2 created" }
                        .forAll { it should endWith(" created") }
            }

            createFile("custom-name", "custom-content")
            File(rootDir, "custom-name").readText() shouldBe "custom-content"

            createFile("already-existing")
            shouldThrow<IllegalStateException> {
                createFile("already-existing")
            }
        }
    }

    "applying commands to files" {
        buildGitSamples(description().name) {

            createFile("file", "hallo") { lines() shouldBe listOf("hallo") }

            file("file") { lines().size shouldBe 1 }

            shouldThrow<java.lang.IllegalStateException> {
                file("does-not-exist") {}
            }
        }
    }


    "editing files" {
        buildGitSamples(description().name) {
            createFile("afile") {
                edit(0)
                edit(2..3)
                edit(5..5, "BETA")
            }

            edit("afile", 7)
            edit("afile", 8, "GAMMA")

            File(rootDir, "afile").readLines() should containAll(
                    "line 0 edited / line 0 created",
                    "line 2 edited / line 2 created",
                    "line 3 edited / line 3 created",
                    "line 5 BETA / line 5 created",
                    "line 7 edited / line 7 created",
                    "line 8 GAMMA / line 8 created"
            )
        }
    }
})