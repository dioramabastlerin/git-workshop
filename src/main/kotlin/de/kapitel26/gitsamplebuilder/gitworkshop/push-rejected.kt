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
                |# Übung - Umgang mit "Push Reject"
                |
                |Wenn mehrere Entwickler per `push` Commits
                |in das selbe Repository hochladen kommt es
                |häufig zu sogenannten "Push Rejects"
                |
                |In diesem Beispiel arbeitest Du in einem Klon `repo`
                |des Projekts `blessed.git`.
                |
                |Auch eine andere Entwicklerin, *Anja*,
                |arbeitet mit einem Klon von `blessed.git`.
                |Wenn *Anja* ihre Änderungen vor Dir hochlädt,
                |wirst Du einen "Push Reject" erfahren.
            """)
        }


        createAufgabe(
                "Lokal Commit(s) erstellen", """
                    |Bearbeite die Datei `bar` und erstelle (mindestens) ein Commit mit den Änderungen.
                    |Überprüfe danach mit `git status`, ob der Workspace sauber ist.
        """) {
            inRepo {
                editAndCommit("bar", 1)
                git("status")
            }
        }

        createAufgabe(
                "Push versuchen", """
                    |Versuche jetzt die Änderung zu pushen.
        """) {
            inRepo {
                git("push", setOf(1))
                markdown("""
                    |Wie Du siehst, der Push wurde verweigert. 
                    |Anscheinend war *Anja* schneller,
                    |und hat ihre Änderungen zuerst nach `blessed.git` gebracht.""")
            }
        }

        createAufgabe(
                "(optional) Problem analysieren", """
                    | Hole zunächt die Änderungen, ohne zu integrieren (`fetch`),
                    | und lasse Dir die Änderungen von *Anja* zeigen.
        """) {
            inRepo {
                git("fetch")
                git("diff --stat HEAD origin/master")
                git("diff --stat HEAD...origin/master")
            }
        }

        createAufgabe(
                "Fremde Änderungen integrieren", """
                    |
        """) {
            inRepo {
                git("pull")
                markdown("""
                    |Da *Anja* eine andere Datei (`foo`) bearbeitet hat,
                    |konnten ihre Änderungen problemlos integriert werden.
                    |Man sieht, dass ein neues Commit entstanden ist,
                    |welches die Stränge zusammenführt.
                """)
                git("log --graph --oneline")
            }
        }

        createAufgabe(
                "Erneut pushen", """
                    |
        """) {
            inRepo {
                git("push")
                markdown("""
                    |Und siehe da: Jetzt klappt's.
                """)
            }
        }

    }
}