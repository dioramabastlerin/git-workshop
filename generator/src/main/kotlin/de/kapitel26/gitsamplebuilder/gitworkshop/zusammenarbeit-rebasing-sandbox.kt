package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.rebasingSandbox() {
    createAufgabenFolge("rebasing") {

        createIntro(
            """Rebasing Sandbox""",
            """

            """
        ) {
            createRepo("repo-rebase") {
                createFileAndCommit("hello") { content = "hallo welt" }

                startBranch("feature") {
                    editAndCommit("hello", "Uppercase hello") { content = "Hallo Welt" }
                }

                createFileAndCommit("bar")

                createClone("../repo-merge")
            }
        }

        createAufgabe(
            "Feature-Branch per Rebase aktualiseren.",
            """
                    """
        ) {

            inRepo("repo-rebase") {
                // rebase onto master, but keep feature
                startBranch("f-tmp", "feature") {
                    git("rebase master")
                }
                git("rebase f-tmp")
                git("branch -d f-tmp")

                editAndCommit("hello", "Add !") { content = "Hallo Welt!" }


                git("switch feature")
                createFile("wurst")
                git("add wurst")
                git("commit --amend -m 'Amended'")
                git("log --graph --all --decorate --oneline")
                git("rebase master", acceptableExitCodes = setOf(1))
                bash("cat hello")

            }
        }

        createAufgabe(
            "Feature-Branch per Merge aktualiseren.",
            """
                    """
        ) {
            inRepo("repo-merge") {
                onBranch("feature") {}

                git("merge feature")

                editAndCommit("hello", "Add !") { content = "Hallo Welt!" }

                git("log --graph --all --decorate --oneline")

                git("switch feature")
                git("merge master", acceptableExitCodes = setOf(0))
                bash("cat hello")

            }
        }
    }
}
