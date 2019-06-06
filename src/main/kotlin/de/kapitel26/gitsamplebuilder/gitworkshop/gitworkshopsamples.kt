package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.buildGitSamples

fun main() {
    buildGitSamples("cloning", "build/gitworkshop") {
        createFile("uebungen")

        createRepo("myfirstrepo") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
        }

        createFile("aufgabe-1.md", """
            # Aufgabe 1 - Repository klonen

            ## Klon durchführen

             * Erstelle einen Klon von `myfirstrepo`, bennant als  `myfirstclone`.

            ## Klon untersuchen

            Zeige den Origin des Klons.

            ## Im Klon arbeiten.

            Erstelle ein Commit und zeige den Status.

        """.trimIndent())

        flushLogToFile()

        duplicatedSample("loesung") {
            //createFile("loesung")
            git("clone myfirstrepo myfirstclone")
            dir("myfirstclone") {
                git("remote -v")
                git("status")
            }
            flushLogToFile("loesung-1.md")
        }

    }
}