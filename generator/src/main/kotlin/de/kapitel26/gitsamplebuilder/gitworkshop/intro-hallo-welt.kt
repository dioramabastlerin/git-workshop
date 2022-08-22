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

                ## Kurze Info zu den ersten Git-Befehlen
                
                * `git init` Erstellt ein neues Git-Repository.
                * `git add <datei(en)>` Datei(-änderungen) für das nächsten Commit hinzufügen.
                * `git commit -m <beschreibung>` Erstellt ein Commit.
                * `git log` zeigt Commits an.

            """
        )

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