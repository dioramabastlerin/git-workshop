package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.commitsErstellen() {

    createAufgabenFolge("erstellen") {

        createIntro(
                """Commits erstellen""",
                """
                Hier geht es darum, ...

                ## Tipps
                
                * `git add <datei/verzeichnis>` 
                   Vor einem Commit müssen Änderungen mit `add` im Staging-Bereich 
                   (auch Index genannt) registriert werden
                * `git commit -m 'Mein Senf'` Erstellt ein Commit mit allen 
                   im Staging-Bereich registrierten Änderungen.
                * `git commit -a` Regsitriert alle Änderungen an bereits in Git versionierten 
                  Dateien im Staging-Bereich, so dass man sich den separaten
                  `add`-Aufruf sparen kann.

                # Setup
    
                Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
                bearbeitet zu werden. 

            """
        )


        createRepo {
            createFileAndCommit("hallo-welt") { content = "Hallo Welt" }
            createFileAndCommit("hello-world") { content = "Hello world!" }
        }

        inRepo {
            createAufgabe(
                    "Verzeichnisstruktur", """
                    Untersuche das Projektverzeichnis.
        """) {


                markdown("""
                Man sieht: Das Projekt enthält eine Datei, ein normales Unterverzeichnis
                und natürlich auch ein `.git`-Verzeichnis, welches das Repository beherbergt.
            """.trimIndent())
            }


            createAufgabe(
                    "Commit - geänderte Datei", """
                    1. Bearbeite die Datei `hallo-welt`, 
                       füge sie mit `git add` zum Index hinzu (Staging)
                       und erstelle ein Commit mit diesen Änderungen.
                    2. Bearbeite die Datei `hallo-welt` erneut
                       und erstelle wieder ein Commit,
                       dieses mal mal aber mit `-a`.
                    
        """) {
                inFile("hallo-welt") { content = "Hallo Welt!" }
                git("add hallo-welt")
                git("commit -m 'Erste Änderung'")
                git("show")

                markdown("Mit der Option `-a` kann man sich den `add`-Aufruf sparen:")

                inFile("hallo-welt") { content = "Hallo Welt!!" }
                git("commit -am 'Zweite Änderung'")
                git("show")
            }
        }

    }
}