---
layout: page
title: <code>commits-erstellen</code>
parent: Aufgaben

---
# Übung - Commits erstellen


## Tipps

* `git add <datei/verzeichnis>` 
   Vor einem Commit müssen Änderungen mit `add` im Staging-Bereich 
   (auch Index genannt) registriert werden
* `git commit -m 'Mein Senf'` Erstellt ein Commit mit allen 
   im Staging-Bereich registrierten Änderungen.
* `git commit -a` Regsitriert alle Änderungen an bereits in Git versionierten 
  Dateien im Staging-Bereich, so dass man sich den separaten
  `add`-Aufruf sparen kann.

# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
bearbeitet zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


## Schritt 1 - Commit - mit Staging

Starte im Verzeichnis `aufgaben/repo`.

Bearbeite die Datei `hallo-welt`,
füge sie mit `git add` zum Index hinzu (Staging)
und erstelle ein Commit mit diesen Änderungen.

## Schritt 2 - Commit - automatisches Staging

Starte im Verzeichnis `aufgaben/repo`.

Bearbeite die Datei `hallo-welt` erneut
und erstelle wieder ein Commit,
dieses mal mal aber mit `-a`.

## Schritt 3 - Commit - neue Datei

Starte im Verzeichnis `aufgaben/repo`.

Erstelle `new-world` und bestätige sie mit einem Commit.

## Schritt 4 - Commit - Datei löschen

Starte im Verzeichnis `aufgaben/repo`.

Lösche `hallo-welt` und bestätige dies per Commit.

## Schritt 5 - Commit - Datei verschieben/umbenennen

Starte im Verzeichnis `aufgaben/repo`.

Benenne die Datei `hello-world` in `renamed-world` um.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-commits-erstellen.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

