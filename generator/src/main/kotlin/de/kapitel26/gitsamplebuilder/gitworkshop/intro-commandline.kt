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
                  - `pwd` zeigt das aktuelle Arbeitsverzeichnis an. Auf Windows: `pwd -W` (Großbuchstabe)

                * Git-Kommandos
                  - `git version` zeigt welche Version von Git installiert ist.
                  - `git help <kommando>` zeigt Hilfe.
                  - `git config <property>` zeigt Wert aus der Konfiguration an. 
                  - `git config set --global <property> <new-value>` 
                    setzt einen Wert in der Konfiguration.
                  - `git config core.editor notepad` konfiguriert Notepad als Editor für Git.
                  - Wenn die Ausgabe mehr Zeilen hat, als Terminalfenster hoch ist,
                    wird die Ausgabe in einem Viewer (`less`) dargestellt.
                    Man kann dann mit Pfeiltasten rauf- und runter scrollen.
                    Den `less` modus beendet man mit der Taste `q`.

                * Befehl `start <file>` (Ubuntu: `xdg-open`, Mac Os: `open`) 
                  öffnet die angegebene Datei mit der verknüpften Standardanwendung.
                  `start .` öffnet den Datei-Explorer im aktuellen Verzeichnis.

                [Kurze Intro zur Kommandozeile](../installation/kommandozeile)
            """
        ) {
            createDir("hallo") {
                createFile("herzlich-willkommen.txt", "Moin!")
            }

            createRepo() {
                createFileAndCommit("some-file.txt")
                (1..99).forEach {
                    editAndCommit("some-file.txt", message="Edit nr ${it}") { edit(0, content = "Editet in Commit ${it}")} 
                }
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

        }

        inRepo() {
            createAufgabe(
                    "`less` und lange Ausgaben", """
                    Wenn Sie `git log` ausführen, sollen 99 Commits angezeigt werden.
                    Weil diese nicht in ein Terminalfenster passt,
                    wird der `less`-Viewer geöffnet. Schliessen sie ihn.
                    Nutzen sie dann `less some-file.txt` um eine Datei im `less`-Modus anzusehen.
            """) {
                val output = """
                    commit 5e14e1dc688e7a2cd02c9ccad3dedf397d407e2e (HEAD -> main)
                    Author: bjoern <kapitel26blog@gmail.com>
                    Date:   Thu Jul 29 00:00:00 2021 +0000
                    
                        : Edit file some-file.txt at line 3 on branch main by bjoern.
                    
                    commit 41984e9ac879b9b56c8e91228a8d5887bca228fd
                    Author: bjoern <kapitel26blog@gmail.com>
                    Date:   Thu Jul 29 00:00:00 2021 +0000
                    
                        : Edit file some-file.txt at line 3 on branch main by bjoern.
                    
                    commit 99399d263ccc8fe4a1bc59a49c93147b17115518
                    Author: bjoern <kapitel26blog@gmail.com>
                    Date:   Thu Jul 29 00:00:00 2021 +0000
                    
                        : Edit file some-file.txt at line 3 on branch main by bjoern.
                    
                    commit df80cb240781a015f2f0ad62a48fc42964fdfe8b
                    Author: bjoern <kapitel26blog@gmail.com>
                    Date:   Thu Jul 29 00:00:00 2021 +0000
                    
                        : Edit file some-file.txt at line 3 on branch main by bjoern.
                    :
                """.trimIndent()
                log.shell("git log", rootDir.name, output.lines(), emptyList())
                markdown("Quit with `q`")
                bash("cat some-file.txt", commandRepresentation="less some-file.txt")
                markdown("Quit with `q`")
            }

            createAufgabe(
                    "Arbeitsverzeichnis anzeigen", """
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
            
            createAufgabe(
                "⭐ Git-Editor konfigurieren", """
                Konfigurieren Sie einen Editor für git.
                [Tipps dazu](https://git-scm.com/book/en/v2/Appendix-C%3A-Git-Commands-Setup-and-Config).f
                Testen Sie dann mit `git config -e`, ob es geklappt hat.
                """) {
                    log.shell("git config --global core.editor notepad", rootDir.name, emptyList(), emptyList())
                    log.shell("git config -e", rootDir.name, emptyList(), emptyList())
            }

            createAufgabe(
                "⭐ Arbeitsverzeichnis", """
                Geben Sie aus, in welchem Arbeitzverzeichnis Sie sich gerade befinden.
                Für Windows-User: Testen sie den Befehl auch mit der Option `-W`.
                """) {
                bash("pwd")
            }
            
            createAufgabe(
                "⭐ Anwendungen öfnnen", """
                Öffnen Sie die Datei `some-file.txt` mit der Standaranwendung.
                Öffnen Sie einen Datei-Explorer im aktuellen Arbeitsverzeichnis.
                """) {
                    log.shell("start some-file.txt", rootDir.name, emptyList(), emptyList())
                    log.shell("start .", rootDir.name, emptyList(), emptyList())
            }
        }

    }
}