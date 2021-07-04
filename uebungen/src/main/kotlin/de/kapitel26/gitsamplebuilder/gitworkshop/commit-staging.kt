package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.staging() {

    createAufgabenFolge("staging") {

        createIntro(
                """Staging""",
                """
                Es geht darum, den *Stage-Bereich*
                (auch Index genannt)

                ## Tipps
                
                * `git add <datei/verzeichnis>` 
                   überträgt die Stand einer Datei in den Stage-Bereich.
                *  Ändert man eine Datei nach dem `add`, hat sie
                   im Workspace einen anderen Stand als in der Stage.
                * `git status`, `git diff` und `git diff --staged` zeigen dies.
                * `git restore --staged <file>` nimmt ein Staging zurück.
                

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
                    Füge sie dann zu Stage-Bereich hinzu.
                    Ersetze dann `doof` durch `toll`.
                    Lasse dir den Status und die Diffs
                    für Workspace und Stage zeigen.
             """) {
                inFile("demo") {
                    content = "Git\n" +
                            "ist\n" +
                            "doof.\n"
                }
                git("add demo")
                inFile("demo") {
                    content = "Git\n" +
                            "ist\n" +
                            "toll.\n"
                }
                git("status")
                git("diff")
                markdown("Man sieht, dass der Stage-Bereich ein anderes Diff hat, als der Workspace.")
                git("diff --staged")
            }

            createAufgabe(
                    "Restore", """
                    Zurücknehmen eines Stagings. 
             """) {
                git("restore --staged demo")
                git("status")
                git("diff")
                markdown("Nach dem zurücknehmen ist der Stage-Bereich wieder leer.")
                git("diff --staged")
            }
        }
    }
}