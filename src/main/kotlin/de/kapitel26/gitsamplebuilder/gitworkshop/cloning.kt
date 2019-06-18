package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.cloning() {
    createAufgabe("cloning") {

        createRepo("myfirstrepo") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
        }

        doc("aufgabe-1.md") {
            markdown("""
                    # Aufgabe 1 - Repository klonen

                    ## Klon durchführen

                     * Erstelle einen Klon von `myfirstrepo`, bennant als  `myfirstclone`.
                """)
        }

        doc("aufgabe-2.md") {
            markdown("""
                    ## Klon untersuchen

                    Zeige den Origin des Klons.
                """)
        }

        doc("aufgabe-3.md") {
            markdown("""
                    ## Im Klon arbeiten.

                    Erstelle ein Commit und zeige den Status.
                """)
        }

        writeDocs()
    }

    createLoesung("cloning") {

        doc("aufgabe-1.md") {
            git("clone myfirstrepo myfirstclone")
        }

        inRepo("myfirstclone") {

            doc("aufgabe-2.md") {
                git("remote -v")
            }

            doc("aufgabe-3.md") {
                editAndCommit("foo", 3)
                git("status")
            }

        }

        writeDocs()
    }
}