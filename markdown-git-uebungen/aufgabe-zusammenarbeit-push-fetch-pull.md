---
layout: page
title: <code>zusammenarbeit-push-fetch-pull</code>
parent: Aufgaben

---
# Übung - Fetch und Pull


## Tipps

* `git fetch` holt Änderungen, ohne zu integrieren
* `git status` zeigt unterschiede am aktuellen Branch
* `git branch -r` zeigt, welche Branches es Remote gibt.
* `git pull` integriert (erst Fetch, dann Merge)
* `git log --graph --oneline` zeigt den Graphen mit Merges

## Setup
                  


<pre><code>$ <b>cd mein-klon</b><br><br><br></code></pre>


<!--UEB-Fetch und Pull--><h2>Schritt 1 - Änderungen holen</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/zusammenarbeit-push-fetch-pull/mein-klon`.

Hole die beiden neuen Commits vom `origin`-Repository,
ohne den lokalen `master` zu verändern.

<!--UEB-Fetch und Pull--><h2>Schritt 2 - Änderungen untersuchen</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/zusammenarbeit-push-fetch-pull/mein-klon`.

Lasse dir den Status zeigen,
und untersuche dann,
welche Commits im `master` des `origin`-Repository vorhanden sind,
welche im lokalen `master` noch nicht integriert wurden..

<!--UEB-Fetch und Pull--><h2>Schritt 3 - Änderungen integrieren</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/zusammenarbeit-push-fetch-pull/mein-klon`.

Integriere die neuesten Commits vom `origin`-Repository
in den lokalen `master`.


<pre><code>mein-klon $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-zusammenarbeit-push-fetch-pull.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

