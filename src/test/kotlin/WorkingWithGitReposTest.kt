package de.kapitel26.gitsamplebuilder

import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.string.include
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File


class WorkingWithGitReposTest : StringSpec({


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
                createFile("myfile")

                commit("myfile")

                val filesInHead = git("ls-tree HEAD --name-only -- myfile")
                filesInHead shouldBe listOf("myfile")
            }
        }
    }

    "creating and switching branches"  {
        buildGitSamples(description().name) {
            createRepository {
                createFile("myfile")
                editAndCommit("myfile", 0)

                startBranch("salami") { editAndCommit("myfile", 5) }
                startBranch("stracke") { editAndCommit("myfile", 11) }
                onBranch("salami") { editAndCommit("myfile", 6) }
                editAndCommit("myfile", 1) // will be back on master
                onBranch("master") { editAndCommit("myfile", 2) }

                git("merge", "salami")
                git("merge", "stracke")
                val resultLines = File(rootDir, "myfile").readLines()
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



