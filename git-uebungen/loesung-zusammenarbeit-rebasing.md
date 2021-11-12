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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* b55b40b (HEAD -&gt; feature) : Feature weitermachen.<br>* 160e61a : Feature anfangen.<br>| * e7d9c22 (master) : Neuerung auf dem master<br>|/  <br>* 684e919 Created file bar on branch master by bstachmann.<br>* d8650f1 Created file foo on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 9d05f69 (HEAD -&gt; feature) : Feature weitermachen.<br>* aacf5b7 : Feature anfangen.<br>* e7d9c22 (master) : Neuerung auf dem master<br>* 684e919 Created file bar on branch master by bstachmann.<br>* d8650f1 Created file foo on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

