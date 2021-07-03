package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.repositoryUntersuchen() {

    createAufgabenFolge("untersuchen") {

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
                * `git branch` und `git tag` listen vorhande Branches und Tags auf.
                * Mit `A..B` Adressiert man Commit, die auf dem Weg
                  von `A` nach `B` hinzukommen. 

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
                startBranch("feature-a") {
                    editAndCommit("bar", 7)
                }

                editAndCommit("bar", 1)
                git("branch some-old-branch")
                editAndCommit("bar", 5)
            }
            git("tag release1.1")
            createFileAndCommit("und-tschuess")
        }

        inRepo {
            createAufgabe(
                    "Verzeichnisstruktur", """
                    Untersuche das Projektverzeichnis.
        """) {

                bash("ls -hal")

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
                git("log --oneline --decorate")
            }

            createAufgabe(
                    "Einzelne Commits untersuchen", """
                    Zeige Details zur aktuellen Version,
                    und zur Vorgängerversion des Releases 1.0
                """) {
                markdown("\n\nHier die aktuelle Version `HEAD`:")
                git("show")
                markdown("\n\nUnd hier kommt die 1.0:")
                git("show release1.0~1")
            }

            createAufgabe(
                    "Inhalte vergangener Versionen untersuchen", """
                    Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.
                    
                    Gebe den Inhalt der Datei `bar` in diese Version aus.
                    
                    Hole diese Version in den Workspace, um sie näher zu untersuchen.
                """) {
                markdown("\n\nDiese Dateien gab es in `HEAD~1`:")
                git("ls-tree -r HEAD~1")
                markdown("\n\nUnd hier der Inhalt von `bar`:")
                git("show HEAD~1:foo/bar")
                markdown("\n\nUnd jetzt holen wir genau diese Version in den Workspace:")
                git("checkout HEAD~1")
                bash("ls -Rl")
            }

            createAufgabe(
                    "Branches und Tags", """
                    Zeige die Branches und Tags an.
                    Zeige jetzt den Commit-Graphen über alle Branches an.
                """) {
                git("branch -vv")
                git("tag")
                markdown("Im Commit-Graphen sieht man, wo die Branches und Tag stehen:")
                git("log --decorate --oneline --graph --all")
            }
        }
    }
}