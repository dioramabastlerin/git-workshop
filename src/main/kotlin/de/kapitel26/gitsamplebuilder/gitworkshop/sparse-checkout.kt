package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.sparseCeckouts() {
    createAufgabenFolge("sparse-checkouts") {

        createRepo("repo") {
            createFileAndCommit("README.md")
            createDir("component-a") {
                createFileAndCommit("foo")
            }
            createDir("component-b") {
                createFileAndCommit("bar")
            }
        }

        createIntro(
            """Sparce Checkout""",
            """

                ## Tipps
                
                 * `git clone --sparse`: Klont ein Repository, ohne den Workspace zu füllen.
                 * `git sparse-checkout init --cone`: Konfiguriert den cone-Modus für bessere Performance.
                 * `git sparse-checkout add fileDirOrPattern`: Dateien bzw. Verzeichnisse hinzufügen,
                    die beim nächsten Checkout geladen werden sollen.
                    Beim nächsten Checkout wird alles geholt.
                 * `git sparse-checkout list`: Aktuelle Sparse-Checkout-Konfiguration ansehen.
                 * `git sparse-checkout disable`: Sparse-Checkout wieder abschalten.

                ## Setup
                
                Ein Git-Repository namens `repo` wurde bereits erstellt.
                Es enthält Dateien auf dem Top-Level und in zwei Unterverzeichnissen.

                ### Verzeichnisse

                 * `${rootDir.name}/` Haupverzeichnis für diese Übung 
                   - `repo/` Bereits vorhandenes Repository.
                  
            """
        )

        createAufgabe(
            "Sparse-Klon durchführen",
            """
                | Erstelle einen Sparse-Klon von `repo` mit dem Namen `myrepo`,
                | überprüfe, dass nur Top-Level-Datein in den Workspace geholt wurden.
                """.trimMargin()
        ) {
            git("clone --sparse repo myrepo")
            inRepo("myrepo") {
                bash("ls -lR")
            }
        }

        createAufgabe(
            "Verzeichnis hinzufügen",
            """
            | Füge das Verzeichnis `component-a` hinzu .
            | Überprüfe die neue Konfiguration.
            | Validiere, dass `component-a` jetzt da ist.
            """.trimMargin()
        ) {
            inRepo("myrepo") {
                git("sparse-checkout add component-a")
                git("sparse-checkout list")
                git("checkout")
                bash("ls -lR")
            }
        }

        createAufgabe(
            "Sparse Checkout deaktivieren",
            """
            | Deaktiviere Sparse-Checkout und führe erneut ein Checkout durch.
            | Validiere, dass jetzt alle Dateien da sind.
            """
        ) {
            inRepo("myrepo") {
                git("sparse-checkout disable")
                git("checkout")
                bash("ls -lR")
            }
        }


        createAufgabe(
            "Beispiel für Slide",
            """
                | Sparse clone, `--cone` aktivieren,
                | Verzeichnis hinzufügen, dann Checkout.
                """.trimMargin()
        ) {
            git("clone --sparse repo myclone")
            inRepo("myclone") {
                git("sparse-checkout init --cone")
                git("sparse-checkout add component-a")
                git("sparse-checkout list")
                git("checkout")
                bash("ls -lR")
            }
        }



        createAufgabe(
            "Beispiel von Slide mit --no-checkout",
            """
                    Beim Checkout mit --no-checkout sieht Git,
                    logischerweise lauter Loechungen   
                    """
        ) {
            git("clone --no-checkout repo myrepo2")

            inRepo("myrepo2") {
                git("status")
                git("sparse-checkout init --cone")
                git("sparse-checkout add component-a/foo")
                git("checkout")
                bash("ls -R")
                git("status")
            }
        }

    }
}