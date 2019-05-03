import de.kapitel26.gitsamplebuilder.buildGitSamples
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildingSampleVariantsTest : StringSpec({

    "duplication" {
        buildGitSamples(description().name) {
            dir("base") {
                val baseDir = this
                createFile("base-file")

                duplicatedSample("duplicatedSample") {
                    baseName shouldBe "base"
                    rootDir.name shouldBe "base.duplicatedSample"
                    rootDir.parent shouldBe baseDir.rootDir.parent

                    list() should containExactly("base-file")


                    duplicatedSample("nestedcall") {
                        baseName shouldBe "base"
                        rootDir.name shouldBe "base.nestedcall"
                        rootDir.parent shouldBe baseDir.rootDir.parent


                    }

                }
            }
        }
    }
})