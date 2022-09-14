---
layout: page
title: <code>zusammenarbeit-rebasing</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Feature-Branch per Rebase aktualiseren.

Auf dem master gibt es Neuerungen.

Lasse Dir den Commit-Graphen über alle Branches zeigen.

Aktualisiere Deinen Feature-Branch.

Lasse Dir den Commit-Graphen über alle Branches zeigen.


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 4ae4d54 (HEAD -&gt; feature) : Feature weitermachen.<br>* fed72f5 : Feature anfangen.<br>| * 5d2d4bf (master) : Neuerung auf dem master<br>|/  <br>* 0ff0778 Created file bar on branch master by bjoern.<br>* dcf127a Created file foo on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>[KSuccessfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 2c115b2 (HEAD -&gt; feature) : Feature weitermachen.<br>* 1633ec3 : Feature anfangen.<br>* 5d2d4bf (master) : Neuerung auf dem master<br>* 0ff0778 Created file bar on branch master by bjoern.<br>* dcf127a Created file foo on branch master by bjoern.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

