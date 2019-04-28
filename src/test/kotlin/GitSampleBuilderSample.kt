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
                git("branch feature")

                file.edit(1..2, "on master")
                commit(file)

                git("checkout feature")

                file.edit(4, "on feature")
                commit(file)
                file.edit(5, "on feature")
                commit(file)

                git("branch feature2")
                git("rebase master")

                file.edit(4, "on other after rebase")
                commit(file)

                git("checkout feature2")

                file.edit(1, "on somewhere else")
                commit(file)

                git("merge feature2")
            }
        }
    }

    "sandbox"  {
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


