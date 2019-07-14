package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.mergeConflicts() {
    createAufgabenFolge("merge-conflicts") {

        createIntro(
                """Umgang Merge-Konflikten""",
                """

                Wenn mehrere Entwickler gemeinsam an einem 
                Projekt arbeiten kommt es immer mal wieder
                zu Merge-Konflikten bei der Integration von 
                Änderungen.

                ## Tipps
                

                ## Setup
    

                ### Verzeichnisse

            """
        )

        createRepo("origin-simple-merge") {
            createFileAndCommit("frontend.js")
            createFileAndCommit("backend.java")
            createClone("../my-simple-merge") {
                editAndCommit("frontend.js", 1)
            }
            editAndCommit("backend.java", 1)
        }

        inRepo("my-simple-merge") {
            createAufgabe(
                    "Glatter Merge",
                    """
                    Just pull.
                    """
            ) {
                git("pull")
                git("show --stat")
            }
        }
    }
}

