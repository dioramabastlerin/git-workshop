package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.staging() {

    createAufgabenFolge("staging") {

        createIntro(
                """Staging""",
                """
                Es geht um den *Stage-Bereich* (auch Index genannt).
                Jede Änderung (bearbeitete, neue oder gelöschte Dateien) 
                wird muss als "Snapshot" im Stage-Bereich registriert werden
                (z.B. mit `git add`),
                bevor Sie in ein Commit übernommen werden kann.

                ## Tipps
                
                * `git add <datei/verzeichnis>` 
                   überträgt den aktuellen Stand einer Datei in den Stage-Bereich.
                *  Ändert man eine Datei nach dem `add`, hat sie
                   im Workspace einen anderen Stand als in der Stage.
                * `git status`, `git diff` und `git diff --staged` zeigen dies.
                * `git restore --staged <file>` nimmt ein Staging zurück.
                * `git restore <file>` stellt eine Datei im Workspace wieder her.
                   **Achtung**: Die lokale Änderungen werden dabei überschreiben!
                * mit `-s <revision>` können auch beliebige andere Stände von Dateien und Verzeichnisse
                  geholt werden.
                        
                # Setup
    
                Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
                bearbeitet zu werden. 

            """
        ) {
            createRepo {
                createFileAndCommit("demo") { content = "Fit\nist\ndoof.\n" }
                createFileAndCommit("beispiel", message = "dazwischen") {  content = "So war es zuerst." }
                editAndCommit("beispiel", message = "dazwischen") { content = "Dann kam das mit dem Mittelteil." }
                editAndCommit("beispiel", message = "später") { content = "Und so endete es." }

                createDir("ufer") { 
                    createDir("west") {
                        createFile("👨‍🌾")
                        createFile("🥬")
                        createFile("🐐")
                        createFile("🐺")
                    }
                    createDir("ost")
                }
                git("add ufer")
                git("commit -m'Starte spiel'")
                inDir("ufer") {
                    listOf(
                        "west/🐐 west/👨‍🌾 ost/",
                        "ost/👨‍🌾 west/",
                        "west/🐺 west/👨‍🌾 ost/",
                        "ost/🐐 ost/👨‍🌾 west/",
                        "west/🥬 west/👨‍🌾 ost/",
                        "ost/👨‍🌾 west/",
                        "west/🐐 west/👨‍🌾 ost/"
                    ).forEachIndexed { i, s ->
                        git(" mv $s")
                        git("commit -am 'Zug ${i+1}'")    
                    }
                }

            }
        }




        inRepo {

            createAufgabe(
                    "Staging", """
                    Ersetze in der Datei `demo`,
                    `Fit` durch `Git`.
                    Füge sie dann zum Stage-Bereich hinzu.
                    Ersetze dann `doof` durch `toll`.
                    Lasse dir den Status und die Diffs
                    für Workspace und Stage zeigen.
             """) {
                markdown("Ersetze `Fit` durch `Git`.")
                inFile("demo") {
                    content = "Git\n" +
                            "ist\n" +
                            "doof.\n"
                }
                git("add demo")
                markdown("Ersetze `doof` durch `toll`.")
                inFile("demo") {
                    content = "Git\n" +
                            "ist\n" +
                            "toll.\n"
                }
                git("status")
                markdown("Die Datei `demo` wird sowohl in `Changes to be committed:` als auch in `Changes not staged for commit` geführt.")
                git("diff")
                markdown("Man sieht, dass der Stage-Bereich ein anderes Diff hat, als der Workspace.")
                git("diff --staged")
            }

            createAufgabe(
                    "Restore - Staging zurücknehmen", """
                    Die letzte Änderung soll doch noch nicht in das nächste Commit übernommen werden,
                    nehme sie zurück. 
             """) {
                git("restore --staged demo")
                git("status")
                git("diff")
                markdown("Nach dem zurücknehmen ist der Stage-Bereich wieder leer.")
                git("diff --staged")
            }

            createAufgabe(
                    "Restore - Änderung ganz zurücknehmen", """
                    Die Änderungen an demo sollen ganz zurückgenommen werden.
                    Lasse Dir nachher Status und Diffs anzeigen.
             """) {
                git("status")
                git("restore demo")
                git("status")
                                git("diff")
                git("diff --staged")
                markdown("Jetzt sind die Änderungen ganz weg.")

            }

            createAufgabe(
                    "⭐ Restore - Zurückholen älterer Datei- und Verzeichnisversionen", """
                    Im Folder `ufer` wurde ein Spiel gespielt.
                    Stelle die Spielstände nach, 
                    indem Du `restore` auf das `ufer`-Verzeichnis anwendest.
                    
                    Tipp: `ll ufer/*` zeigt die Verzeichnisse des Spiels.
                    
                    Tipp: Beim `restore` werden unversionierte Dateien nicht abgeräumt.
                    Man kann sie mit dem `clean`-Befehl abräumen.
             """) {
                git("log --oneline -- ufer/")
                (1..8).forEach { i ->
                    markdown("Zug $i")
                    git("stash -u")
                    git("restore -s HEAD~${8-i} ufer")
                    ll("ufer/*")               
                }
            }
        }
    }
}