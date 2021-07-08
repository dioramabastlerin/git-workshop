---
layout: page
title: <code>zusammenarbeit-push-fetch-pull</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Änderungen holen

Hole die beiden neuen Commits vom `origin`-Repository,
ohne den lokalen `master` zu verändern.


<pre><code>mein-klon $ <b>git fetch</b><br><br>From ../blessed<br>   7c68c28..b486d2c  master     -&gt; origin/master<br><br></code></pre>


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


<pre><code>mein-klon $ <b>git log master..origin/master</b><br><br>commit b486d2c8553b82200c93b6756606a0e6f2c81366<br>Author: bstachmann &lt;egal&gt;<br>Date:   Thu Jul 8 20:41:17 2021 +0200<br><br>    : Second edit after cloning<br><br>commit 111964607bac4fd9e51ee2d41bd66fbe16f76012<br>Author: bstachmann &lt;egal&gt;<br>Date:   Thu Jul 8 20:41:17 2021 +0200<br><br>    : First edit after cloning<br><br></code></pre>


Die `..`-Notation zeigt genau jene Commits,
die in `origing/master` aber noch nicht in `master` enthalten sind.
Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
könne, da wir `master` ja gerade `HEAD` ist.

## Lösung zu Schritt 3 - Änderungen integrieren

Integriere die neuesten Commits vom `origin`-Repository
in den lokalen `master`.


<pre><code>mein-klon $ <b>git pull</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br><br></code></pre>



<pre><code>mein-klon $ <b>git log --graph --oneline</b><br><br>*   4e7e3ad Merge branch 'master' of ../blessed<br>|\  <br>| * b486d2c : Second edit after cloning<br>| * 1119646 : First edit after cloning<br>* | 28de668 : My local edit<br>|/  <br>* 7c68c28 Initial edit before cloning<br>* 07b7373 Initial edit before cloning<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-push-fetch-pull.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

