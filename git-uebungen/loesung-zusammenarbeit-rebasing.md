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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 57b52cf (HEAD -&gt; feature) : Feature weitermachen.<br>* 954ae71 : Feature anfangen.<br>| * cca5958 (master) : Neuerung auf dem master<br>|/  <br>* 5724259 Created file bar on branch master by bstachmann.<br>* be0b799 Created file foo on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 39c2c93 (HEAD -&gt; feature) : Feature weitermachen.<br>* 6ddf7ff : Feature anfangen.<br>* cca5958 (master) : Neuerung auf dem master<br>* 5724259 Created file bar on branch master by bstachmann.<br>* be0b799 Created file foo on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

