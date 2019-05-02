package de.kapitel26.gitsamplebuilder

import io.kotlintest.TestContext
import io.kotlintest.specs.StringSpec
import java.io.File

class GitSampleBuilderSample : StringSpec({

    "problems with rebased commits"  {
        inSamplesDirectory {
            createRepository {

                val file = createFile()
                commit(file)


                onBranch("feature") {
                    editAndCommit(file, 5)
                }

                onBranch("master") {
                    editAndCommit(file, 1)
                }

                onBranch("feature") {
                    onBranch("rebased-feature") {
                        git("rebase", "master")
                    }

                    editAndCommit(file, 5)
                    //git("merge", "rebased-feature")
                }
            }
        }
    }

    "sandbox".config(enabled = false) {
        inSamplesDirectory {
            val serverRepo = bareRepo("myproject") {
                execute("touch myfile")
                git("add myfile")
                git("commit -m add-my-file")
            }


            val alice = serverRepo.cloneTo(dir("alice")) {
                execute("touch fileofalice")
                git("add fileofalice")
                git("commit -m fileofalice")
                git("push")
            }

            serverRepo.cloneTo(dir("bob")) {
                git("pull")
                execute("touch bobsfile")
                git("add bobsfile")
                git("commit -m bobsfile")
                git("push")
            }

            inRepo(alice) {
                show("git pull")
                show("ls -lah")
            }
        }
    }


})

private fun TestContext.inSamplesDirectory(block: Directory.() -> Unit) {
    Directory(File("build/samples/${description().name}"))
            .apply { cleanDirectory() }
            .run(block)
}


