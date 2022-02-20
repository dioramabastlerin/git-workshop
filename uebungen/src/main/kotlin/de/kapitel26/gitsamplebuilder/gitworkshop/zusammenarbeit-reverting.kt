package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.reverting() {
    createAufgabenFolge("reverting") {

        createIntro(
            """Reverting""",
            """
                Git ermöglicht es Änderungen, 
                die in einem früheren Commits gemacht wurden,
                durch ein neues Commit wieder rückgängig zu machen.
               
                ## Infos
                
                * `git revert <commit>` erstellt ein neues Commit, 
                  welches die Änderungen eines früheren Commmits wieder
                  rückgängig macht.

                * Bei einem Merge-Commit
                  muss man zusätzlich Angeben auf welchen Merge-Parent
                  sich die Ermittlung der Änderungen beziehen soll:<br/>
                  `git revert -m 1 <merge-commit>` 

                * Bei weiter zurückliegenden Commits kann es zu Konflikten kommen
                  (weil betroffene Dateien in späteren Commits weiter bearbeitet wurden).
                  Diese müssen dann wie bei Merge-Konflikten aufgelöst werden.

                ## Zum Beispielsetup
                
                Im Beispielrepository wurde eine Datei umbennant,
                das entsprechende Commit ist mit `umbenennung` getagged.

                Außerdem wurde ein Feature-Branch per Merge integriert.
                Das Merge-Commit ist mit `feature-merge` getagged.

                Beide Änderungen sollen zurückgenommen werden.
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
                    editAndCommit("from-feature",1, message = "Weiterentwicklung")
                }
            }
        }

        inRepo{
            createAufgabe(
                    "Einzelnes Commit rückgängig machen.",
                    """
                    In der Historie wurde die Datei `README.md` in `liesmich`
                    umbenannt. Mache diese Änderung rückgängig.
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
                    Tipp: Das Merge-Commit ist als `feature-merge` getagged. 
                    Tipp: Am verschinden der Datei `from-feature` kann man den Erfolg erkennen.
                    """
            ) {
                bash("ls")
                
                git("revert -m 1 feature-merge")

                bash("ls")
                git("tag merge-reverted")
            }

            createAufgabe(
                    "Feature-Branch kaputt!?",
                    """
                    Ein Revert wird oft genutzt, um ein Feature kurzfristig zurückzunehmen,
                    z. B. wegen eine Produktionsproblems.
                    Später möchte man den Feature-Branch korrigieren und dann erneut integrieren. 
                    Das geht nicht so ohne weiteres.

                    Im Beispiel hat der Branch `feature` eine Weiterentwicklung erfahren.
                    Versuche ihn erneut zu Mergen.
                    Untersuche die Fehlermeldung und den Commit-Graphen.
                    Tipp: Das gescheiterte Merge kann mit `git merge --abort` abgebrochen werden.
                    """
            ) {
                
                git("merge feature", acceptableExitCodes = setOf(1))
                git("merge --abort")
                git("log --oneline --graph")
                markdown("""
                    Das Merge scheitert, weil Git Commits,
                    die schon in der Historie enthalten sind,
                    nicht erneut merged.
                    Dies betrifft im Beispiel jenes Commit, 
                    das die Datei `from-feature` erzeugt.
                    Es ist in der Historie enthalten und
                    in einem späteren Commit (Dem Revert) wurde die Datei gelöscht.
                    Der Mergekonflikt meldet also, dass die Datei auf der einen
                    Seite bearbeitet und auf der Anderen gelöscht wurde.
                """)
            }

            createAufgabe(
                    "Merge-Revert wieder rückgängig machen.",
                    """
                    Der Trick ist, das Revert-Commit selbst zu reverten,
                    dann sind jene Änderungen wieder da,
                    auf denen die Weiterentwicklung des Features basiert.

                    Tipp: Das Commit ist als `feature-merge` getagged.
                    """
            ) {
                git("revert merge-reverted")
                git("merge feature")

                git("log --oneline --graph")
                git("log --oneline from-feature")
            }
        }
    }
}