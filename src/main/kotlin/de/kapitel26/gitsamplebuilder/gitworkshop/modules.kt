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
            createAufgabe(
                    "Module als Subtree einbinden",
                    """
                    Binde die Module `mod-a.git` und `mod-b.git`
                    per `subtree add` ein.
                    Untersuche dann die entstandene Verzeichnisstruktur.
                    """
            ) {
                createFileAndCommit("README")
                git("subtree add --prefix=mod-a ../mod-a.git master")
                git("subtree add --prefix=mod-b ../mod-b.git master")
                git("ls-tree -r HEAD")
            }
        }

        createAufgabe(
                "Änderung aus eine Modul übernehmen",
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

    }
}
