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
* `git branch` und `git tag` listen vorhande Branches und Tags auf.
* Mit `A..B` Adressiert man Commit, die auf dem Weg
  von `A` nach `B` hinzukommen. 
* Mit `blame` findet man heraus,in welchen Commit Zeilen zuletzt bearbeitet wurden.
  - `-M` ermittelt Verschiebungen innerhalb einer Datei. 
  - `-w` erkennt Zeilen wieder, auch wenn Whitespacing verändert wurde.
  - `--show-numbers` zeigt vorherige Zeilennummern.
  - `-C` ermittelt Kopien/Verschiebungen aus Dateien im selben  Commit, in dem die Zeile bearbeitet wurde,
    `-C -C -C` sogar aus beliebigen Dateien.
   
# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
untersucht zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


<!--UEB-Repository untersuchen--><h2>Schritt 1 - Verzeichnisstruktur</h2>

Starte im Verzeichnis `aufgaben/repo`.

Untersuche das Projektverzeichnis.

<!--UEB-Repository untersuchen--><h2>Schritt 2 - Commits ansehen</h2>

Starte im Verzeichnis `aufgaben/repo`.

Sieh Dir die Commits an und lasse dabei Informationen 
zu Branches und Tags mit anzeigen.

<!--UEB-Repository untersuchen--><h2>Schritt 3 - Einzelne Commits untersuchen</h2>

Starte im Verzeichnis `aufgaben/repo`.

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0

<!--UEB-Repository untersuchen--><h2>Schritt 4 - Inhalte vergangener Versionen untersuchen</h2>

Starte im Verzeichnis `aufgaben/repo`.

Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.

Gebe den Inhalt der Datei `bar`,  wie er im vorigen Commit war. aus.

Hole die (ganze) vorige Version in den Workspace, um sie näher zu untersuchen.

<!--UEB-Repository untersuchen--><h2>Schritt 5 - Branches und Tags</h2>

Starte im Verzeichnis `aufgaben/repo`.

Zeige die Branches und Tags an.
Zeige jetzt den Commit-Graphen über alle Branches an.

<!--UEB-Repository untersuchen--><h2>Schritt 6 - ⭐ Herkunft von Zeilen ermitteln</h2>

Starte im Verzeichnis `aufgaben/repo`.

Es geht darum für die Datei `nachher` Folgendes zu ermitteln:

* Für jede Zeile zeigen, in welchem Commit sie zuletzt bearbeitet wurde.
* Innerhalb der Datei wurden Zeilen verschoben. Welche?
* Es wurden auch Zeilen aus anderen Dateien verschoben und kopiert. Welche?


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-repository-untersuchen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

