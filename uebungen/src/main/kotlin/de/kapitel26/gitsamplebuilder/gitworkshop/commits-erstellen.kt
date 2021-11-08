package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.erstellen() {

    createAufgabenFolge("erstellen") {

        createIntro(
                """Commits erstellen""",
                """

                ## Tipps
                
                * `git add <datei/verzeichnis>` 
                   Vor einem Commit müssen Änderungen mit `add` im Staging-Bereich 
                   (auch Index genannt) registriert werden
                * `git commit -m 'Mein Senf'` Erstellt ein Commit mit allen 
                   im Staging-Bereich registrierten Änderungen.
                * `git commit -a` Regsitriert alle Änderungen an bereits in Git versionierten 
                  Dateien im Staging-Bereich, so dass man sich den separaten
                  `add`-Aufruf sparen kann.
                * `git log --follow -- <file-name>`
                   Zeigt die Historie einer Datei auch über Umbenennungen hinweg.

                # Setup
    
                Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
                bearbeitet zu werden. 

            """
        ) {
            createRepo {
                createFileAndCommit("hallo-welt") { content = "Hallo Welt" }
                createFileAndCommit("hello-world") { content = "Hello world!" }
                createFileAndCommit("datei1")
            }
        }

        inRepo {

            createAufgabe(
                    "Commit - mit Staging", """
                    Bearbeite die Datei `hallo-welt`,
                    füge sie mit `git add` zum Index hinzu (Staging)
                    und erstelle ein Commit mit diesen Änderungen.
             """) {
                inFile("hallo-welt") { content = "Hallo Welt!" }
                git("add hallo-welt")
                git("commit -m 'Erste Änderung'")
                git("show")
            }

            createAufgabe(
                    "Commit - automatisches Staging", """
                    Bearbeite die Datei `hallo-welt` erneut
                    und erstelle wieder ein Commit,
                    dieses mal mal aber mit `-a`.
             """) {

                inFile("hallo-welt") { content = "Hallo Welt!!" }
                git("commit -am 'Zweite Änderung'")
                markdown("Mit der Option `-a` kann man sich den `add`-Aufruf sparen:")
                git("log --oneline")
            }

            createAufgabe(
                    "Commit - neue Datei", """
                    Erstelle `new-world` und bestätige sie mit einem Commit.
             """) {

                createFile("new-world") { content = "New World!" }
                git("add .")
                git("commit -m 'Neue Datei'")
            }

            createAufgabe(
                    "⭐ Commit - Datei löschen", """
                    Lösche `hallo-welt` und bestätige dies per Commit.
             """) {

                bash("rm hallo-welt")
                git("commit -am 'Datei löschen'")
            }

            createAufgabe(
                    "Commit - Datei verschieben/umbenennen", """
                    Benenne die Datei `hello-world` in `renamed-world` um.
             """) {

                bash("mv hello-world renamed-world")
                git("add .")
                git("commit -m 'Umbenennen'")
                markdown("Anmerkung: Wenn wir `git mv`  statt `mv` genutzt" +
                        " hätten, dann wäre das separate `git add` nicht nötig gewesen.")
                git("log --follow --oneline -- renamed-world")
            }

            createAufgabe(
                    "⭐ Rename detection", """
                    Benenne die Datei `datei1` in `datei2` mit `git mv` um. 
                    Sorge dafür, dass die *Rename Detection* dies nicht erkennt.
             """) {

                git("mv datei1 datei2")
                inFile("datei2") { content = "Dieser Inhalt hat nichts mit dem vorigen Inhalt zu tun." }
                git("commit -am 'Böse umbenennen'")
                git("log --follow --oneline -- datei2")
            }        
        }
    }
}