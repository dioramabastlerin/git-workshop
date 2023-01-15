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
                * `git branch` und `git tag` listen vorhandene Branches und Tags auf.
                * Mit `git switch <branch-name>` kann man auf andere Branches wechseln.
                * Mit `git switch --detach <commit>` kann man auf beliebige Versionen wechseln.
                * Mit `blame` findet man heraus,in welchen Commit Zeilen zuletzt bearbeitet wurden.
                  - `-M` ermittelt Verschiebungen innerhalb einer Datei. 
                  - `-w` erkennt Zeilen wieder, auch wenn Whitespacing verändert wurde.
                  - `--show-number` zeigt vorherige Zeilennummern.
                  - `-C` ermittelt Kopien/Verschiebungen aus Dateien im selben  Commit, in dem die Zeile bearbeitet wurde,
                    `-C -C -C` sogar aus beliebigen Dateien.
                * Mit `git restore -s <commit> -- <datei-oder-pfad>` kann man *Inhalte* beliebiger Versionen 
                  von Dateien/Pfaden in den Workspace holten. Es wird dabei nicht auf das angegebenen Commit
                  gewechselt, sondern nur Dateiinhalte in den Workspace geholt. Die betroffenen Dateien 
                  werden als `modified` angezeigt und können Commited werden.
                   
                # Setup
    
                Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
                untersucht zu werden. 

            """
        ) {


            createRepo {
                createFileAndCommit("hallo-welt") { content = "Hallo Welt!" }
                createDir("foo") {
                    createFileAndCommit("bar") {
                        edit(1, content = "Eine wirklich ziemlich lange Zeile in der Datei 'bar'")
                    }
                    createFileAndCommit("vorher", "Beginne mit leerer Datei") {
                        content = ""
                    }
                    editAndCommit("vorher", "Ergänze zwei zeilen") {
                        edit(0, content = "Diese Zeilen wurden also ganz am Anfang geschrieben.")
                        edit(1, content = "Und das ist wohl doch sehr lange her. Wie man sieht.")
                    }
                }

                git("mv foo/vorher nachher")
                git("commit -m 'Benenne die Datei im'")

                editAndCommit("nachher", "Ergänze eine Zeile") {
                    edit(2, content = "Nach der Umbenennung")
                }

                editAndCommit("nachher", "Kopiere eine Zeile aus 'bar'") {
                    edit(3, content = "Eine wirklich ziemlich lange Zeile in der Datei 'bar'")
                }

                createFileAndCommit("restaurant") {
                    content = "Eine sehr lange Zeile aus 'restaurant', die verschoben wird."
                }

                inFile("restaurant") {
                    content = ""
                }
                inFile("nachher") {
                    edit(4, content = "Eine sehr lange Zeile aus 'restaurant', die verschoben wird.")
                    edit(5, content = "Und eine, die nichts damit zu tun hat.")
                }
                git("commit -am 'Verschiebe eine  Zeile'")
                editAndCommit("nachher", "Noch ein paar neue Zeilen") {
                    edit(6, content = "Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.")
                    edit(7, content = "dazwischen.")
                    edit(8, content = "Ende")                    
                }
                editAndCommit("nachher", "Eine Zeile verschieben") {
                    edit(6, content = "dazwischen.")
                    edit(7, content = "Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.")
                    edit(8, content = "Ende")                    
                }

                git("tag release1.0")  
                editAndCommit("hallo-welt") { content = "Hello World!" }
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

        }

        inRepo {


            createAufgabe(
                "Inhalte vergangener Versionen untersuchen", """
                    Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.
                    
                    Gebe den Inhalt der Datei `bar`,  wie er im vorigen Commit war. aus.
                    
                    Wechsle zum vorigen Commit, und untersuche, wie der Workspace dannn aussieht.
                    Wechsle dann wieder auf `main` zurück.
                """
            ) {
                markdown("\n\nDiese Dateien gab es in `HEAD~1`:")
                git("ls-tree -r HEAD~1")
                markdown("\n\nUnd hier der Inhalt von `bar`:")
                git("show HEAD~1:foo/bar")
                markdown("\n\nUnd jetzt holen wir genau diese Version in den Workspace:")
                git("switch --detach HEAD~1")
                ll()
                git("switch main")
            }

            createAufgabe(
                "Branches und Tags", """
                    Zeige die Branches und Tags an.
                    Zeige jetzt den Commit-Graphen über alle Branches an.
                """
            ) {
                git("branch -vv")
                git("tag")
                markdown("Im Commit-Graphen sieht man, wo die Branches und Tag stehen:")
                git("log --decorate --oneline --graph --all")
            }

           
            createAufgabe(
                "⭐ Hole alten Stand einer einzelnen Datei zurück.", """
                    Die Datei `hallo-welt` wurde nach dem `release1.0` bearbeitet.
                    Dem Kunden gefällt das nicht. Stelle den alten Zustand mit
                    einem neuen Commit wieder her. 
                """
            ) {
                markdown("In `release1.0` sah es os aus:")
                git("show release1.0:hallo-welt")
                markdown("Jetzt sieh es so aus:")
                git("show HEAD:hallo-welt")
                markdown("Gezieltes zurückholen:")
                git("restore -s release1.0 hallo-welt")
                git("commit -am 'Zurückgeholt'")
                markdown("Im Commit-Graphen sieht man, wo die Branches und Tag stehen:")
                markdown("Jetzt sieh es so wieder so aus:")
                git("show HEAD:hallo-welt")
            }

       }
    }
}