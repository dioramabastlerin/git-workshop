package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.integrationVonAenderungen() {
    createAufgabenFolge("integration-von-aenderungen") {

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
                * Nach einem Merge-Konflikt:
                  1. Konfliktdateien bearbeiten
                  2. dann `git add` nicht vergessen
                  3. Den Merge mit `git commit` abschließen
                  
                  
                ## Ausgangssituation
                
                Ihre Kollegin Anja hat die Arbeit an einem Projekt begonnen.
                Nun kommen Sie hinzu und übernehmen Aufgaben.
                Anja hat aber parallel ebenfalls weiter gearbeitet.
                Integrieren Sie die neuen Änderungen von Anja.

            """
        ) {
            createRepo("origin-for-merge-samples.git", "--bare") {

                createClone("../anjas-repo") {
                    user("anja")

                    createFileAndCommit("README.md") { content = "Hallo Wolt!\n" }
                    createFileAndCommit("average.kts") {
                        content = """
                    if(args.isEmpty())
                        throw RuntimeException("No arguments given!")
    
                    val s = args.map{ it.toInt() }.sum()
                    
                    println("The average is ${'$'}{s/args.size}")
                    
                """.trimIndent()
                    }
                    git("push")
                }

                createClone("../changes-in-different-files") {
                }

                createClone("../fast-forward") {
                }

                createClone("../no-ff") {
                }

                createClone("../changes-in-same-files") {
                    inFile("average.kts") {
                        replace("val s = ", "val summe = ")
                        replace("{s/args.size}", replaceWith = "{summe/args.size}")
                        commit("Refactoring: s in summe umbenennen")
                    }
                }
            }
            inRepo("anjas-repo") {
                inFile("average.kts") {
                    replace("{ it.toInt() }", "{ it.toDouble() }")
                    commit("Verwende double Werte statt int")
                }
                git("push")
            }
        }

        inRepo("fast-forward") {
            createAufgabe(
                    "Fast-Forward beim Pull",
                    """
                    Im einfachste Fall haben wir selber gar nichts gemacht,
                    und wollen nur die Änderungen von Anja übernehmen.
                    
                    Führe ein Pull durch.
                    
                    Lasse Dir Status und den Commit-Graphen zeigen.
                    """
            ) {

                git("pull")

                markdown("Git signalisiert, dass ein Fast-Forward durchgeführt wurde.")
                markdown("Der Graph zeigt, dass keine Verzweigung entstanden ist und kein Merge notwendig war.")

                git("log --graph --oneline --decorate")
            }
        }

        inRepo("no-ff") {
            createAufgabe(
                    "Merge erzwingen beim Pull",
                    """
                    Auch dieses haben wir nichtnichts gemacht,
                    und wollen nur die Änderungen von Anja übernehmen.
                    
                    Führe ein Pull mit `--no-ff` durch.
                    
                    Lasse Dir Status und den Commit-Graphen zeigen.
                    """
            ) {

                git("pull --no-ff")

                markdown("Git signalisiert, dass kein Fast-Forward durchgeführt wurde.")
                markdown("Der Graph zeigt, dass ein Merge enstanden ist.")

                git("log --graph --oneline --decorate")
            }
        }



        inRepo("changes-in-different-files") {
            createAufgabe(
                    "Integration bei Änderungen in verschiedenen Dateien",
                    """
                        1. Bearbeite die Datei `README.md`.
                           - Erstelle ein Commit dazu.
                           - Prüfe mit `git show`, ob das Commit OK ist.
                        2. Versuche ein Push
                           - Dies wird scheitern, denn Deine Kollegin Bea 
                             hat die in der Zwischenzeit die Datei `average.kts`
                             bearbeitet und gepushed.
                        3. Integriere mit Pull
                        4. Untersuche das Ergebnis, z. B.
                           - den Commit-Graphen an
                           - die Änderungen, die Anja gemacht hat 
                           - die Commits, die Anja gemacht hat
                    """) {

                inFile("README.md") {
                    content = "Hallo Welt!\n"
                    commit()
                }

                git("show")

                git("push", setOf(1))

                markdown("""
                    Diese Meldung zeigt, dass im `origin` Änderungen vorliegen,
                    die wir noch nicht integriert haben.
                """)

                git("pull")

                markdown("""
                    Git hat die Änderungen geholt und ein Merge-Commit erzeugt.
                """)

                git("log --oneline --graph")
                git("diff HEAD^1...HEAD^2")
                git("log HEAD^2..HEAD^1")

                markdown("""
                    Und jetzt können wir erneut ein Push versuchen.
                """)

                git("push")

            }
        }

        inRepo("changes-in-same-files") {
            createAufgabe(
                    "Integration bei Änderungen in derselben Datei",
                    """
                    In diesem Fall bearbeiten wir dieselbe Datei,
                    die auch Anja bearbeitet hat.
                    Es wird zu einem Konflikt kommen, 
                    den wir aulösen müssen.
                    
                    1. Wir haben schon eine Änderung, die zu einem Konflikt führt,
                       vorbereitet und committed. Untersuche diese mit `git show`
                    2. Führe ein Pull durch.
                    3. Lasse Dir den Status zeigen und löse den Konflikt.
                    """
            ) {
                git("show")

                git("pull", acceptableExitCodes = setOf(1))

                markdown("Wie erwartet, ist es zu einem Konflikt gekommen.")

                git("status")

                inFile("average.kts") {
                    replaceRegex(
                            """\<\<\<.*\>\>\>""".toRegex(),
                            "val summe = args.map{ it.toDouble() }.sum()"
                    )
                }
                markdown("Nicht vergessen: Nach dem Bereinigen `git add` aufrufen.")
                git("add average.kts")
                git("commit -m 'Änderungen von Anja integriert'")

                markdown("Und hier nochmal der entstandene Graph:")
                git("log --graph --oneline")
            }
        }
    }
}
