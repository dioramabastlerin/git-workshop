package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.repositoryLog() {

    createAufgabenFolge("log") {

        createIntro(
            """Repository - Log""",
            """
                Das Log repräsentiert die Geschichte des Projekts
                als Folge von Commits.
                Jedes Commit repräsentiert einen Stand aller Dateien des Projekts.
                Hier wird geübt, das Log zu Untersuchen und zu Lesen.


                ## Tipps
                
                * `git log` zeigt alles Commits, die im aktuellen Branch enthalten sind.
                  - `--oneline` macht die Ausgabe kompakter.
                  - `--stat` zeigt wie viele Dateien in welcher Date geändert wurden.
                * `git show <some-commit>` zeigt Details zu einem Commit
                * Mit `~` Adressiert man Vorgänger eines Commits, 
                  z. B. ist `HEAD~2` der Vorvorgänger von `HEAD`.
                * `git ls-tree -r <commit>` listet alles Dateien auf, die im angegebenen
                  Commit versioniert sind.
                * Mit `blame` findet man heraus,in welchen Commit Zeilen zuletzt bearbeitet wurden.
                  - `-M` ermittelt Verschiebungen innerhalb einer Datei. 
                  - `-w` erkennt Zeilen wieder, auch wenn Whitespacing verändert wurde.
                  - `--show-number` zeigt vorherige Zeilennummern.
                  - `-C` ermittelt Kopien/Verschiebungen aus Dateien im selben  Commit, in dem die Zeile bearbeitet wurde,
                    `-C -C -C` sogar aus beliebigen Dateien.

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
                "Verzeichnisstruktur", """
                    Untersuche das Projektverzeichnis.
                    Welche Dateien gibt es im Workspace? Welche Verzeichnisse?
                    Wo liegt das Repository?
        """
            ) {

                ll()
                ll("foo")
                ll(".git")

                markdown("""
                    Man sieht: Das Projekt enthält einige Dateien, ein Unterverzeichnis
                    und natürlich auch ein `.git`-Verzeichnis, welches das Repository beherbergt.
                """.trimIndent())
            }


            createAufgabe(
                "Commits ansehen", """
                    Sieh Dir die Commits. 
                    Achte dabei auf die angezeigten Branches und Tags.
                """
            ) {
                git("log --oneline")
            }


            createAufgabe(
                "⭐ Commits ansehen: Datei-Statistik", """
                    Sieh Dir die Commits an. 
                    Lase dir dabei die Statistik anzeigen, 
                    d.h. wie viele Zeilen in welcher Datei geändert wurden.
                """
            ) {
                git("log --stat")
            }


            createAufgabe(
                "Einzelne Commits untersuchen", """
                    Zeige Details zur aktuellen Version,
                    und zur Vorgängerversion des Releases 1.0
                """
            ) {
                markdown("\n\nHier die aktuelle Version `HEAD`:")
                git("show")
                markdown("\n\nUnd hier kommt die 1.0:")
                git("show release1.0~1")
            }

            createAufgabe(
                "Inhalte vergangener Versionen untersuchen", """
                    Lasse Dir anzeigen welche Dateien es im vorigen Commit gab.
                    
                    Gebe den Inhalt der Datei `bar` so aus,  wie er im vorigen Commit war.
                """
            ) {
                markdown("\n\nDiese Dateien gab es in `HEAD~1`:")
                git("ls-tree -r HEAD~1")
                markdown("\n\nUnd hier der Inhalt von `bar`:")
                git("show HEAD~1:foo/bar")
            }


            createAufgabe(
                "⭐ Herkunft von Zeilen ermitteln", """
                    Es geht darum für die Datei `nachher` Folgendes zu ermitteln:

                    * Für jede Zeile zeigen, in welchem Commit sie zuletzt bearbeitet wurde.
                    * Innerhalb der Datei wurden Zeilen verschoben. Welche?
                    * Es wurden auch Zeilen aus anderen Dateien verschoben und kopiert. Welche?
                """
            ) {
                git("blame nachher -s -w")
                markdown("Man sieht, in welchem Commit die Zeilen zuletzt bearbeitet wurden, auch über Umbenennungen hinweg.")
                git("blame nachher -s -w -M --show-number")
                markdown("Die Zeilennummern zeigen, welche Zeilen verschoben wurden.")
                git("blame nachher -s -w -M -C")
                markdown("Hier sieht eine Verschiebung aus der Datei `restaurant`.")
                git("blame nachher -s -w -M -C -C -C")
                markdown("Hier sieht man, dass Inhalte aus einer anderen Datei `foo/bar` kopiert wurden.")
            } 
       }
    }
}