package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.repositoryUntersuchen() {

    createAufgabenFolge("repository-untersuchen") {

        createIntro(
                """Repository untersuchen""",
                """
                Hier geht es darum, herauszufinden, was in einem Repository enthalten ist.

                ## Tipps
                
                * `git log` zeigt alles Commits, die im aktuellen Branch enthalten sind.
                  - `--oneline` macht die Ausgabe kompakter.
                * `git show <some-commit>` zeigt Details zu einem Commit
                * Mit `~` Adressiert man Vorgänger eines Commits, 
                  z. B. ist `HEAD~2` der Vorvorgänger von `HEAD`.

                # Setup
    
                Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
                untersucht zu werden. 

            """
        )


        createRepo {
            createFileAndCommit("hallo-welt")
            createDir("foo") {
                createFileAndCommit("bar")
            }
            git("tag release1.0")
            editAndCommit("hallo-welt", 3)
            inDir("foo") {
                editAndCommit("bar", 1)
                editAndCommit("bar", 5)
            }
            git("tag release1.1")
            createFileAndCommit("und-tschuess")
        }

        createAufgabe(
                "Verzeichnisstruktur", """
                    Untersuche das Projektverzeichnis.
        """) {

            inRepo {
                bash("ls -hal")
            }

            markdown("""
                Man sieht: Das Projekt enthält eine Datei, ein normales Unterverzeichnis
                und natürlich auch ein `.git`-Verzeichnis, welches das Repository beherbergt.
            """.trimIndent())
        }


        createAufgabe(
                "Commits ansehen", """
                    Sieh Dir die Commits an und lasse dabei Informationen 
                    zu Branches und Tags mit anzeigen.
        """) {
            inRepo {
                git("log --oneline --decorate")
            }
        }

        createAufgabe(
                "Einzelne Commits untersuchen", """
                    Zeige Details zur aktuellen Version,
                    und zur Vorgängerversion des Releases 1.0
                """) {
            inRepo {
                markdown("\n\nHier die aktuelle Version `HEAD`:")
                git("show")
                markdown("\n\nUnd hier kommt die 1.0:")
                git("show release1.0~1")
            }
        }


    }
}