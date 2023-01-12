---
layout: page
title: <code>repository-log</code>
parent: Aufgaben

---
# Übung - Repository - Log

Das Log repräsentiert die Geschichte des Projekts
als Folge von Commits.
Jedes Commit repräsentiert einen Stand aller Dateien des Projekts.
Hier wird geübt, das Log zu Untersuchen und zu Lesen.


## Tipps

* `git log` zeigt alles Commits, die im aktuellen Branch enthalten sind.
  - `--oneline` macht die Ausgabe kompakter.
  - `--stat` zeigt wie viele Dateien in welcher Date geändert wurden.
* `git show <some-commit>` zeigt Details zu einem Commit
* Mit `~` Adressiert man Vorgänger eines Commits, 
  z. B. ist `HEAD~2` der Vorvorgänger von `HEAD`.
* `git ls-tree -r <commit>` listet alles Dateien auf, die im angegebenen
  Commit versioniert sind.
* Mit `blame` findet man heraus,in welchen Commit Zeilen zuletzt bearbeitet wurden.
  - `-M` ermittelt Verschiebungen innerhalb einer Datei. 
  - `-w` erkennt Zeilen wieder, auch wenn Whitespacing verändert wurde.
  - `--show-number` zeigt vorherige Zeilennummern.
  - `-C` ermittelt Kopien/Verschiebungen aus Dateien im selben  Commit, in dem die Zeile bearbeitet wurde,
    `-C -C -C` sogar aus beliebigen Dateien.

# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
untersucht zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


<!--UEB-Repository - Log--><h2>Schritt 1 - Verzeichnisstruktur</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/repository-log/repo`.

Untersuche das Projektverzeichnis.
Welche Dateien gibt es im Workspace? Welche Verzeichnisse?
Wo liegt das Repository?

<!--UEB-Repository - Log--><h2>Schritt 2 - Commits ansehen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/repository-log/repo`.

Sieh Dir die Commits. 
Achte dabei auf die angezeigten Branches und Tags.

<!--UEB-Repository - Log--><h2>Schritt 3 - ⭐ Commits ansehen: Datei-Statistik</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/repository-log/repo`.

Sieh Dir die Commits an. 
Lase dir dabei die Statistik anzeigen, 
d.h. wie viele Zeilen in welcher Datei geändert wurden.

<!--UEB-Repository - Log--><h2>Schritt 4 - Einzelne Commits untersuchen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/repository-log/repo`.

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0

<!--UEB-Repository - Log--><h2>Schritt 5 - Inhalte vergangener Versionen untersuchen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/repository-log/repo`.

Lasse Dir anzeigen welche Dateien es im vorigen Commit gab.

Gebe den Inhalt der Datei `bar` so aus,  wie er im vorigen Commit war.

<!--UEB-Repository - Log--><h2>Schritt 6 - ⭐ Herkunft von Zeilen ermitteln</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/repository-log/repo`.

Es geht darum für die Datei `nachher` Folgendes zu ermitteln:

* Für jede Zeile zeigen, in welchem Commit sie zuletzt bearbeitet wurde.
* Innerhalb der Datei wurden Zeilen verschoben. Welche?
* Es wurden auch Zeilen aus anderen Dateien verschoben und kopiert. Welche?


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-repository-log.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

