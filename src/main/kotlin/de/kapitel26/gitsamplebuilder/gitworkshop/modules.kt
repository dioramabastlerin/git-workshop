package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.modules() {
    createAufgabenFolge("modules") {

        createRepo("mod-a.git", "--bare") { createClone("../mod-a") }
        createRepo("mod-b.git", "--bare") { createClone("../mod-b") }

        inRepo("mod-a") {
            createFileAndCommit("anton")
            git("push")
        }

        inRepo("mod-b") {
            createFileAndCommit("berta")
            git("push")
        }

        createAufgabe(
                "Subtree1",
                """
                    TODO.
                    """
        ) {
            createRepo("subtrees") {
                createFileAndCommit("README")
                git("subtree add --prefix=mod-a ../mod-a.git master")
                git("subtree add --prefix=mod-b ../mod-b.git master")
                git("ls-tree -r HEAD")
            }
        }

        createAufgabe(
                "Subtree2",
                """
                    TODO.
                    """
        ) {
            inRepo("subtrees") {
                editAndCommit("mod-a/anton", 3)
                git("show --stat ")
                git("subtree push --prefix=mod-a ../mod-a.git master")
            }

            inRepo("mod-a") {
                git("pull")
                // git("show --stat ")
            }
        }

        createAufgabe(
                "Subtree3",
                """
                    TODO.
                    """
        ) {
            inRepo("mod-b") {
                editAndCommit("berta", 7)
                // git("show --stat ")
            }

            inRepo("subtrees") {
                git("subtree pull --prefix=mod-b ../mod-b.git master")
                // git("show --stat ")
            }
        }
    }
}
