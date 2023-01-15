package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.repositorysZusammenfuehren() {
    createAufgabenFolge("repositorys-zusammenfuehren") {


        createIntro(
                """Kleine Projekte zusammenfuehren""",
                """
                Zwei bisher unabhängige Repositorys sollen,
                unter Erhaltung der Historie zu einem 
                großen Repository zusammengeführt werden.
                
                ## Tipps
              
                 * Mit `fetch` (und `pull`) kann man auch Historie aus fremden Repositorys ohne gemeinsame Histories
                 mit dem aktuellen Repository holen (Stichwort *unrelated histories*).
                 * `merge` (und `pull`) verweigern normalerweise das Integrieren von *unrelated histories*
                   - Mit `--allow-unrelated-histories` kann man dies jedoch erzwingen.
                 * `git mv` verschiebt Dateien und Verzeichnisse, z. B. `git mv datei1 date1 zielverzeichnis`.
                 *  `subtree add --prefix=<Zielverzeichnis> <Quellrepository> <Quellbranch oder Version>`: Einbetten eines anderen Repositorys in ein Unterverzeichnis.
                    - Fall man nicht die ganze Historie übernehmen möchte, kann man `--squash` angeben.
                    - **Achtung:** `subtree add ` führt einen Merge auf `HEAD` durch und  
                      kann daher nicht in einem leeren Repository ohne Commits ausgeführt werden.

                ## Setup
     
                Zwei Repositorys
                 
                 * `backend.git`
                 * `ui.git` 
    
                sind bereits vorhanden.
 
            """
        ) {
            createRepo("backend.git", "--bare") { createClone("../backend") }
            createRepo("ui.git", "--bare") { createClone("../ui") }

            inRepo("backend") {
                createDir("src") {
                    createFileAndCommit("Backend.java")
                }
                createDir("test") {
                    createFileAndCommit("BackendTest.java")
                }
                git("push")
            }

            inRepo("ui") {
                createDir("src") {
                    createFileAndCommit("UI.java")
                }
                createDir("test") {
                    createFileAndCommit("UITest.java")
                }
                git("push")
            }
        }

        createAufgabe(
                "Zusammenführen `git subtree`",
                """
                
                 1. Erstelle ein Repo `application` mit einem Commit.
                 2. Füge `backend.git` in einem Unterverzeichnis `backend` hinzu.
                 3. Füge `ui.git` in einem Unterverzeichnis `ui` hinzu.
                 4. Untersuche Verzeichnissstruktur und Commit-Graphen
                    """
        ) {
            createRepo("application") {
                markdown("Wir erzeugen ein erstes Commmit, damit der `subtree`-Befehl ausgeführt werden kann.")
                createFileAndCommit("README")
                markdown("Dann fügen wir die Repos mit `subtree` hinzu:")
                git("subtree add --prefix=backend ../backend.git main")
                git("subtree add --prefix=ui ../ui.git main")

                markdown("Man sieht ui und backend wurden mitsamt Historie zusammengeführt:")
                git("ls-tree -r --name-only HEAD")
                git("log --oneline --graph")
            }
        }


        createAufgabe(
                "Zusammenführen mit `fetch`, `mv` und `merge`",
                """
            UI und Backend sollen in einem neuen Klon `gesamt` zusammengeführt werden.
            Folge den Anweisungen im Kapitel *"Kleine Projekte zusammenführen"*.
            Untersuche dann Verzeichnissstruktur und Commit-Graphen
            """
        ) {
            git("clone backend gesamt")
            inRepo("gesamt") {
                markdown("Backend-Dateien in Unterverzeichnis verschieben:")
                bash("mkdir backend")
                git("mv src test backend")
                git("commit -m 'backend-Verzeichnis angelegt'")

                markdown("Inhalt des UI-Repository in einen lokalen Branch `uimain` holen:")
                git("remote add ui ../ui/")
                git("fetch ui")
                git("checkout -b uimain ui/main")

                markdown("UI-Dateien in Unterverzeichnis verschieben:")
                bash("mkdir ui")
                git("mv src test ui")
                git("commit -m 'ui-Verzeichnis angelegt'")

                markdown("`uimain` integrieren:")
                git("checkout main")
                git("merge uimain --allow-unrelated-histories")

                markdown("Man sieht ui und backend wurden mitsamt Historie zusammengeführt:")
                git("ls-tree -r --name-only HEAD")
                git("log --oneline --graph")
            }
        }
    }
}
