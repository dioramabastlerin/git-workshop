package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.modules() {
    createAufgabenFolge("modules") {

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

        createIntro(
                """Modularisierung mit Submodules und Subrepos""",
                """

                Es geht darum, wie man in Git ein übergreifendes
                Repository erstellt, dass Inhalte aus mehreren
                anderen Repository einbettet.
                
                Git bietet dazu zwei unterschiedliche Ansätze:
                `git submodule` und `git subtree`.
                Wir werden hier beide erproben.

                ## Tipps

                
                
                ### Submodules
                
                Bei diesem Ansatz werden Git-Repositorys geschachtelt,
                d. h. in dem Zielverzeichnis wo das Modul eingebettet werden
                wird das das andere Repository geklont.
                Das übergeordnete Repository merkt sich dabei nur
                die Adresse (URL) des anderen Repositorys,
                das Zielverzeichnis und den Commit-Hash,
                der ausgecheckt werden soll.
                
                 * 

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
                
                Tipp: Normalerweise werden beim Subtree alle Commits aus dem Quellrepository

                ## Setup
                
                Zwei Repositorys `mod-a` und `mod-b` sind vorhanden.
                Diese sollen 

                ### Verzeichnisse

                 * `${rootDir.name}/` Haupverzeichnis für diese Übung 
                   - `myfirstrepo/` Bereits vorhandenes Repository.
                  
            """
        )

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
                "Subtree: Änderung aus einem Modul übernehmen",
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
                "Subtree: Änderung in ein Modul übertragen",
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
                "Subtree: Übergeordnetes Repo klonen",
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


    }
}
