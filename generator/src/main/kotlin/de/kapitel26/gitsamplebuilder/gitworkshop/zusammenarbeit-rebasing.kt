package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.rebasing() {
    createAufgabenFolge("rebasing") {

        createIntro(
            """Rebasing""",
            """

                
                Rebasing ist, neben dem Merging, eine weitere Möglichkeit,
                Änderung zu integrieren.

                ## Infos
                
                * `git rebase` 

                ## Tipps
                
                * `git rebase`
                  
                ## Ausgangssituation

            """
        ) {
            createRepo {

                createFileAndCommit("foo")
                createFileAndCommit("bar")

                startBranch("feature") {
                    editAndCommit("foo", 3, "Feature anfangen.")
                    editAndCommit("foo", 5, "Feature weitermachen.")
                }

                editAndCommit("bar", 1, "Neuerung auf dem main")

                git("checkout feature")
            }
        }

        inRepo {
            createAufgabe(
                "Feature-Branch per Rebase aktualiseren.",
                """
                    Auf dem main gibt es Neuerungen.
                    
                    Lasse Dir den Commit-Graphen über alle Branches zeigen.
 
                    Aktualisiere Deinen Feature-Branch.
                    
                    Lasse Dir den Commit-Graphen über alle Branches zeigen.
                    """
            ) {

                git("log --graph --all --decorate --oneline")

                git("rebase main")

                git("log --graph --all --decorate --oneline")
            }
        }
    }
}
