---
layout: page
title: <code>zusammenarbeit-push-fetch-pull</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Änderungen holen

Hole die beiden neuen Commits vom `origin`-Repository,
ohne den lokalen `main` zu verändern.


<pre><code>mein-klon $ <b>git fetch</b><br><br>From ../blessed<br>   6014eb9..0b4e6c2  main       -&gt; origin/main<br><br></code></pre>


Die Ausgabe zeigt, dass Änderungen auf dem Branch `main` geholt wurden.


<pre><code>mein-klon $ <b>git status</b><br><br>On branch main<br>Your branch and 'origin/main' have diverged,<br>and have 1 and 2 different commits each, respectively.<br>  (use &quot;git pull&quot; to merge the remote branch into yours)<br><br>nothing to commit, working tree clean<br><br></code></pre>


## Lösung zu Schritt 2 - Änderungen untersuchen

Lasse dir den Status zeigen,
und untersuche dann,
welche Commits im `main` des `origin`-Repository vorhanden sind,
welche im lokalen `main` noch nicht integriert wurden..


<pre><code>mein-klon $ <b>git status</b><br><br>On branch main<br>Your branch and 'origin/main' have diverged,<br>and have 1 and 2 different commits each, respectively.<br>  (use &quot;git pull&quot; to merge the remote branch into yours)<br><br>nothing to commit, working tree clean<br><br></code></pre>


Der Status zeigt, dass es im Origin-Repo
(auf dem Branch `main`) zwei Commits gibt,
die wir noch nicht integriert haben.


<pre><code>mein-klon $ <b>git log main..origin/main</b><br><br>commit 0b4e6c2d582e300fd5ebc1cbe17e7a3f6641f02c<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Second edit after cloning<br><br>commit 47ce9cff6da38bc51b508a0d9b238d4d3da1da9e<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : First edit after cloning<br><br></code></pre>


Die `..`-Notation zeigt genau jene Commits,
die in `origing/main` aber noch nicht in `main` enthalten sind.
Etwas kürzer hätte man hier auch auch `git log ..origin/main` schreiben
könne, da wir `main` ja gerade `HEAD` ist.

## Lösung zu Schritt 3 - Änderungen integrieren

Integriere die neuesten Commits vom `origin`-Repository
in den lokalen `main`.


<pre><code>mein-klon $ <b>git pull</b><br><br>Merge made by the 'ort' strategy.<br> foo | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br><br></code></pre>



<pre><code>mein-klon $ <b>git log --graph --oneline</b><br><br>*   b94c0a2 Merge branch 'main' of ../blessed<br>|\  <br>| * 0b4e6c2 : Second edit after cloning<br>| * 47ce9cf : First edit after cloning<br>* | 4554415 : My local edit<br>|/  <br>* 6014eb9 Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-push-fetch-pull.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

