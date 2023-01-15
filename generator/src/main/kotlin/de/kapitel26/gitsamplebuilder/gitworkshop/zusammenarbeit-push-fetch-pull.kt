package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.fetchAndPull() {
    createAufgabenFolge("push-fetch-pull") {

        createIntro(
            """Fetch und Pull""",
            """

                ## Tipps

                * `git fetch` holt Änderungen, ohne zu integrieren
                * `git status` zeigt unterschiede am aktuellen Branch
                * `git branch -r` zeigt, welche Branches es Remote gibt.
                * `git pull` integriert (erst Fetch, dann Merge)
                * `git log --graph --oneline` zeigt den Graphen mit Merges
                
                ## Setup
                                  
            """
        ) {
            createRepo("blessed.git", "--bare") {

                createClone("../anderer-klon") {
                    createFileAndCommit("foo", "Initial edit before cloning")
                    createFileAndCommit("bar", "Initial edit before cloning")
                    git("push")
                }

                createClone("../mein-klon")
            }
            inRepo("anderer-klon") {
                editAndCommit("foo", 3, "First edit after cloning")
                editAndCommit("foo", 7, "Second edit after cloning")
                git("push")
            }

            inRepo("mein-klon") {
                editAndCommit("bar", 3, "My local edit")
            }

        }

        inRepo("mein-klon") {

            createAufgabe(
                "Änderungen holen", """
                    Hole die beiden neuen Commits vom `origin`-Repository,
                    ohne den lokalen `main` zu verändern.
        """
            ) {
                git("fetch")
                markdown("Die Ausgabe zeigt, dass Änderungen auf dem Branch `main` geholt wurden.")
                git("status")
            }

            createAufgabe(
                "Änderungen untersuchen", """
                    Lasse dir den Status zeigen,
                    und untersuche dann,
                    welche Commits im `main` des `origin`-Repository vorhanden sind,
                    welche im lokalen `main` noch nicht integriert wurden..
        """
            ) {
                git("status")
                markdown(
                    """
                        Der Status zeigt, dass es im Origin-Repo
                        (auf dem Branch `main`) zwei Commits gibt,
                        die wir noch nicht integriert haben.
                    """
                )
                git("log main..origin/main")
                markdown(
                    """
                        Die `..`-Notation zeigt genau jene Commits,
                        die in `origing/main` aber noch nicht in `main` enthalten sind.
                        Etwas kürzer hätte man hier auch auch `git log ..origin/main` schreiben
                        könne, da wir `main` ja gerade `HEAD` ist.
                    """
                )

            }

            createAufgabe(
                "Änderungen integrieren", """
                    Integriere die neuesten Commits vom `origin`-Repository
                    in den lokalen `main`.
        """
            ) {
                git("pull")
                git("log --graph --oneline")
            }

        }
    }
}