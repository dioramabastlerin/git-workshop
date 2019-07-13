package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.pushRejected() {
    createAufgabenFolge("push-rejected") {

        val projectName = "apollo"
        val blessedRepo = "blessed-$projectName.git"
        val anjasClone = "anjas-$projectName"
        val myClone = "my-$projectName"
        val anjasFile = "backend.java"
        val myFile = "frontend.java"

        createRepo(blessedRepo, "--bare")

        createClonedRepo(blessedRepo, anjasClone) {
            user("anja")
            createFileAndCommit(anjasFile)
            createFileAndCommit(myFile)
            git("push")
        }

        createClonedRepo(blessedRepo, myClone)

        inRepo(anjasClone) {
            editAndCommit(anjasFile, 1)
            editAndCommit(anjasFile, 5)
            git("push")
        }

        doc("00-aufgabe.md") {
            markdown("""
                # Übung - Umgang mit "Push Reject"
                
                Wenn mehrere Entwickler am selben Projekt arbeiten,
                kommt es beim `git push` häufig zu der Meldung
                `error: failed to push some refs ...`,
                dem sogenannten *Push Reject*.
                
                **Das ist nicht schlimm.** 
                Es bedeutet lediglich, dass im `origin`-Repository
                Commits gefunden wurden, 
                die lokal noch nicht integriert sind.

                Man mit `git pull` kann man die Änderungen holen und integrieren.
                
                Oder man kann mit `git fetch` die Änderungen zunächst nur abholen,
                um sie zu untersuchen, und später zu entscheiden,
                was man damit machen möchte.

                ## Setup
    
                 * Du arbeitest an einem Projekt `$projectName`,
                   das von Deiner Kollegin Anja erstellt wurde.
                 * Du sollst die Datei `$myFile` verbessern,
                   während Anja an der Datei `$anjasFile` weiterarbeitet.

                Wenn Anja ihre Änderungen vor Dir hochlädt,
                wirst Du einen *Push Reject* erfahren.
                
                ### Verzeichnisse

                 * Das Übungsverzeichnis ist `${rootDir.name}`
                   - Das gemeinsame Projekt liegt in `$blessedRepo`
                   - Anja arbeitet in  `$anjasClone`
                   - **Du arbeitest in `$myClone`**
            """)
        }


        inRepo(myClone) {
            createAufgabe(
                    "Lokal Commit(s) erstellen",
                    """
                    Bearbeite die Datei `bar` und erstelle (mindestens) ein Commit mit den Änderungen.
                    Überprüfe danach mit `git status`, ob der Workspace sauber ist.
                    """
            ) {
                editAndCommit(myFile, 1)
                git("status")
            }

            createAufgabe(
                    "Push versuchen",
                    """Versuche jetzt die Änderung zu pushen."""
            ) {
                git("push", setOf(1))
                markdown("""
                    Wie Du siehst, der Push wurde verweigert. 
                    Anscheinend war Anja schneller,
                    und hat ihre Änderungen zuerst nach `blessed.git` gebracht.
                """)
            }

            createAufgabe(
                    "(optional) Problem analysieren"
                    , """
                    Hole zunächt die Änderungen, ohne zu integrieren (`fetch`),
                    und lasse Dir die Änderungen von *Anja* zeigen.
                    """
            ) {
                git("fetch")
                git("log --oneline HEAD..origin/master")
                git("diff --stat HEAD origin/master")
                git("diff --stat HEAD...origin/master")
            }

            createAufgabe(
                    "Fremde Änderungen integrieren",
                    """
                    
                    """
            ) {
                git("pull")
                markdown("""
                    Da *Anja* eine andere Datei (`$anjasFile`) bearbeitet hat,
                    konnten ihre Änderungen problemlos integriert werden.
                    Man sieht, dass ein neues Commit entstanden ist,
                    welches die Stränge zusammenführt.
                """)
                git("log --graph --oneline")
            }

            createAufgabe(
                    "Erneut pushen",
                    """
                    
                    """
            ) {
                git("push")
                markdown("""
                    Und siehe da: Jetzt klappt's.
                """)
            }
        }

    }
}

