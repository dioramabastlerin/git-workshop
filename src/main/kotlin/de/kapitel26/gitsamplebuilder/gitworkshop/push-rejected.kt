package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.pushRejected() {
    createAufgabenFolge("push-rejected") {

        createRepo("blessed.git", "--bare")

        createClonedRepo("blessed.git", "anderer-klon") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
            git("push")
        }

        createClonedRepo("blessed.git")

        inRepo("anderer-klon") {
            editAndCommit("foo", 1)
            editAndCommit("foo", 5)
            git("push")
        }

        inRepo {
            editAndCommit("bar", 1)
        }

        createAufgabe(
                "Änderungen holen", """
                    Hole die beiden neuen Commits vom `origin`-Repository,
                    ohne den lokalen `master` zu verändern.
        """) {
            inRepo {
                git("push")
            }
        }

    }
}