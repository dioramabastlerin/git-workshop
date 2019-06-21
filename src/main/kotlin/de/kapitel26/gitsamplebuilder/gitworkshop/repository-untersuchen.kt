package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.repositoryUntersuchen() {
    createAufgabenFolge("repository-untersuchen") {

        createRepo {
            createFileAndCommit("hallo-welt")
            createDir("foo") {
                createFileAndCommit("bar")
            }
        }

        doc("00-intro.md") {
            markdown("""
            # Repositorys untersuchen

            Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
            untersucht zu werden. 
            """)

            inRepo {
                execute("ls -hal")
            }

            markdown("""
                
                Man sieht: Das Projekt enthält eine Datei, ein normales Unterverzeichnis
                und natürlich auch ein `.git`-Verzeichnis, welches das Repository
            """.trimIndent())
        }

        createAufgabe(
                "Commits ansehen",
                """Schaue Dir Commits in diesem Repo an."""
        ) {
            inRepo {
                git("log --oneline")
                git("log --oneline --decorate")
            }
        }

    }
}