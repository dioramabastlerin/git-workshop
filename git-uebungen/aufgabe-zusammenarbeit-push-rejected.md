---
layout: page
title: <code>zusammenarbeit-push-rejected</code>
parent: Aufgaben

---
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

 * Du arbeitest an einem Projekt `apollo`,
   das von Deiner Kollegin Anja erstellt wurde.
 * Du sollst die Datei `frontend.java` verbessern,
   während Anja an der Datei `backend.java` weiterarbeitet.

Wenn Anja ihre Änderungen vor Dir hochlädt,
wirst Du einen *Push Reject* erfahren.

### Verzeichnisse

 * `./` Haupverzeichnis für diese Übung 
   - `blessed-apollo.git/` Das geteilte (blessed) Repository liegt hier.
   - `anjas-apollo/` Hier arbeitet Anja.  
   - `my-apollo/` **Du arbeitest hier.**


<pre><code>$ <b>cd my-apollo</b><br><br><br></code></pre>


<!--UEB-Umgang mit "Push Reject"--><h2>Schritt 1 - Lokal Commit(s) erstellen</h2>

Starte im Verzeichnis `aufgaben/my-apollo`.

Bearbeite die Datei `frontend.java` und erstelle (mindestens) ein Commit mit den Änderungen.
Überprüfe danach mit `git status`, ob der Workspace sauber ist.

<!--UEB-Umgang mit "Push Reject"--><h2>Schritt 2 - Push versuchen</h2>

Starte im Verzeichnis `aufgaben/my-apollo`.

Versuche jetzt Deine Änderungen zu pushen.

<!--UEB-Umgang mit "Push Reject"--><h2>Schritt 3 - (optional) Problem analysieren</h2>

Starte im Verzeichnis `aufgaben/my-apollo`.

Hole zunächt die Änderungen, ohne zu integrieren (`fetch`),
und lasse Dir die Änderungen von *Anja* zeigen.
 
 * Welche Commits hat Anja gemacht (`log`)?
 * Welche Unterschiede gibt es zweichen deiner und Anjas Version (symmetrisches `diff`)?
 * Welche Änderungen hat Anja gemacht (asymmetrisches `diff`)?

<!--UEB-Umgang mit "Push Reject"--><h2>Schritt 4 - Fremde Änderungen integrieren</h2>

Starte im Verzeichnis `aufgaben/my-apollo`.

Integriere die Änderungen mit Pull und sieh Dir dann den Commit-Graphen an.

<!--UEB-Umgang mit "Push Reject"--><h2>Schritt 5 - Erneut pushen</h2>

Starte im Verzeichnis `aufgaben/my-apollo`.

                    


<pre><code>my-apollo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-zusammenarbeit-push-rejected.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

