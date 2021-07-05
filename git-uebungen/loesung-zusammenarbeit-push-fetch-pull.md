---
layout: page
title: <code>zusammenarbeit-push-fetch-pull</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Änderungen holen

Hole die beiden neuen Commits vom `origin`-Repository,
ohne den lokalen `master` zu verändern.


<pre><code>mein-klon $ <b>git fetch</b><br><br>From ../blessed<br>   39b2e80..2b21b91  master     -&gt; origin/master<br><br></code></pre>


Die Ausgabe zeigt, dass Änderungen auf dem Branch `master` geholt wurden.


<pre><code>mein-klon $ <b>git status</b><br><br>On branch master<br>Your branch and 'origin/master' have diverged,<br>and have 1 and 2 different commits each, respectively.<br>  (use &quot;git pull&quot; to merge the remote branch into yours)<br><br>nothing to commit, working tree clean<br><br></code></pre>


## Lösung zu Schritt 2 - Änderungen untersuchen

Lasse dir den Status zeigen,
und untersuche dann,
welche Commits im `master` des `origin`-Repository vorhanden sind,
welche im lokalen `master` noch nicht integriert wurden..


<pre><code>mein-klon $ <b>git status</b><br><br>On branch master<br>Your branch and 'origin/master' have diverged,<br>and have 1 and 2 different commits each, respectively.<br>  (use &quot;git pull&quot; to merge the remote branch into yours)<br><br>nothing to commit, working tree clean<br><br></code></pre>


Der Status zeigt, dass es im Origin-Repo
(auf dem Branch `master`) zwei Commits gibt,
die wir noch nicht integriert haben.


<pre><code>mein-klon $ <b>git log master..origin/master</b><br><br>commit 2b21b91d4bf05ed0dc1ce76d6700532e7c8e3c0c<br>Author: bstachmann &lt;egal&gt;<br>Date:   Mon Jul 5 23:09:23 2021 +0200<br><br>    : Second edit after cloning<br><br>commit 227be7809b30a73bbcf4d292204919919324dc69<br>Author: bstachmann &lt;egal&gt;<br>Date:   Mon Jul 5 23:09:23 2021 +0200<br><br>    : First edit after cloning<br><br></code></pre>


Die `..`-Notation zeigt genau jene Commits,
die in `origing/master` aber noch nicht in `master` enthalten sind.
Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
könne, da wir `master` ja gerade `HEAD` ist.

## Lösung zu Schritt 3 - Änderungen integrieren

Integriere die neuesten Commits vom `origin`-Repository
in den lokalen `master`.


<pre><code>mein-klon $ <b>git pull</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br><br></code></pre>



<pre><code>mein-klon $ <b>git log --graph --oneline</b><br><br>*   207c8c0 Merge branch 'master' of ../blessed<br>|\  <br>| * 2b21b91 : Second edit after cloning<br>| * 227be78 : First edit after cloning<br>* | 2610748 : My local edit<br>|/  <br>* 39b2e80 Initial edit before cloning<br>* 5f22900 Initial edit before cloning<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-push-fetch-pull.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

