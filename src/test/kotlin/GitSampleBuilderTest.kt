package de.kapitel26.gitsamplebuilder

import io.kotlintest.TestContext
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class GitSampleBuilderTest : StringSpec({

    "creating directories and listing content"  {
        inSamplesDirectory {

            directory("sub1")
            directory("sub1") // ok, if it exists already
            directory("sub2/subsub")

            this.list() shouldContainAll listOf("sub1", "sub2")
            directory("sub2").list() shouldBe listOf("subsub")
        }
    }

    "executing commands in directories" {
        inSamplesDirectory {
            execute("ls -1") should beEmpty()
            execute("touch hallo welt")
            execute("ls -1 .") shouldContainExactly listOf("hallo", "welt")

            directory("sub") {
                execute("pwd")[0] shouldBe rootDir.absolutePath
                execute("touch created-in-subdir")
            }
            execute("ls -1 sub") shouldBe listOf("created-in-subdir")
        }
    }

    "creating repositorys"  {
        inSamplesDirectory {

            listOf(
                    createRepository(), // default name "repo
                    createRepository("custom-name1"),
                    createRepository("custom-name2") {}
            )
                    .also { it.forAll { repo -> repo.list() shouldContain ".git" } }
                    .map { it.rootDir.name } shouldBe listOf("repo", "custom-name1", "custom-name2")
        }
    }

    "executing commands in repositorys"  {
        inSamplesDirectory {
            val repo0 = createRepository()
            repo0.run {
                git("status")[0] shouldBe "On branch master"
            }

            createRepository("repo1") {
                git("status")[0] shouldBe "On branch master"
            }
        }
    }
})

private fun TestContext.inSamplesDirectory(block: Directory.() -> Unit) {
    Directory(File("build/tests/${description().name}"))
            .apply { cleanDirectory() }
            .run(block)
}


