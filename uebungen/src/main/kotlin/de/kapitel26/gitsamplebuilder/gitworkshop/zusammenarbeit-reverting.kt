package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.reverting() {
    createAufgabenFolge("reverting") {

        createIntro(
            """Reverting""",
            """
                Git ermöglicht es, Änderungen, die in früheren Commits gemacht wurden,
                durch ein neues Commit wieder rückgängig zu machen.
               
                ## Infos
                
                * `git revert <commit>` erstellt ein neues Commit, 
                  welches die Änderungen eines früheren Commmits wieder
                  rückgängig macht.

                * `git revert -m 1 <merge-commit>` Bei einem Merge-Commit
                  muss man zusätzlich Angeben auf welchen Merge-Parent
                  sich die Ermittlung der Änderungen beziehen soll.

                * Bei weiter zurückliegenden Commits kann es zu Konflikten kommen
                  (weil betroffene Dateien in späteren Commits weiter bearbeitet wurden).
                  Diese müssen dann wie Merge-Konflikte aufgelöst werden.  
            """
        ) {
            createRepo {
                createFileAndCommit("README.md")
                startBranch("feature") {
                    createFileAndCommit("from-feature")
                }
                git("mv README.md liesmich")
                git("commit -m 'umbenennen'")
                git("tag umbenennung")

                git("merge feature")
                git("tag feature-merge")

                onBranch("feature") {
                    editAndCommit("from-feature",1)
                }

                createAufgabe(
                        "Einzelnes Commit rückgängig machen.",
                        """
                        In der Historie wurde die Datei `README.md` in `liesmich`
                        umbenannt. Mache dies änderung rückgängig.
                        Tipp: Das Commit ist als `umbenennung` getagged.
                        """
                ) {
                    bash("ls")
                    git("revert umbenennung")
                    bash("ls")
                    
                 
                }

                createAufgabe(
                        "Merge-Commit rückgängig machen.",
                        """
                        In der Historie wurde ein Feature-Branch per Merge integriert.file . Mache dies änderung rückgängig.
                        Tipp: Das Commit ist als `feature-merge` getagged.
                        """
                ) {
                    
                    git("revert -m 1 feature-merge")
                    git("tag merge-reverted")
//                    editAndCommit("README",3)

                    git("log --oneline --graph")

                }

                createAufgabe(
                        "Der feature-Branch kann jetzt nicht erneut gemerged werden.",
                        """
                        TODO In der Historie wurde ein Feature-Branch per Merge integriert.file . Mache dies änderung rückgängig.
                        Tipp: Das Commit ist als `feature-merge` getagged.
                        """
                ) {
                    
                    git("merge feature", acceptableExitCodes = setOf(1))
                    git("merge --abort")

                }

                createAufgabe(
                        "Merge-Revert wieder rückgängig machen.",
                        """
                        TODO. In der Historie wurde ein Feature-Branch per Merge integriert.file . Mache dies änderung rückgängig.
                        Tipp: Das Commit ist als `feature-merge` getagged.
                        """
                ) {
                
                    git("revert merge-reverted")
                    git("merge feature")

                    git("log --oneline --graph")
                 
                }

            }
        }
    }
}