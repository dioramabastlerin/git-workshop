package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.cloning() {
    createAufgabe("cloning") {

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
                git("log --oneline")
            }
        }

        doc("01-aufgabe.md") {
            markdown("""
                    ## Klon durchführen

                    Erstelle einen Klon von `myfirstrepo` mit dem Namen `myfirstclone`.
                """)
        }

        doc("02-aufgabe.md") {
            markdown("""
                    ## Klon untersuchen

                    Zeige den Origin des Klons `myfirstclone`.
                    `origin` steht in der Regel für jenes Repository,
                    von dem geklont wurde.
                """)
        }

        doc("03-aufgabe.md") {
            markdown("""
                    ## Im Klon arbeiten.

                    Erstelle ein Commit und zeige dann den Status.
                """)
        }

        writeDocs()
    }

    createLoesung("cloning") {

        doc("01-aufgabe.md") {
            git("clone myfirstrepo myfirstclone")
        }

        inRepo("myfirstclone") {

            doc("02-aufgabe.md") {
                git("remote -v")
            }

            doc("03-aufgabe.md") {
                editAndCommit("foo", 3)
                git("status")
            }

        }

        writeDocs()
    }
}