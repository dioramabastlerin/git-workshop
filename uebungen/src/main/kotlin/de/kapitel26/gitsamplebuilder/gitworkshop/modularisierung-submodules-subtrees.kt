package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.submodulesSubtrees() {
    createAufgabenFolge("submodules-subtrees") {

        createIntro(
                """Modularisierung mit Submodules und Subrepos""",
                """

                Es geht darum, wie man in Git ein übergreifendes
                Repository erstellt, dass Inhalte aus mehreren
                anderen Repository einbettet.
                
                Git bietet dazu zwei unterschiedliche Ansätze:
                `git submodule` und `git subtree`.
                Wir werden hier beide für folgende Anwendungsfälle erprobe:
                
                * Module als Subtree einbinden
                * Änderung aus einem Modul übernehmen
                * Änderung in ein Modul übertragen
                * Übergeordnetes Repo klonen
                
                ### Submodules
                
                Bei diesem Ansatz werden Git-Repositorys geschachtelt,
                d. h. in dem Zielverzeichnis wo das Modul eingebettet werden
                wird das das andere Repository geklont.
                Das übergeordnete Repository merkt sich dabei nur
                die Adresse (URL) des anderen Repositorys,
                das Zielverzeichnis und den Commit-Hash,
                der ausgecheckt werden soll.
                
                 * `submodule add  <Quellrepository> <Zielverzeichnis>`:
                    Zum initialen Einbetten.
                    **Achtung!** Bei Submodules müssen Änderungen durch ein `git commit` bestätigt werden!
                 * `submodule update --init <Zielverzeicnis>`:
                    Zum Holen der der Submodule.

                ### Subtrees
                
                Bei diesem Ansatz werden Commits aus dem aus dem untergeordeten
                Repository übertragen und per `merge` integriert, 
                ganz ähnlich wie beim normalen `pull`.
                Die Besonderheit ist, dass Zur Integraion 
                dabei eine sogenanntes `subtree`-Merge erfolgt, 
                bei dem die Dateien in eine vorgegebenes Zielverzeichnis (`prefix`) verschoben werden.
                
                 * `subtree add --prefix=<Zielverzeichnis> <Quellrepository>`: Initales einbetten.
                 * `subtree pull --prefix=<Zielverzeichnis> <Quellrepository>`: Aktualisieren aus dem Quellrepository.
                 * `subtree push--prefix=<Zielverzeichnis> <Quellrepository>`: Übertragen ins Quellrepository.
                
                Tipp: Wer nicht mag,
                dass Subtree alle Commit aus dem Quellrepository holt,
                kann die Option `--squash` nutzen.

                ## Setup
                
                Zwei Repositorys `mod-a` und `mod-b` sind vorhanden.
                Diese sollen 

                ### Verzeichnisse

                 * `${rootDir.name}/` Haupverzeichnis für diese Übung 
                   - `myfirstrepo/` Bereits vorhandenes Repository.
                  
                  
            """
        ) {
            createRepo("mod-a.git", "--bare") { createClone("../mod-a") }
            createRepo("mod-b.git", "--bare") { createClone("../mod-b") }

            inRepo("mod-a") {
                createFileAndCommit("anton")
                git("push")
            }

            inRepo("mod-b") {
                createFileAndCommit("berta")
                git("push")
            }

            createRepo("subtrees") {
                createFileAndCommit("README")
            }

            createRepo("submodules") {
                createFileAndCommit("README")
            }

        }

        solutionCollector.collectedCommands.add("Subtrees" to { markdown("# Subtrees") })


        inRepo("subtrees") {
            createAufgabe(
                    "Module als Subtree einbinden",
                    """
                    Binde die Module `mod-a.git` und `mod-b.git`
                    per `subtree add` ein.
                    Untersuche dann die entstandene Verzeichnisstruktur.
                    """
            ) {
                git("subtree add --prefix=mod-a ../mod-a.git master")
                git("subtree add --prefix=mod-b ../mod-b.git master")
                git("ls-tree -r HEAD")
            }
        }

        createAufgabe(
                "Änderung aus einem Modul übernehmen",
                """
                    Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
                    Sie Dir das entstandene Commit an (`show --stat`)
                    Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
                    Sieh Dir das übertragene Commit an.
                    """
        ) {
            inRepo("mod-b") {
                editAndCommit("berta", 7)
                git("show --stat ")
                git("push")
            }

            inRepo("subtrees") {
                git("subtree pull --prefix=mod-b ../mod-b.git master")
                git("show --stat ")
            }
        }

        createAufgabe(
                "Änderung in ein Modul übertragen",
                """
                    Gehe in `subtrees` ändere `mod-a/anton` und committe.
                    Übertrage die Änderung per `subtree push` nach `mod-a.git`.
                    Sieh Dir das übertragene Commit in `mod-a.git` an.
                    """
        ) {
            inRepo("subtrees") {

                editAndCommit("mod-a/anton", 3)
                git("subtree push --prefix=mod-a ../mod-a.git master")
            }

            inRepo("mod-a.git") {
                git("show --stat ")
            }
        }

        createAufgabe(
                "Übergeordnetes Repo klonen",
                """
                    Klone `subtrees` zu `mysubtrees`.
                    Untersuche die `HEAD` Verzeichnisstruktur,
                    und den Commit-graphen.
                    """
        ) {
            git("clone subtrees mysubtrees")

            inRepo("mysubtrees") {
                markdown("Man sieht, dass die Einbettungen" +
                        " als normale Dateien und Verzeichnisse" +
                        " im `HEAD`-Tree erscheinen")
                git("ls-tree -r HEAD .")
                markdown("Im Commit-Graphen sieht man," +
                        " woher die Daten kommen.")
                git("log --graph --oneline --stat")
            }
        }

        solutionCollector.collectedCommands.add("Submodules" to { markdown("# Submodules") })


        inRepo("submodules") {
            createAufgabe(
                    "Module als Submodule einbinden",
                    """
                    Binde die Module `mod-a.git` und `mod-b.git`
                    per `submodule add` ein.
                    Untersuche dann die entstandene Verzeichnisstruktur.
                    """
            ) {
                git("submodule add  ../mod-a.git mod-a")
                git("submodule add  ../mod-b.git mod-b")
                markdown("Man sieht, dass die Module als eigenständige" +
                        " Git-Repositorys mit separatem `.git`-Verzeichnis" +
                        " eingebettet wurden.")
                bash("ls -lah mod-a mod-b")
                markdown("Achtung! Die submodule wurden hinzugefügt, aber es fehlt noch ein Commit.")
                git("status")
                git("commit -m 'add mod-a and mod-b'")
            }
        }

        createAufgabe(
                "Subtree: Änderung aus einem Modul übernehmen",
                """
                    Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
                    Sie Dir das entstandene Commit an (`show --stat`)
                    Gehe in das Repo `submodules/mod-b` und hole die Änderungen per `pull` ab.
                    Sieh Dir das übertragene Commit an.
                    """
        ) {
            inRepo("mod-b") {
                editAndCommit("berta", 8)
                git("show --stat ")
                git("push")
            }

            inRepo("submodules") {
                inRepo("mod-b") {
                    git("pull")
                }
                git("add mod-b")
                git("commit -am 'updated mod-b'")
            }
        }

        createAufgabe(
                "Änderung in ein Modul übertragen",
                """
                    Gehe in `subtrees/mod-a` ändere `anton` und committe.
                    Übertrage die Änderung per `push` nach `mod-a.git`.
                    Sieh Dir das übertragene Commit in `mod-a.git` an.
                    """
        ) {
            inRepo("submodules") {
                inRepo("mod-a") {
                    editAndCommit("anton", 5)
                    git("push")
                }
                markdown("Nicht vergessen: Änderungen am im übergeordenten Repository committen.")
                git("add mod-a")
                git("commit -m 'new version of mod-a'")
            }

            inRepo("mod-a.git") {
                git("show --stat ")
            }
        }

        createAufgabe(
                "Übergeordnetes Repo klonen",
                """
                    Klone `submodules` zu `mysubmodules`.
                    Untersuche die Verzeichnisstruktur.
                    Vergiß nicht, ein `submodule update` auszuführen.
                    """
        ) {
            git("clone submodules mysubmodules")

            inRepo("mysubmodules") {
                markdown("Die Modulverzeichnisse sind da aber noch leer:")
                bash("ls -lah mod-a mod-b")
                markdown("Jetzt holen wir die Module:")
                git("submodule update --init")
                bash("ls -lah mod-a mod-b")
            }
        }


    }
}
