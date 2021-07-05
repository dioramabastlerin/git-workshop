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

# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
untersucht zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


## Schritt 1 - Verzeichnisstruktur

Starte im Verzeichnis `aufgaben/repo`.

Untersuche das Projektverzeichnis.

## Schritt 2 - Commits ansehen

Starte im Verzeichnis `aufgaben/repo`.

Sieh Dir die Commits an und lasse dabei Informationen 
zu Branches und Tags mit anzeigen.

## Schritt 3 - Einzelne Commits untersuchen

Starte im Verzeichnis `aufgaben/repo`.

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0

## Schritt 4 - Inhalte vergangener Versionen untersuchen

Starte im Verzeichnis `aufgaben/repo`.

Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.

Gebe den Inhalt der Datei `bar` in diese Version aus.

Hole diese Version in den Workspace, um sie näher zu untersuchen.

## Schritt 5 - Branches und Tags

Starte im Verzeichnis `aufgaben/repo`.

Zeige die Branches und Tags an.
Zeige jetzt den Commit-Graphen über alle Branches an.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-repository-untersuchen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

