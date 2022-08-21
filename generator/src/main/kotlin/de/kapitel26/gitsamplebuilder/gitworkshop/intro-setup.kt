package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.setup() {

    createAufgabenFolge("setup") {

        createIntro(
                """Das `git`-Kommando!""",
                """
                In der ersten Übung geht es darum,
                die (Git-) Bash-Kommandozeile und vor allem
                das `git`-Kommando kennenzulernen.

                ## Tipps
                
                * Für Windows-Nutzer:
                  - Nutzen sie die **Git-Bash**-Kommandozeile dann können sie Beispiele
                    genau wie hier angegeben ausführen.
                  - Verwenden sie '/' statt '\', für Verzeichnispfade.
                  - Wenn Sie mit der Windows-Kommandozeile `CMD` arbeiten,
                    müssen sie ggf. kleiner Änderungen vornehmen,
                    damit die Beispiele funktionieren.

                * TODO
                  - cd, ls, ll
                  - less/notepad/editor
                  - git verseion

            """
        )

        createDir("hallo") {
            createFile("README.md", "Herzlich Willkomen!")
        }

        inDir("../..") {
            createAufgabe(
                "Navigation in Übungsverzeichnisse", """
                Starte in jenem Verzeichnis, wo `build.zip` entpackt wurde.
                Navigiere in das Unterverzeichnis `aufgaben/intro-setup/hallo`
                und sieh Dir den Inhalt der dort liegenden Datei an.
                Navigiere dann wieder zurück ins Ursprungsverzeichnis.
            """) {
                bash("ls")
                inDir("aufgaben") {
                    inDir("intro-setup") {
                        inDir("hallo") {
                            bash("ls")
                            bash("cat README.md")
                        }
                    }
                }
            }
        }
        

        createAufgabe(
                "Git-Version prüfen", """
                Gib aus, welche Version von Git installiert ist.
        """) {
            bash("git version")
        }

        createAufgabe(
                "Hilfe", """
                    Lassen Sie sich die Hilfeseite zum `log`-Befehl ausgeben.
        """) {
            val output = """
                GIT-LOG(1)                                                        Git Manual                                                        GIT-LOG(1)

                NAME
                       git-log - Show commit logs
                ...
            """.trimIndent()
            log.shell("git help log", rootDir.name, output.lines(), emptyList())
        }


        createAufgabe(
                "Setup", """
                Konfigurieren Sie Benutzername und Email:
                
                    $ git config --global user.name mein-name
                    $ git config --global user.email meine-email
        """) {
            log.shell("git config --global user.name mein-name", rootDir.name, emptyList(), emptyList())
            log.shell("git config --global user.email meine-email", rootDir.name, emptyList(), emptyList())
        }
    }
}