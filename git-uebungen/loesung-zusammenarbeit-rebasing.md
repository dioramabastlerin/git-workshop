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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* cc924e8 (HEAD -&gt; feature) : Feature weitermachen.<br>* 1da248d : Feature anfangen.<br>| * 04c5175 (master) : Neuerung auf dem master<br>|/  <br>* 4c0af20 Created file bar on branch master by bstachmann.<br>* b07f72d Created file foo on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 9c35f64 (HEAD -&gt; feature) : Feature weitermachen.<br>* 543aa5b : Feature anfangen.<br>* 04c5175 (master) : Neuerung auf dem master<br>* 4c0af20 Created file bar on branch master by bstachmann.<br>* b07f72d Created file foo on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

