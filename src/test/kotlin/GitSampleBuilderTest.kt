package de.kapitel26.gitsamplebuilder

import io.kotlintest.TestContext
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.startWith
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

    "creating files" {
        inSamplesDirectory {
            val file1 = createFile()

            with(file1.location) {
                name shouldBe "file"
                exists() shouldBe true
                readLines()
                        .apply { size shouldBe 12 }
                        .forAll { it should startWith("NEW -") }
            }

            createFile("hans", "wurst")
            File(rootDir, "hans").readText() shouldBe "wurst"

        }
    }

    "editing files" {
        inSamplesDirectory {
            val file1 = createFile()
            file1.edit(2..3)
            file1.edit(5..5, "BETA")

            File(rootDir, "file").readLines() shouldContainAll listOf(
                    "line 2 edited / NEW - created as line 2 of file.",
                    "line 3 edited / NEW - created as line 3 of file.",
                    "line 5 BETA / NEW - created as line 5 of file."
            )


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

    "committing a file"  {
        inSamplesDirectory {
            createRepository {
                val file = createFile("myfile")

                commit(file)

                val filesInHead = git("ls-tree HEAD --name-only -- myfile")
                filesInHead shouldBe listOf("myfile")
            }
        }
    }

    "switching branches"  {
        inSamplesDirectory {
            createRepository {
                val file = createFile("myfile")
                editAndCommit(file, 0)
                onBranch("salami") { editAndCommit(file, 5) }
                onBranch("stracke") { editAndCommit(file, 11) }
                onBranch("salami") { editAndCommit(file, 6) }
                editAndCommit(file, 1)

                git("merge", "salami")
                git("merge", "stracke")
            }
        }
    }


})

private fun TestContext.inSamplesDirectory(block: Directory.() -> Unit) {
    Directory(File("build/tests/${description().name}"))
            .apply { cleanDirectory() }
            .run(block)
}


