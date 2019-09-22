package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.cloning() {
    createAufgabenFolge("klonen") {

        createRepo("myfirstrepo") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
        }

        logTo("00-intro.md") {
            markdown("""
            # Klonen von Repositorys

            Ein Git-Repository namens `myfirstrepo` wurde bereits erstellt.
            Es enthält zwei Commits:
            """)

            inRepo("myfirstrepo") {
                git("log --oneline")
            }
        }

        createAufgabe(
                "Klon durchführen",
                """Erstelle einen Klon von `myfirstrepo` mit dem Namen `myfirstclone`."""
        ) {
            git("clone myfirstrepo myfirstclone")
        }

        createAufgabe(
                "Klon untersuchen",
                """Zeige den Origin des Klons `myfirstclone`.
                    `origin` steht in der Regel für jenes Repository,
                    von dem geklont wurde.
                """
        ) {
            inRepo("myfirstclone") {
                git("remote -v")
            }
        }

        createAufgabe(
                "Im Klon arbeiten",
                """ Erstelle ein Commit und zeige dann den Status."""
        ) {
            inRepo("myfirstclone") {
                editAndCommit("foo", 3)
                git("status")
            }
        }

    }
}