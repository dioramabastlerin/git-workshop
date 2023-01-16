package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.commandline() {

    createAufgabenFolge("commandline") {

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
                    müssen sie ggf. kleinere Änderungen vornehmen,
                    damit die Beispiele funktionieren.

                * Bash-Kommandos
                  - `cd <verzeichnisname>`, wechselt in ein anderes Verzeichnis.
                  - `cd ..`, wechselt in das übergeordente Verzeichnis.
                    Eine Ebene hoch!
                  - `ls` zeigt die Namen der Dateien und Unterverzeichnisse im aktuellen Verzeichnisse.
                  - `ll` Wie `ls` nur mit mehr Details.
                  - `less`. Inhalt einer Datei anzeigen. Scrollen mit Pfeiltasten. Mit Taste `q` beenden.

                * Git-Kommandos
                  - `git version` zeigt welche Version von Git installiert ist.
                  - `git help <kommando>` zeigt Hilfe.
                  - `git config <property>` zeigt Wert aus der Konfiguration an. 
                  - `git config set --global <property> <new-value>` 
                    setzt einen Wert in der Konfiguration.

                [Kurze Intro zur Kommandozeile](../installation/kommandozeile)
            """
        ) {
            createDir("hallo") {
                createFile("herzlich-willkommen.txt", "Moin!")
            }

        }

        inDir("../..") {
            createAufgabe(
                "Navigation in Übungsverzeichnisse", """
                Starte in jenem Verzeichnis, wo `build.zip` entpackt wurde.
                Navigiere in das Unterverzeichnis `aufgaben/intro-commandline/hallo`
                und sieh Dir den Inhalt der dort liegenden Datei an.
                Navigiere dann wieder zurück ins Ursprungsverzeichnis.
            """) {
                bash("ls")
                inDir("aufgaben") {
                    inDir("intro-commandline") {
                        inDir("hallo") {
                            bash("ls")
                        }
                    }
                }
            }
        }
        
        inDir("hallo") {
            createAufgabe(
                    "Git-Version prüfen", """
                    Gib aus, welche Version von Git installiert ist.
            """) {
                bash("git version")
            }

            createAufgabe(
                    "Hilfe", """
                        Zeige die Hilfeseite zum `log`-Befehl an.
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
                    "Konfiguration von Git", """
                    Prüfe die User-Konfiguration:

                        $ git config user.name
                        $ git config user.email
                        $ git config pull.rebase
                        $ git config merge.conflictStyle
                        $ git config --global init.defaultBranch 

                    Konfiguriere Sie Benutzername und -Email, 
                    sofern noch nicht gesetzt:
                    
                        $ git config --global user.name mein-name
                        $ git config --global user.email meine-email
                    
                    Die folgenden Konfigurationen wurden beim Aufzeichnen der 
                    Musterlösung genutzt.
                    Es ist empfehlenswert sie für diesen Workshop setzen:

                        $ git config --global pull.rebase false 
                        $ git config --global merge.conflictStyle diff3
                        $ git config --global init.defaultBranch main

            """) {
                log.shell("git config --global user.name mein-name", rootDir.name, emptyList(), emptyList())
                log.shell("git config --global user.email meine-email", rootDir.name, emptyList(), emptyList())
                log.shell("git config --global pull.rebase false ", rootDir.name, emptyList(), emptyList())
                log.shell("git config --global merge.conflictStyle diff3", rootDir.name, emptyList(), emptyList())
                log.shell("git config --global init.defaultBranch main", rootDir.name, emptyList(), emptyList())   
            }

            createAufgabe(
                "⭐ Historie", """
                    Blättern sie die 🡅-Taste mehrfach und drücken dann enter,
                    um einen der vorigen Befehle erneut auszuführen.
                    Tippen sie `strg+r` und geben sie dann `conflict`ein,
                    um den Befehl zum Setzen von `merge.conflictStyle` erneut auszuführen.
                """) {
                log.shell("git config --global user.email meine-email", rootDir.name, emptyList(), emptyList())
                log.shell("git config --global merge.conflictStyle diff3", rootDir.name, emptyList(), emptyList())
            }

            // todo less 

            // dateien erstellen und bearbeiten editor konfigurieren

            // start . , pwd
        }
    }
}