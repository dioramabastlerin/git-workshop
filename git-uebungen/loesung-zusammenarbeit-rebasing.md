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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* ca30042 (master) : Neuerung auf dem master<br>| * 7baffc3 (HEAD -&gt; feature) : Feature weitermachen.<br>| * 76ff285 : Feature anfangen.<br>|/  <br>* e54ac5a Created file bar on branch master by bstachmann.<br>* 84890d5 Created file foo on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* dc138ba (HEAD -&gt; feature) : Feature weitermachen.<br>* 373a9e4 : Feature anfangen.<br>* ca30042 (master) : Neuerung auf dem master<br>* e54ac5a Created file bar on branch master by bstachmann.<br>* 84890d5 Created file foo on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

