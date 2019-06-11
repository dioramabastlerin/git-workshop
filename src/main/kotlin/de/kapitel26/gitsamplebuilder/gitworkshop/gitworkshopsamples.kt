package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.buildGitSamples
import de.kapitel26.gitsamplebuilder.impl.Dir
import java.io.File

fun main() {

    Dir(File("build/gitworkshop")).clear()

    buildGitSamples("push-fetch-pull", "build/gitworkshop", "aufgabe-1") {

        createRepo("blessed.git", "--bare")

        cloneRepo("blessed.git", "anderer-klon") {
            createFileAndCommit("foo", "Initial edit before cloning")
            git("push")
        }

        cloneRepo("blessed.git", "mein-klon")

        repo("anderer-klon") {
            editAndCommit("foo", 3, "First edit after cloning")
            editAndCommit("foo", 7, "Second edit after cloning")
            git("push")
        }

        flushLogToFile()

        doc("""
            # Aufgabe 1

            ## 1a Änderungen holen

            Hole die beiden neuen Commits vom `origin`-Repository,
            ohne den lokalen `master` zu verändern.

            ## 1b Änderungen untersuchen

            Lasse dir den Status zeigen,
            und untersuche dann,
            welche Commits im `master` des `origin`-Repository vorhanden sind,
            welche im lokalen `master` noch nicht integriert wurden.

            ## 1c Änderungen integrieren

            Integriere die neuesten Commits vom `origin`-Repository
            in den lokalen `master`.

        """)

        flushLogToFile("aufgabe-1.md")

        duplicatedSample("loesung-1") {
            repo("mein-klon") {

                git("log --oneline --decorate -3")

                doc("Zunächst finden wir nur ein Commit auf dem lokalen `master`.")

                doc("## 1a Änderungen holen")
                git("fetch")
                doc("Die Ausgabe zeigt, dass Änderungen auf dem Branch `master` geholt wurden.")

                doc("## 1b Änderungen untersuchen")
                git("status")
                doc("""Der Status zeigt, dass es im Origin-Repo
                    (auf dem Branch `master`) zwei Commits gibt,
                    die wir noch nicht integriert haben.
                """)

                git("log master..origin/master")

                doc("""Die `..`-Notation zeigt genau jene Commits,
                    die in `origing/master` aber noch nicht in `master` enthalten sind.
                    Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
                    könne, da wir `master` ja gerade `HEAD` ist.""")

                doc("## 1c Änderungen integrieren")

                git("pull")
                git("log --oneline --decorate -3")
            }
            flushLogToFile("loesung-1.md")
        }

    }


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