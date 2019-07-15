package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

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

        createRepo("origin-for-merge-samples") {

            createFileAndCommit("frontend.js")
            createFileAndCommit("backend.kt", loadResource("merges/backend.kt"))

            createClone("../my-simple-merge") {
                editAndCommit("frontend.js", 1)
            }

            createClone("../my-conflicting-merge") {
                inFileCommit("backend.kt") { content = loadResource("merges/backend1.kt") }
            }

            editAndCommit("frontend.js", 1)
            inFileCommit("backend.kt") { content = loadResource("merges/backend2.kt") }
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

        inRepo("my-conflicting-merge") {
            createAufgabe(
                    "Nicht so glatter Merge",
                    """
                    Just pull.
                    """
            ) {
                git("pull", acceptableExitCodes = setOf(1))
                git("show --stat")
            }
        }

    }
}

class Dummy

fun loadResource(s: String): String = Dummy().javaClass.getResourceAsStream(s).bufferedReader().readText()
