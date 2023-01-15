package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.branchingFortgeschritten() {
    createAufgabenFolge("branching-fortgeschritten") {

        createIntro(
            """Branching (fortgeschritten)""",
            """

               
                ## Infos
                
                * `git branch` 

                ## Tipps
                
                * `git switch -c <name>` erzeugt einen neuen Branch und aktiviert 
                  diesen sogleich.
                * `git branch -vv` zeigt Details zu den lokalen Branches
                * `git switch <name>` wechselt den aktiven Branch
                * `git merge <branch>` integriert die Änderungen von `branch` und erstellt ein Commit
                   auf dem aktiven Branch.
                * 

                  
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

            inRepo("blessed.git") {
                createClone("../other") {
                    startBranch("feature-x") {
                        createFileAndCommit("datei-x")
                        editAndCommit("datei-x",3)
                        git("push origin feature-x")
                    }
                    startBranch("feature-y") {
                        createFileAndCommit("datei-y")
                        git("push origin feature-y")
                    }
                }
            }
        }

        inRepo {

            createAufgabe(
                    "Branch erstellen",
                    """
                    Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
                    und erstelle ein Commit.
                    Wechsle dann zurück auf den `main` und bearbeite dort `bar`.
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
                    Merge `feature-a` auf den `main`und
                    zeige den Commit-Graphen.
                    """
            ) {
                git("merge feature-a")
                git("log --all --oneline --graph --decorate")
            }

            createAufgabe(
                    "⭐ Merge analysieren",
                    """
                    Zeige, welche Commits vom `main` im Merge hinzugekommen sind.
                    Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
                    Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.
                    """
            ) {
                git("log HEAD^2..HEAD^1")
                git("log HEAD^1..HEAD^2")
                git("diff HEAD^2...HEAD^1")
                git("diff HEAD^1...HEAD^2")
            }

            createAufgabe(
                    "⭐ Merge analysieren",
                    """
                    Zeige, welche Commits vom `main` im Merge hinzugekommen sind.
                    Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
                    Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.
                    """
            ) {
                git("log HEAD^2..HEAD^1")
                git("log HEAD^1..HEAD^2")
                git("diff HEAD^2...HEAD^1")
                git("diff HEAD^1...HEAD^2")
            }

            createAufgabe(
                    "Remote Branches untersuchen",
                    """
                    """
            ) {
                git("branch -r -vv")
                git("fetch")
                git("branch -r -vv")
                git("log --oneline ..origin/feature-x")
                git("log --oneline ..origin/feature-y")
            }
        }
    }
}