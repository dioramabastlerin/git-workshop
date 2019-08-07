package de.kapitel26.gitsamplebuilder

import impl.Repo
import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.matchers.containAll
import io.kotlintest.matchers.string.include
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import java.io.File


class WorkingWithGitReposTest : StringSpec({


    "creating repositorys"  {
        buildGitSamples(description().name) {

            createRepo { listFilenames() should containExactly(".git") }
            createRepo("custom-name") { listFilenames() should containExactly(".git") }

            listFilenames() should containExactly("custom-name", "repo")
        }


    }

    "executing commands in repositorys"  {
        buildGitSamples(description().name) {
            createRepo("repo1") {
                git("status")[0] shouldBe "On branch master"
            }

            inRepo("repo1") {
                git("status") should containAll("On branch master")
            }

            shouldThrow<IllegalStateException> {
                inRepo("not-existing") { }
            }
        }
    }

    "committing a file"  {
        buildGitSamples(description().name) {
            createRepo {
                createFile("myfile")
                git("add myfile") // bc file is new
                git { commit("myfile", null) }

                edit("myfile", 1)
                git { commit("myfile", null) }

                filesInHead() shouldBe listOf("myfile")
            }
        }
    }

    "creating and committing a file"  {
        buildGitSamples(description().name) {
            createRepo {
                createFileAndCommit("myfile")

                filesInHead() shouldBe listOf("myfile")
            }
        }
    }

    "creating and switching branches"  {
        buildGitSamples(description().name) {
            createRepo {
                createFile("myfile")
                git("add myfile")
                editAndCommit("myfile", 0)

                startBranch("salami") { editAndCommit("myfile", 5) }
                startBranch("stracke") { editAndCommit("myfile", 11) }
                onBranch("salami") { editAndCommit("myfile", 6) }
                editAndCommit("myfile", 1) // will be back on master
                onBranch("master") { editAndCommit("myfile", 2) }

                git("merge salami")
                git("merge stracke")
                val resultLines = File(rootDir, "myfile").readLines()
                resultLines[0] should include("master")
                resultLines[1] should include("master")
                resultLines[2] should include("master")
                resultLines[5] should include("salami")
                resultLines[6] should include("salami")
                resultLines[11] should include("stracke")

            }
        }
    }
})

private fun Repo.filesInHead() = git("ls-tree HEAD --name-only -- myfile")



