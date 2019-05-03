package de.kapitel26.gitsamplebuilder

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GitSampleBuilderSample : StringSpec({


    "problems with rebased commits"  {
        buildGitSamples(description().name) {
            createRepo {

                createFile("file")

                commit("file")

                startBranch("feature") {
                    editAndCommit("file", 5, "to be REBASED")
                }

                onBranch("master") {
                    editAndCommit("file", 1)
                }

                duplicatedSample("rebased-commit-will-not-merge") {
                    startBranch("rebased-feature", "feature") {
                        git("rebase", "master")
                    }

                    git("checkout", "feature")
                    editAndCommit("file", 5, "edit again")
                    try {
                        git("merge", "rebased-feature")
                    } catch (e: CommandlineException) {
                        e.failedProcess.exitValue() shouldBe 1
                    }
                }

                duplicatedSample("merge-will-work") {
                    startBranch("merged-feature", "feature") {
                        git("merge", "master")
                    }

                    git("checkout", "feature")
                    editAndCommit("file", 5, "edit again")
                    git("merge", "merged-feature")

                }

                duplicatedSample("rebased-commit-will-merge-sometimes") {
                    startBranch("rebased-feature", "feature") {
                        git("rebase", "master")
                    }

                    git("checkout", "feature")
                    git("merge", "rebased-feature")
                }

            }
        }
    }
})


