package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.staging() {

    createAufgabenFolge("staging") {

        createIntro(
                """Staging""",
                """
                Es geht um den *Stage-Bereich* (auch Index genannt).
                Änderungen (bearbeitete, neue oder gelöschte Dateien) werden nur dann
                in ein Commit übernommen, wenn sie vorher im *Stage-Bereich*
                registriert werden, z.B. mit `add`

                ## Tipps
                
                * `git add <datei/verzeichnis>` 
                   überträgt den aktuellen Stand einer Datei in den Stage-Bereich.
                *  Ändert man eine Datei nach dem `add`, hat sie
                   im Workspace einen anderen Stand als in der Stage.
                * `git status`, `git diff` und `git diff --staged` zeigen dies.
                * `git restore --staged <file>` nimmt ein Staging zurück.
                * `git restore <file>` stellt eine Datei im Workspace wieder her.
                   **Achtung**: Die lokale Änderungen werden dabei überschreiben!
                

                # Setup
    
                Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
                bearbeitet zu werden. 

            """
        ) {
            createRepo {
                createFileAndCommit("demo") { content = "Fit\nist\ndoof.\n" }
            }
        }




        inRepo {

            createAufgabe(
                    "Staging", """
                    Ersetze in der Datei `demo`,
                    `Fit` durch `Git`.
                    Füge sie dann zum Stage-Bereich hinzu.
                    Ersetze dann `doof` durch `toll`.
                    Lasse dir den Status und die Diffs
                    für Workspace und Stage zeigen.
             """) {
                markdown("Ersetze `Fit` durch `Git`.")
                inFile("demo") {
                    content = "Git\n" +
                            "ist\n" +
                            "doof.\n"
                }
                git("add demo")
                markdown("Ersetze `doof` durch `toll`.")
                inFile("demo") {
                    content = "Git\n" +
                            "ist\n" +
                            "toll.\n"
                }
                git("status")
                markdown("Die Datei `demo` wird sowohl in `Changes to be committed:` als auch in `Changes not staged for commit` geführt.")
                git("diff")
                markdown("Man sieht, dass der Stage-Bereich ein anderes Diff hat, als der Workspace.")
                git("diff --staged")
            }

            createAufgabe(
                    "Restore - Staging zurücknehmen", """
                    Die letzte Änderung soll nicht in das nächste Commit übernommen werden,
                    nehme sie zurück. 
             """) {
                git("restore --staged demo")
                git("status")
                git("diff")
                markdown("Nach dem zurücknehmen ist der Stage-Bereich wieder leer.")
                git("diff --staged")
            }

            createAufgabe(
                    "Restore - Datei wiederherstellen", """
                    Die letzte Änderung soll nicht in das nächste Commit übernommen werden,
                    nehme sie zurück. 
             """) {
                git("restore demo")
                git("status")
                git("diff")
                git("diff --staged")
                markdown("Jetz sind die Änderungen ganz weg.")
            }
        }
    }
}