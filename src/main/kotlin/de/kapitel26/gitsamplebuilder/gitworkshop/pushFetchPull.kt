package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.pushFetchPull() {
    createAufgabenFolge("push-fetch-pull") {

        createRepo("blessed.git", "--bare")

        createClonedRepo("blessed.git", "anderer-klon") {
            createFileAndCommit("foo", "Initial edit before cloning")
            git("push")
        }

        createClonedRepo("blessed.git", "mein-klon")

        inRepo("anderer-klon") {
            editAndCommit("foo", 3, "First edit after cloning")
            editAndCommit("foo", 7, "Second edit after cloning")
            git("push")
        }

        createAufgabe(
                "Änderungen holen", """
                    Hole die beiden neuen Commits vom `origin`-Repository,
                    ohne den lokalen `master` zu verändern.
        """) {
            inRepo("mein-klon") {
                git("fetch")
                markdown("Die Ausgabe zeigt, dass Änderungen auf dem Branch `master` geholt wurden.")
                git("status")
            }
        }

        createAufgabe(
                "Änderungen untersuchen", """
                    Lasse dir den Status zeigen,
                    und untersuche dann,
                    welche Commits im `master` des `origin`-Repository vorhanden sind,
                    welche im lokalen `master` noch nicht integriert wurden..
        """) {
            inRepo("mein-klon") {
                git("status")
                markdown("""
                        Der Status zeigt, dass es im Origin-Repo
                        (auf dem Branch `master`) zwei Commits gibt,
                        die wir noch nicht integriert haben.
                    """)
                git("log master..origin/master")
                markdown("""
                        Die `..`-Notation zeigt genau jene Commits,
                        die in `origing/master` aber noch nicht in `master` enthalten sind.
                        Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
                        könne, da wir `master` ja gerade `HEAD` ist.
                    """)

            }
        }

        createAufgabe(
                "Änderungen integrieren", """
                    Integriere die neuesten Commits vom `origin`-Repository
                    in den lokalen `master`.
        """) {
            inRepo("mein-klon") {
                git("pull")
                git("log --graph --oneline")
            }
        }

    }
}