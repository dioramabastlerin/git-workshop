package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.integrationOfChanges() {
    createAufgabenFolge("integration-of-changes") {

        createIntro(
                """Integration von Änderungen""",
                """

                Wenn mehrere Entwickler unabhängig am selben Projekt arbeiten,
                müssen deren Änderungen von Zeit zu Zeit integriert werden.
                Dies nennt man *Merging*.
                
                Die Integration kann in Git mit den Befehlen `pull`, `merge`
                und `rebase` durchgeführt werden.

                Dabei kommt es immer mal wieder zu *Merge-Konflikten*.
                
                In dieser Übung zeigen wir die Integration per `pull`,
                weil dies sehr typisch für das Arbeiten mit Git ist.
                
                Die Zusammenführung und der Umgang mit Konflikten funktioniert
                aber bei `merge` und `rebase` ganz ähnlich..

                ## Infos
                
                * `git pull` integriert den lokalen Branch mit seinem "upstream" Gegenstück,
                  hier: `master` und `origin/master`

                ## Tipps
                
                * `git pull` Holt und integriert Änderungen Äquivalent zu `git fetch` + `git merge`)
                * `git log --graph` zeigt den Commit-Graphen
                * `HEAD^1` und `HEAD^2` bezeichnen den ersten bzw. zweiten Vorgänger,
                  des aktuellen `HEAD`-Commits.
                * `git diff HEAD^1...HEAD^2` zeigt die "fremden" Änderungen 
                * `git log HEAD^1..HEAD^2` zeigt die "fremden" Commits 

            """
        )

        createRepo("origin-for-merge-samples") {
            user("bea")

            createFileAndCommit("README.md")
            createFileAndCommit("average.kts") {
                content = """
                if(args.isEmpty())
                    throw RuntimeException("No arguments given!")

                val s = args.map{ it.toInt() }.sum()
                
                println("The average is ${'$'}{s/args.size}")
            """.trimIndent()
            }

            createClone("../changes-in-different-files") {

            }

            createClone("../my-conflicting-merge") {

                inFileCommit("average.kts", "Refactoring: s in summe umbenennen") {
                    replace("val s = ", "val summe = ")
                    replace("{s/args.size}", replaceWith = "{summe/args.size}")
                }
            }

            user("anja")
            inFileCommit("average.kts", "Verwende double Werte statt int") {
                replace("{ it.toInt() }", "{ it.toDouble() }")
            }
        }

        inRepo("changes-in-different-files") {
            createAufgabe(
                    "Integration von Änderungen in verschiedenen Dateien",
                    """
                        1. Bearbeite die Datei `README.md`.
                           - Erstelle ein Commit dazu.
                           - Prüfe mit `git show`, ob das Commit OK ist.
                        2. Versuche ein `git push`
                           - Dies wird scheitern, denn Deine Kollegin Anja 
                             hat die in der Zwischenzeit die Datei `average.kts`
                             bearbeitet und gepushed.
                        3. Integriere mit `git pull`
                        4. Untersuche das Ergebnis, z. B.
                           - den Commit-Graphen an (Log mit `--graph`)
                           - die Änderungen, die Anja gemacht hat (`diff` mit `...`)
                           - die Commits, die Anja gemacht hat (`log` mit `..`)
                    """) {
                bash("touch wurst")
                inFileCommit("README.md") { edit(3) }
                git("show")

                git("pull")

                git("log --oneline --graph")
                git("diff HEAD^1...HEAD^2")
                git("log HEAD^2..HEAD^1")
            }
        }

        inRepo("my-conflicting-merge") {
            createAufgabe(
                    "Integration bei Änderungen in derselben Dateie",
                    """
                    Just pull.
                    """
            ) {
                git("show")
                git("pull", acceptableExitCodes = setOf(1))
                git("status")

                inFile("average.kts") {
                    replaceRegex(
                            """\<\<\<.*\>\>\>""".toRegex(),
                            "val summe = args.map{ it.toDouble() }.sum()"
                    )
                }
                git("add average.kts")
                git("commit -m 'Änderungen von Anja integriert'")

                git("log --graph --oneline")
            }
        }

    }
}
