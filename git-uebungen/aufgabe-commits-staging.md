---
layout: page
title: <code>commits-staging</code>
parent: Aufgaben

---
# Übung - Staging

Es geht darum, den *Stage-Bereich*
(auch Index genannt)

## Tipps

* `git add <datei/verzeichnis>` 
   überträgt die Stand einer Datei in den Stage-Bereich.
*  Ändert man eine Datei nach dem `add`, hat sie
   im Workspace einen anderen Stand als in der Stage.
* `git status`, `git diff` und `git diff --staged` zeigen dies.
* `git restore --staged <file>` nimmt ein Staging zurück.


# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
bearbeitet zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


## Schritt 1 - Staging

Starte im Verzeichnis `aufgaben/repo`.

Ersetze in der Datei `demo`,
`Fit` durch `Git`.
Füge sie dann zu Stage-Bereich hinzu.
Ersetze dann `doof` durch `toll`.
Lasse dir den Status und die Diffs
für Workspace und Stage zeigen.

## Schritt 2 - Restore

Starte im Verzeichnis `aufgaben/repo`.

Zurücknehmen eines Stagings. 


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-commits-staging.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

