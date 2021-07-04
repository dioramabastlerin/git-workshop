package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.branching() {
    createAufgabenFolge("branching") {

        createIntro(
            """Branching""",
            """

               
                ## Infos
                
                * `git branch` 

                ## Tipps
                
                * `git branch -vv`
                  
                ## Ausgangssituation

            """
        ) {
            createRepo("blessed.git", "--bare") {
                createClone("../repo")
            }

            inRepo {
                createFileAndCommit("foo", "Initial edit before cloning")
                createFileAndCommit("bar", "Initial edit before cloning")
                git("push")
            }

        }

        inRepo {

            createAufgabe(
                    "Branch erstellen",
                    """
                    Erstelle einen Branch `feature-a` und bearbeite die Datei `foo`.
                    Wechsle zurück auf den `master` und bearbeite dort `bar`.
                    Zeige den Commit-Graphen.
                    """
            ) {
                startBranch("feature-a") {
                    editAndCommit("foo", 7)
                }

                editAndCommit("bar", 3)
                git("log --all --oneline --graph --decorate")
            }

            createAufgabe(
                    "Branch mergen",
                    """
                    Merge `feature-a` auf den `master`und
                    zeige den Commit-Graphen.
                    """
            ) {
                git("merge feature-a")
                git("log --all --oneline --graph --decorate")
            }

            createAufgabe(
                    "Merge analysieren",
                    """
                    Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
                    Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
                    Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.
                    """
            ) {
                git("log HEAD^2..HEAD^1")
                git("log HEAD^1..HEAD^2")
                git("diff HEAD^2...HEAD^1")
                git("diff HEAD^1...HEAD^2")
            }


        }
    }
}