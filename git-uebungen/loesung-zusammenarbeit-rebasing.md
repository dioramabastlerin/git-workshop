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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* a5f2ccb (HEAD -&gt; feature) : Feature weitermachen.<br>* 9d60721 : Feature anfangen.<br>| * ba8d2e0 (master) : Neuerung auf dem master<br>|/  <br>* 81cf6e6 Created file bar on branch master by bstachmann.<br>* 6851340 Created file foo on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 48f0617 (HEAD -&gt; feature) : Feature weitermachen.<br>* c40707d : Feature anfangen.<br>* ba8d2e0 (master) : Neuerung auf dem master<br>* 81cf6e6 Created file bar on branch master by bstachmann.<br>* 6851340 Created file foo on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

