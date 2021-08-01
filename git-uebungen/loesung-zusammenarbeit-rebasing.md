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


<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 2789c43 (HEAD -&gt; feature) : Feature weitermachen.<br>* ac17a32 : Feature anfangen.<br>| * 7879504 (master) : Neuerung auf dem master<br>|/  <br>* a3f18c6 Created file bar on branch master by dioramabastlerin.<br>* baddef6 Created file foo on branch master by dioramabastlerin.<br><br></code></pre>



<pre><code>repo $ <b>git rebase master</b><br><br>Rebasing (1/2)<br>Rebasing (2/2)<br><br>                                                                                <br>Successfully rebased and updated refs/heads/feature.<br><br></code></pre>



<pre><code>repo $ <b>git log --graph --all --decorate --oneline</b><br><br>* 1d729e8 (HEAD -&gt; feature) : Feature weitermachen.<br>* 45af9ce : Feature anfangen.<br>* 7879504 (master) : Neuerung auf dem master<br>* a3f18c6 Created file bar on branch master by dioramabastlerin.<br>* baddef6 Created file foo on branch master by dioramabastlerin.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-rebasing.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

