package de.kapitel26.gitsamplebuilder

import io.kotlintest.TestContext
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class GitSampleBuilderSample : StringSpec({


    "problems with rebased commits"  {
        inSamplesDirectory {
            createRepository {

                createFile()

                commit(file())

                startBranch("feature") {
                    editAndCommit(file(), 5)
                }

                onBranch("master") {
                    editAndCommit(file(), 1)
                }

                duplicatedSample("rebased-commit-will-not-merge") {
                    exeuteSplittedRaw(true, "pwd")

                    startBranch("rebased-feature", "feature") {
                        git("rebase", "master")
                    }

                    git("checkout", "feature")
                    editAndCommit(file(), 5)
                    try {
                        git("merge", "rebased-feature")
                    } catch (e: CommandlineException) {
                        e.failedProcess.exitValue() shouldBe 1
                    }
                }

                exeuteSplittedRaw(true, "pwd")

                duplicatedSample("rebased-commit-will-merge-sometimes") {
                    exeuteSplittedRaw(true, "pwd")
                    startBranch("rebased-feature", "feature") {
                        git("merge", "master")
                    }

                    git("checkout", "feature")
                    git("merge", "rebased-feature")
                }

                duplicatedSample("merge-will-work") {
                    exeuteSplittedRaw(true, "pwd")

                    startBranch("rebased-feature", "feature") {
                        git("merge", "master")
                    }

                    git("checkout", "feature")
                    editAndCommit(file(), 5)
                    git("merge", "rebased-feature")

                }
            }
        }
    }
})

private fun TestContext.inSamplesDirectory(block: PlainDirectory.() -> Unit) {
    PlainDirectory(File("build/samples/${description().name}"))
            .apply { cleanDirectory() }
            .run(block)
}


