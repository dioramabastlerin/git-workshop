---
layout: page
title: <code>zusammenarbeit-reverting</code>
parent: Aufgaben

---
# Übung - Reverting

Git ermöglicht es Änderungen, 
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


<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


<!--UEB-Reverting--><h2>Schritt 1 - Einzelnes Commit rückgängig machen.</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-reverting/repo`.

In der Historie wurde die Datei `README.md` in `liesmich`
umbenannt. Mache diese Änderung rückgängig.
Tipp: Das Commit ist als `umbenennung` getagged.

<!--UEB-Reverting--><h2>Schritt 2 - Merge-Commit rückgängig machen.</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-reverting/repo`.

In der Historie wurde ein Feature-Branch per Merge integriert.file . Mache dies änderung rückgängig.
Tipp: Das Merge-Commit ist als `feature-merge` getagged. 
Tipp: Am verschinden der Datei `from-feature` kann man den Erfolg erkennen.

<!--UEB-Reverting--><h2>Schritt 3 - Feature-Branch kaputt!?</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-reverting/repo`.

Ein Revert wird oft genutzt, um ein Feature kurzfristig zurückzunehmen,
z. B. wegen eine Produktionsproblems.
Später möchte man den Feature-Branch korrigieren und dann erneut integrieren. 
Das geht nicht so ohne weiteres.

Im Beispiel hat der Branch `feature` eine Weiterentwicklung erfahren.
Versuche ihn erneut zu Mergen.
Untersuche die Fehlermeldung und den Commit-Graphen.
Tipp: Das gescheiterte Merge kann mit `git merge --abort` abgebrochen werden.

<!--UEB-Reverting--><h2>Schritt 4 - Merge-Revert wieder rückgängig machen.</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-reverting/repo`.

Der Trick ist, das Revert-Commit selbst zu reverten,
dann sind jene Änderungen wieder da,
auf denen die Weiterentwicklung des Features basiert.

Tipp: Das Commit ist als `feature-merge` getagged.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-zusammenarbeit-reverting.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

