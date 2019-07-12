package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples

fun CollectionOfSamples.pushRejected() {
    createAufgabenFolge("push-rejected") {

        createRepo("blessed.git", "--bare")

        createClonedRepo("blessed.git", "anderer-klon") {
            createFileAndCommit("foo")
            createFileAndCommit("bar")
            git("push")
        }

        createClonedRepo("blessed.git")

        inRepo("anderer-klon") {
            editAndCommit("foo", 1)
            editAndCommit("foo", 5)
            git("push")
        }

        doc("00-aufgabe.md") {
            markdown("""
                # Übung - Umgang mit "Push Reject"
                
                Wenn mehrere Entwickler am selben Projekt arbeiten,
                kommt es beim `git push` häufig zu der Meldung
                `error: failed to push some refs ...`,
                dem sogenannten *Push Reject*.
                
                Das ist nicht schlimm. 
                Es bedeutet lediglich, dass im `origin`-Repository
                Commits gefunden wurden, 
                die lokal noch nicht integriert sind.

                ## Setup

                Wenn *Anja* ihre Änderungen vor Dir hochlädt,
                wirst Du einen "Push Reject" erfahren.
                
                 * `blessed.git`: Hier liegt das projekt.
                 * `repo`: In diesem Klon arbeitest Du.
                 * `anderer-klon`: Hier arbeitet Anja.
            """)
        }


        inRepo {
            createAufgabe(
                    "Lokal Commit(s) erstellen",
                    """
                    |Bearbeite die Datei `bar` und erstelle (mindestens) ein Commit mit den Änderungen.
                    |Überprüfe danach mit `git status`, ob der Workspace sauber ist.
                    """
            ) {
                editAndCommit("bar", 1)
                git("status")
            }

            createAufgabe(
                    "Push versuchen", """
                    |Versuche jetzt die Änderung zu pushen.
        """) {
                git("push", setOf(1))
                markdown("""
                    |Wie Du siehst, der Push wurde verweigert. 
                    |Anscheinend war *Anja* schneller,
                    |und hat ihre Änderungen zuerst nach `blessed.git` gebracht.""")
            }

            createAufgabe(
                    "(optional) Problem analysieren", """
                    | Hole zunächt die Änderungen, ohne zu integrieren (`fetch`),
                    | und lasse Dir die Änderungen von *Anja* zeigen.
        """) {
                git("fetch")
                git("diff --stat HEAD origin/master")
                git("diff --stat HEAD...origin/master")
            }

            createAufgabe(
                    "Fremde Änderungen integrieren", """
                    |
        """) {
                git("pull")
                markdown("""
                    |Da *Anja* eine andere Datei (`foo`) bearbeitet hat,
                    |konnten ihre Änderungen problemlos integriert werden.
                    |Man sieht, dass ein neues Commit entstanden ist,
                    |welches die Stränge zusammenführt.
                """)
                git("log --graph --oneline")
            }

            createAufgabe(
                    "Erneut pushen", """
                    |
        """) {
                git("push")
                markdown("""
                    |Und siehe da: Jetzt klappt's.
                """)
            }
        }

    }
}