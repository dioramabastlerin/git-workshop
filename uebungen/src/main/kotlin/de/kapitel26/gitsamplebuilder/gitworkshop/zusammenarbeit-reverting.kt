package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.reverting() {
    createAufgabenFolge("reverting") {

        createIntro(
            """Reverting""",
            """

               
                ## Infos
                
                * `git revert` 

                ## Tipps
                
                * `git switch -c <name>` erzeugt einen neuen Branch und aktiviert 
                  diesen sogleich.
                  
                ## Ausgangssituation

            """
        ) {
            createRepo {
                createFileAndCommit("README")
                startBranch("feature") {
                    createFileAndCommit("from-feature")
                }
                editAndCommit("README",2)
                git("merge feature")

                createAufgabe(
                        "TODO erstellen",
                        """
                        Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
                        und erstelle ein Commit.
                        Wechsle dann zurück auf den `master` und bearbeite dort `bar`.
                        Zeige den Commit-Graphen.
                        """
                ) {
                    git("revert -m 1 HEAD")
                    editAndCommit("README",3)

                    git("log --oneline --graph")

                    onBranch("feature") {
                        editAndCommit("from-feature",1)
                    }


                    git("merge feature", acceptableExitCodes = setOf(1))
                    git("merge --abort")


                    git("revert HEAD~1")
                    git("merge feature")

                    git("log --oneline --graph")
                 
                }
            }
        }
    }
}