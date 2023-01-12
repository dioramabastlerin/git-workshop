---
layout: page
title: <code>zusammenarbeit-branching</code>
parent: Aufgaben

---
# Übung - Branching



## Infos

* `git branch` 

## Tipps

* `git switch -c <name>` erzeugt einen neuen Branch und aktiviert 
  diesen sogleich.
* `git branch -vv` zeigt Details zu den lokalen Branches
* `git switch <name>` wechselt den aktiven Branch
* `git merge <branch>` integriert die Änderungen von `branch` und erstellt ein Commit
   auf dem aktiven Branch.
* 

  
## Ausgangssituation



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


<!--UEB-Branching--><h2>Schritt 1 - Branch erstellen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching/repo`.

Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
und erstelle ein Commit.
Wechsle dann zurück auf den `master` und bearbeite dort `bar`.
Zeige den Commit-Graphen.

<!--UEB-Branching--><h2>Schritt 2 - Branch mergen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching/repo`.

Merge `feature-a` auf den `master`und
zeige den Commit-Graphen.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-zusammenarbeit-branching.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

