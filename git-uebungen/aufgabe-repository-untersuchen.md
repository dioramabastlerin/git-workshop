---
layout: page
title: <code>repository-untersuchen</code>
parent: Aufgaben

---
# Übung - Repository untersuchen

Hier geht es darum, herauszufinden, was in einem Repository enthalten ist.

## Tipps

* `git log` zeigt alles Commits, die im aktuellen Branch enthalten sind.
  - `--oneline` macht die Ausgabe kompakter.
* `git show <some-commit>` zeigt Details zu einem Commit
* Mit `~` Adressiert man Vorgänger eines Commits, 
  z. B. ist `HEAD~2` der Vorvorgänger von `HEAD`.
* `git branch` und `git tag` listen vorhandene Branches und Tags auf.
* Mit `git switch <branch-name>` kann man auf andere Branches wechseln.
* Mit `git switch --detach <commit>` kann man auf beliebige Versionen wechseln.
* Mit `blame` findet man heraus,in welchen Commit Zeilen zuletzt bearbeitet wurden.
  - `-M` ermittelt Verschiebungen innerhalb einer Datei. 
  - `-w` erkennt Zeilen wieder, auch wenn Whitespacing verändert wurde.
  - `--show-number` zeigt vorherige Zeilennummern.
  - `-C` ermittelt Kopien/Verschiebungen aus Dateien im selben  Commit, in dem die Zeile bearbeitet wurde,
    `-C -C -C` sogar aus beliebigen Dateien.
* Mit `git restore -s <commit> -- <datei-oder-pfad>` kann man *Inhalte* beliebiger Versionen 
  von Dateien/Pfaden in den Workspace holten. Es wird dabei nicht auf das angegebenen Commit
  gewechselt, sondern nur Dateiinhalte in den Workspace geholt. Die betroffenen Dateien 
  werden als `modified` angezeigt und können Commited werden.
   
# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
untersucht zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


<!--UEB-Repository untersuchen--><h2>Schritt 1 - Verzeichnisstruktur</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Untersuche das Projektverzeichnis.

<!--UEB-Repository untersuchen--><h2>Schritt 2 - Commits ansehen</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Sieh Dir die Commits an und lasse dabei Informationen 
zu Branches und Tags mit anzeigen.

<!--UEB-Repository untersuchen--><h2>Schritt 3 - Einzelne Commits untersuchen</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0

<!--UEB-Repository untersuchen--><h2>Schritt 4 - Inhalte vergangener Versionen untersuchen</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.

Gebe den Inhalt der Datei `bar`,  wie er im vorigen Commit war. aus.

Wechsle zum vorigen Commit, und untersuche, wie der Workspace dannn aussieht.
Wechsle dann wieder auf `master` zurück.

<!--UEB-Repository untersuchen--><h2>Schritt 5 - Branches und Tags</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Zeige die Branches und Tags an.
Zeige jetzt den Commit-Graphen über alle Branches an.

<!--UEB-Repository untersuchen--><h2>Schritt 6 - ⭐ Herkunft von Zeilen ermitteln</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Es geht darum für die Datei `nachher` Folgendes zu ermitteln:

* Für jede Zeile zeigen, in welchem Commit sie zuletzt bearbeitet wurde.
* Innerhalb der Datei wurden Zeilen verschoben. Welche?
* Es wurden auch Zeilen aus anderen Dateien verschoben und kopiert. Welche?

<!--UEB-Repository untersuchen--><h2>Schritt 7 - ⭐ Hole alten Stand einer einzelnen Datei zurück.</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/repository-untersuchen/repo`.

Die Datei `hallo-welt` wurde nach dem `release1.0` bearbeitet.
Dem Kunden gefällt das nicht. Stelle den alten Zustand mit
einem neuen Commit wieder her. 


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-repository-untersuchen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

