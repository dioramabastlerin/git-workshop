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
                    ohne den lokalen `master` zu verändern.
        """
            ) {
                git("fetch")
                markdown("Die Ausgabe zeigt, dass Änderungen auf dem Branch `master` geholt wurden.")
                git("status")
            }

            createAufgabe(
                "Änderungen untersuchen", """
                    Lasse dir den Status zeigen,
                    und untersuche dann,
                    welche Commits im `master` des `origin`-Repository vorhanden sind,
                    welche im lokalen `master` noch nicht integriert wurden..
        """
            ) {
                git("status")
                markdown(
                    """
                        Der Status zeigt, dass es im Origin-Repo
                        (auf dem Branch `master`) zwei Commits gibt,
                        die wir noch nicht integriert haben.
                    """
                )
                git("log master..origin/master")
                markdown(
                    """
                        Die `..`-Notation zeigt genau jene Commits,
                        die in `origing/master` aber noch nicht in `master` enthalten sind.
                        Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
                        könne, da wir `master` ja gerade `HEAD` ist.
                    """
                )

            }

            createAufgabe(
                "Änderungen integrieren", """
                    Integriere die neuesten Commits vom `origin`-Repository
                    in den lokalen `master`.
        """
            ) {
                git("pull")
                git("log --graph --oneline")
            }

        }
    }
}