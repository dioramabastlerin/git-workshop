package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.halloWelt() {

    createAufgabenFolge("hallo-welt") {

        createIntro(
                """Hallo Git!""",
                """
                Hier geht es darum, ein Gefühl dafür zu bekommen,
                wie die Git-Kommandozeile funktioniert.
                Führen sie die vorgegebenen Kommandos aus und schauen Sie,
                was passiert.
                Was die Kommandos genau tun, erfahren Sie im Verlauf des Seminars.

                ## Tipps
                
                * Für Windows Nutzer:
                  - Nutzen sie die **Git-Bash**-Kommandozeile dann können sie Beispiele
                    genau wie hier angegeben ausführen.
                  - Verwenden sie '/' statt '\', für Verzeichnispfade.
                  - Wenn Sie mit der Windows-Kommandozeile CMD arbeiten,
                    müssen sie ggf. kleiner Änderungen vornehmen,
                    damit die Beispiele funktionieren.

            """
        )

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


        createAufgabe(
                "Das erste Repo", """
                Erstellen sie ihr erstes Repository mit den folgenden Befehlen:
                
                    $ git init myrepo
                    $ cd myrepo
                    $ echo 'welt' >hallo    # erzeugt eine Datei
                    $ git add hallo
                    $ git commit -m 'Hallo Welt!'
                    $ git log
        """) {
            createRepo("myrepo") {
                bash("echo 'welt' >hallo")
                git("add hallo")
                git("commit -m 'Hallo Welt!'")
                markdown("Glückwunsch: Sieh sehen Ihr erstes Commit in Ihrem ersten Git-Repository!")
                git("log")
            }
        }

    }
}