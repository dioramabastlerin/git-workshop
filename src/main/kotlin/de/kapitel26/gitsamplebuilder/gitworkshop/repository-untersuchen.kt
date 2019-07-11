package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.repositoryUntersuchen() {

    createAufgabenFolge("repository-untersuchen") {

        createRepo {
            createFileAndCommit("hallo-welt")
            createDir("foo") {
                createFileAndCommit("bar")
            }
            newGit("tag release1.0")
            editAndCommit("hallo-welt", 3)
            inDir("foo") {
                editAndCommit("bar", 1)
                editAndCommit("bar", 5)
            }
            newGit("tag release1.1")
            createFileAndCommit("und-tschuess")
        }

        doc("00-intro.md") {
            markdown("""
            # Repositorys untersuchen

            Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
            untersucht zu werden. 
            """)

            inRepo {
                execute("ls -hal")
            }

            markdown("""
                
                Man sieht: Das Projekt enthält eine Datei, ein normales Unterverzeichnis
                und natürlich auch ein `.git`-Verzeichnis, welches das Repository
            """.trimIndent())
        }


        createAufgabe(
                "Commits ansehen", """
                    Sieh Dir die Commits an und lasse dabei Informationen 
                    zu Branches und Tags mit anzeigen.
        """) {
            inRepo {
                newGit("log --oneline --decorate")
            }
        }

        createAufgabe(
                "Einzelne Commits untersuchen", """
                    Zeige Details zur aktuellen Version,
                    und zur Vorgängerversion des Releases 1.0
                """) {
            inRepo {
                markdown("\n\nHier die aktuelle Version `HEAD`:")
                newGit("show")
                markdown("\n\nUnd hier kommt die 1.0:")
                newGit("show release1.0~1")
            }
        }


    }
}