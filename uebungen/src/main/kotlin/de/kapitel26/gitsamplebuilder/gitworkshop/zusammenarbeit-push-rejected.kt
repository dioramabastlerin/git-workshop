package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.pushRejected() {
    createAufgabenFolge("push-rejected") {

        val projectName = "apollo"
        val blessedRepo = "blessed-$projectName.git"
        val anjasClone = "anjas-$projectName"
        val myClone = "my-$projectName"
        val anjasFile = "backend.java"
        val myFile = "frontend.java"


        createIntro(
                """Umgang mit "Push Reject"""",
                """
                 
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

                ## Tipps
                
                 * `git push`: Überträgt Commits zum `origin`.
                 * `git fetch`: Holt Commit, ändert aber Workspace und lokalen Branch nicht
                 * `git pull`: Hold und **integriert** Commits.
                 * `git log A..B` zeigt "was B gemacht hat".\
                    Oder etwas genauer: Jenes Commits aus der Historie von `B`, 
                    die noch nicht in `A` enthalten sind.
                 * `git diff A B` zeigt die Unterschiede zwischen A und B\
                   (symmetrisches Diff)
                 * `git diff A...B` zeigt die Änderungen der Seite B.\
                    Oder etwas genauer: Das Diff zwischen dem letzen gemeinsamen Vorgänger zu B.\
                    (asymmetrisches Diff)

                ## Setup
    
                 * Du arbeitest an einem Projekt `$projectName`,
                   das von Deiner Kollegin Anja erstellt wurde.
                 * Du sollst die Datei `$myFile` verbessern,
                   während Anja an der Datei `$anjasFile` weiterarbeitet.

                Wenn Anja ihre Änderungen vor Dir hochlädt,
                wirst Du einen *Push Reject* erfahren.
                
                ### Verzeichnisse

                 * `${rootDir.name}/` Haupverzeichnis für diese Übung 
                   - `$blessedRepo/` Das geteilte (blessed) Repository liegt hier.
                   - `$anjasClone/` Hier arbeitet Anja.  
                   - `$myClone/` **Du arbeitest hier.**
            """
        ) {
            createRepo(blessedRepo, "--bare") {
                createClone("../$anjasClone") {
                    user("anja")
                    createFileAndCommit(anjasFile)
                    createFileAndCommit(myFile)
                    git("push")
                }

                createClone("../$myClone")
            }

            inRepo(anjasClone) {
                editAndCommit(anjasFile, 1)
                editAndCommit(anjasFile, 5)
                git("push")
            }

        }

        inRepo(myClone) {
            createAufgabe(
                    "Lokal Commit(s) erstellen",
                    """
                    Bearbeite die Datei `$myFile` und erstelle (mindestens) ein Commit mit den Änderungen.
                    Überprüfe danach mit `git status`, ob der Workspace sauber ist.
                    """
            ) {
                editAndCommit(myFile, 1)
                markdown("""Und jetzt noch eben prüfen, ob `working tree clean` ist.""")
                git("status")
            }

            createAufgabe(
                    "Push versuchen",
                    """Versuche jetzt Deine Änderungen zu pushen."""
            ) {
                git("push", setOf(1))
                markdown("""
                    Wie Du siehst, der Push wurde verweigert. 
                    Anscheinend war Anja schneller,
                    und hat ihre Änderungen zuerst nach `$blessedRepo` gepushed.
                """)
            }

            createAufgabe(
                    "(optional) Problem analysieren"
                    , """
                    Hole zunächt die Änderungen, ohne zu integrieren (`fetch`),
                    und lasse Dir die Änderungen von *Anja* zeigen.
                     
                     * Welche Commits hat Anja gemacht (`log`)?
                     * Welche Unterschiede gibt es zweichen deiner und Anjas Version (symmetrisches `diff`)?
                     * Welche Änderungen hat Anja gemacht (asymmetrisches `diff`)?
                    """
            ) {
                markdown("`fetch` holt die Daten, ohne den Workspace oder Deine lokalen Branches zu verändern.")
                git("fetch")
                markdown("Die Ausgabe zeigt, dass neue Commit für den `origin/master` geholt wurden")
                markdown("Die `..`-Notation zeigt, welche Commits hinzugekommen sind:")
                git("log --oneline master..origin/master")
                markdown("""
                    Das normale (symmetrische) Diff zeig alle Unterschiede. 
                    Sowohl das, was du gemacht hast, als auch das, was Anja gemacht hat:"
                """)
                git("diff --stat HEAD origin/master")
                markdown("""
                    Das asymmetrische Diff `...` zeigt nur jene Änderungen,
                    die Anja gemacht hat
                    (bezogen auf den letzten gemeinsamen Vorgänger):"
                """)
                git("diff --stat HEAD...origin/master")
            }

            createAufgabe(
                    "Fremde Änderungen integrieren",
                    """
                    Integriere die Änderungen mit Pull und sieh Dir dann den Commit-Graphen an.
                    """
            ) {
                git("pull")
                markdown("""
                    Da *Anja* eine andere Datei (`$anjasFile`) bearbeitet hat als Du (`$myFile`),
                    konnten ihre Änderungen problemlos integriert werden.
                    Man sieht, dass ein neues Commit entstanden ist,
                    welches die Stränge zusammenführt.
                """)
                git("log --graph --oneline")

                markdown("#### Achtung: Beim `pull` kann es Merge-Konflikte geben ...")
                markdown("""
                    ... wenn beide Seiten dieselben Stellen bearbeitet haben.
                    Das Auflösen von Merge-Konflikten ist Thema eines folgenden Kapitels.
                """.trimIndent())
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

                markdown("#### Achtung: Falls schon wieder jemand schneller war ...")
                markdown("""
                    ... und nach $blessedRepo gepushed hat,
                    kann es nochmal ein *Push Reject* geben,
                    und wir versuchen erneut ein `pull`, dann ein `push`,
                    solange, bis es klapp.
                """)
            }
        }

    }
}

