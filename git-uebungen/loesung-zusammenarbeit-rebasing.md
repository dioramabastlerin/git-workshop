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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 6b492a1 (HEAD -&gt; feature) : Feature weitermachen.<br>* 9937ea1 : Feature anfangen.<br>| * 683da94 (master) : Neuerung auf dem master<br>|/  <br>* a97d0c4 Created file bar on branch master by bstachmann.<br>* 51000d8 Created file foo on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 7abf6fa (HEAD -&gt; feature) : Feature weitermachen.<br>* b2fcc16 : Feature anfangen.<br>* 683da94 (master) : Neuerung auf dem master<br>* a97d0c4 Created file bar on branch master by bstachmann.<br>* 51000d8 Created file foo on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

