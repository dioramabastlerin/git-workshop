package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.klonen() {
    createAufgabenFolge("klonen") {


        createIntro(
                """Klonen von Repositorys""",
                """

                ## Tipps
                
                 * `git clone <original> <kopie>`: Klont ein Repository.
                 * `git remote -v`: Zeigt auf, welchen anderen Klone bekannt sind,
                   insbesondere `origin`.

                ## Setup
                
                Ein Git-Repository namens `myfirstrepo` wurde bereits erstellt.
                Es enthält zwei Commits.

                ### Verzeichnisse

                 * `${rootDir.name}/` Haupverzeichnis für diese Übung 
                   - `myfirstrepo/` Bereits vorhandenes Repository.
                  
            """
        ) {
            createRepo("myfirstrepo") {
                createFileAndCommit("foo")
                createFileAndCommit("bar")
            }

        }

        createAufgabe(
                "Klon durchführen",
                """Erstelle einen Klon von `myfirstrepo` mit dem Namen `myfirstclone`."""
        ) {
            git("clone myfirstrepo myfirstclone")
        }

        createAufgabe(
                "Klon untersuchen",
                """
                    Schaue die Commits an und
                    zeige den Origin des Klons `myfirstclone`.
                    `origin` steht in der Regel für jenes Repository,
                    von dem geklont wurde.
                    Zeige dann den Status.
                """
        ) {
            inRepo("myfirstclone") {
                git("log --oneline")
                git("remote -v")
            }
        }

        createAufgabe(
                "Im Klon arbeiten",
                """ Erstelle ein Commit und zeige dann den Status."""
        ) {
            inRepo("myfirstclone") {
                editAndCommit("foo", 3)
                git("status")
            }
        }

    }
}