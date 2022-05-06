import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.collections.containExactly
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

            inFile("file") { lines().size shouldBe 1 }

            shouldThrow<java.lang.IllegalStateException> {
                inFile("does-not-exist") {}
            }
        }
    }


    "manipulating content files" {
        buildGitSamples(description().name) {
            createFile("afile") {
                content.lines().size shouldBe 12

                content = """
                    eins
                    zwei
                    drei
                """.trimIndent()

                content.lines().size shouldBe 3
            }

            File(rootDir, "afile").readLines() should containExactly("eins", "zwei", "drei")

            inFile("afile") { content = "moin" }

            File(rootDir, "afile").readText() shouldBe "moin"
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

            File(rootDir, "afile").readLines() shouldBe listOf(
                    "line 0 Edit file afile / line 0 created",
                    "line 1 created",
                    "line 2 edited / line 2 created",
                    "line 3 edited / line 3 created",
                    "line 4 created",
                    "line 5 BETA / line 5 created",
                    "line 6 created",
                    "line 7 edited / line 7 created",
                    "line 8 GAMMA / line 8 created",
                    "line 9 created",
                    "line 10 created",
                    "line 11 created"
            )
        }
    }

    "replacing in files" {
        buildGitSamples(description().name) {
            createFile("afile", "abc\ndef\nghi") {
                replace("ef", "EF")
            }

            File(rootDir, "afile").readLines() shouldBe listOf(
                    "abc",
                    "dEF",
                    "ghi"
            )

            // TODO wenn nichts gefunden wird
        }
    }

})