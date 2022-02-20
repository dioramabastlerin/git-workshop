---
layout: page
title: <code>zusammenarbeit-reverting</code>
parent: Aufgaben

---
# Übung - Reverting

Git ermöglicht es, Änderungen, 
die in einem früheren Commits gemacht wurden,
durch ein neues Commit wieder rückgängig zu machen.

## Infos

* `git revert <commit>` erstellt ein neues Commit, 
  welches die Änderungen eines früheren Commmits wieder
  rückgängig macht.

* Bei einem Merge-Commit
  muss man zusätzlich Angeben auf welchen Merge-Parent
  sich die Ermittlung der Änderungen beziehen soll:<br/>
  `git revert -m 1 <merge-commit>` 

* Bei weiter zurückliegenden Commits kann es zu Konflikten kommen
  (weil betroffene Dateien in späteren Commits weiter bearbeitet wurden).
  Diese müssen dann wie bei Merge-Konflikten aufgelöst werden.

## Zum Beispielsetup

Im Beispielrepository wurde eine Datei umbennant,
das entsprechende Commit ist mit `umbenennung` getagged.

Außerdem wurde ein Feature-Branch per Merge integriert.
Das Merge-Commit ist mit `feature-merge` getagged.

Beide Änderungen sollen zurückgenommen werden.

[Zur Lösung](loesung-zusammenarbeit-reverting.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

