package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.pushFetchPull() {
    createAufgabe("push-fetch-pull") {

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

        doc("aufgabe-1.md") {
            markdown("""
                    ## 1. Änderungen holen

                    Hole die beiden neuen Commits vom `origin`-Repository,
                    ohne den lokalen `master` zu verändern.
                """)
        }

        doc("aufgabe-2.md") {
            markdown("""
                    ## 2. Änderungen untersuchen

                    Lasse dir den Status zeigen,
                    und untersuche dann,
                    welche Commits im `master` des `origin`-Repository vorhanden sind,
                    welche im lokalen `master` noch nicht integriert wurden.
                """)
        }

        doc("aufgabe-3.md") {
            markdown("""
                    ## 3. Änderungen integrieren

                    Integriere die neuesten Commits vom `origin`-Repository
                    in den lokalen `master`.

                """)
        }

        writeDocs()
    }

    createLoesung("push-fetch-pull") {
        inRepo("mein-klon") {

            git("log --oneline --decorate -3")
            markdown("Zunächst sehen wir nur ein Commit auf dem lokalen `master`.")

            doc("aufgabe-1.md") {
                markdown("### Lösung")
                git("fetch")
                markdown("Die Ausgabe zeigt, dass Änderungen auf dem Branch `master` geholt wurden.")
                git("status")
            }

            doc("aufgabe-2.md") {

                markdown("### Lösung")
                //git("status")
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

            doc("aufgabe-3.md") {
                markdown("### Lösung")
                git("pull")
                git("log --oneline --decorate -3")
            }
        }
        writeDocs()
    }
}