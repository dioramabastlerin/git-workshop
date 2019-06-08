package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.buildGitSamples

fun main() {
    buildGitSamples("cloning", "build/gitworkshop") {

        createRepo("myfirstrepo") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
        }

        flushLogToFile()

        doc("""
            # Aufgabe 1 - Repository klonen

            ## Klon durchführen

             * Erstelle einen Klon von `myfirstrepo`, bennant als  `myfirstclone`.

            ## Klon untersuchen

            Zeige den Origin des Klons.

            ## Im Klon arbeiten.

            Erstelle ein Commit und zeige den Status.

        """)

        flushLogToFile("aufgabe-1.md")

        duplicatedSample("loesung") {
            doc("## Lösung\n\n")

            doc("## Klon durchführen\n\n")
            git("clone myfirstrepo myfirstclone")

            repo("myfirstclone") {

                doc("## Klon untersuchen\n\n")
                git("remote -v")


                doc("## Im Klon arbeiten.\n\n")
                editAndCommit("foo", 3)
                git("status")
            }
            flushLogToFile("loesung-1.md")
        }

    }
}