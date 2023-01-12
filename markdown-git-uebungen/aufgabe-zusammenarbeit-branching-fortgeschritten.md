---
layout: page
title: <code>zusammenarbeit-branching-fortgeschritten</code>
parent: Aufgaben

---
# Übung - Branching (fortgeschritten)



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


<!--UEB-Branching (fortgeschritten)--><h2>Schritt 1 - Branch erstellen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching-fortgeschritten/repo`.

Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
und erstelle ein Commit.
Wechsle dann zurück auf den `master` und bearbeite dort `bar`.
Zeige den Commit-Graphen.

<!--UEB-Branching (fortgeschritten)--><h2>Schritt 2 - Branch mergen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching-fortgeschritten/repo`.

Merge `feature-a` auf den `master`und
zeige den Commit-Graphen.

<!--UEB-Branching (fortgeschritten)--><h2>Schritt 3 - ⭐ Merge analysieren</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching-fortgeschritten/repo`.

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.

<!--UEB-Branching (fortgeschritten)--><h2>Schritt 4 - ⭐ Merge analysieren</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching-fortgeschritten/repo`.

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.

<!--UEB-Branching (fortgeschritten)--><h2>Schritt 5 - Remote Branches untersuchen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/zusammenarbeit-branching-fortgeschritten/repo`.




<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-zusammenarbeit-branching-fortgeschritten.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

