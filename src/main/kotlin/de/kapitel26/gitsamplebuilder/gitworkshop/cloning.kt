package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.cloning() {
    createAufgabenFolge("cloning") {

        createRepo("myfirstrepo") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
        }

        doc("00-intro.md") {
            markdown("""
            # Klonen von Repositorys

            Ein Git-Repository namens `myfirstrepo` wurde bereits erstellt.
            Es enthält zwei Commits:
            """)

            inRepo("myfirstrepo") {
                newGit("log --oneline")
            }
        }

        createAufgabe(
                "Klon durchführen",
                """Erstelle einen Klon von `myfirstrepo` mit dem Namen `myfirstclone`."""
        ) {
            newGit("clone myfirstrepo myfirstclone")
        }

        createAufgabe(
                "Klon untersuchen",
                """Zeige den Origin des Klons `myfirstclone`.
                    `origin` steht in der Regel für jenes Repository,
                    von dem geklont wurde.
                """
        ) {
            inRepo("myfirstclone") {
                newGit("remote -v")
            }
        }

        createAufgabe(
                "Im Klon arbeiten",
                """ Erstelle ein Commit und zeige dann den Status."""
        ) {
            inRepo("myfirstclone") {
                editAndCommit("foo", 3)
                newGit("status")
            }
        }

    }
}