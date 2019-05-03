package de.kapitel26.gitsamplebuilder

import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.containAll
import io.kotlintest.matchers.endWith
import io.kotlintest.matchers.string.include
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class GitSampleBuilderTest : StringSpec({

    "creating directories and listing content"  {
        buildGitSamples(description().name) {

            directory("sub1")
            directory("sub1") // ok, if it exists already
            directory("sub2/subsub")

            list() shouldContainAll listOf("sub1", "sub2")
            directory("sub2").list() shouldBe listOf("subsub")
        }
    }

    "executing commands in directories" {
        buildGitSamples(description().name) {
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

    "duplication" {
        buildGitSamples(description().name) {
            directory("base") {
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


    "creating repositorys"  {
        buildGitSamples(description().name) {

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
        buildGitSamples(description().name) {
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
        buildGitSamples(description().name) {
            createRepository {
                val file = createFile("myfile")

                commit(file)

                val filesInHead = git("ls-tree HEAD --name-only -- myfile")
                filesInHead shouldBe listOf("myfile")
            }
        }
    }

    "creating and switching branches"  {
        buildGitSamples(description().name) {
            createRepository {
                val file = createFile("myfile")
                editAndCommit(file, 0)

                startBranch("salami") { editAndCommit(file, 5) }
                startBranch("stracke") { editAndCommit(file, 11) }
                onBranch("salami") { editAndCommit(file, 6) }
                editAndCommit(file, 1) // will be back on master
                onBranch("master") { editAndCommit(file, 2) }

                git("merge", "salami")
                git("merge", "stracke")
                val resultLines = file.lines()
                resultLines[0] should include("`master`")
                resultLines[1] should include("`master`")
                resultLines[2] should include("`master`")
                resultLines[5] should include("`salami`")
                resultLines[6] should include("`salami`")
                resultLines[11] should include("`stracke`")

            }
        }
    }
})



