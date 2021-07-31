---
layout: page
title: <code>zusammenarbeit-push-fetch-pull</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Änderungen holen

Hole die beiden neuen Commits vom `origin`-Repository,
ohne den lokalen `master` zu verändern.


<pre><code>mein-klon $ <b>git fetch</b><br><br>From ../blessed<br>   14acc6c..259881a  master     -&gt; origin/master<br><br></code></pre>


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


<pre><code>mein-klon $ <b>git log master..origin/master</b><br><br>commit 259881ace4decd437d3947f80980159f8a49a223<br>Author: bstachmann &lt;bstachmann@yahoo.de&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Second edit after cloning<br><br>commit bb346646c525cb02944adab8ab618947fbeb74bc<br>Author: bstachmann &lt;bstachmann@yahoo.de&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : First edit after cloning<br><br></code></pre>


Die `..`-Notation zeigt genau jene Commits,
die in `origing/master` aber noch nicht in `master` enthalten sind.
Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
könne, da wir `master` ja gerade `HEAD` ist.

## Lösung zu Schritt 3 - Änderungen integrieren

Integriere die neuesten Commits vom `origin`-Repository
in den lokalen `master`.


<pre><code>mein-klon $ <b>git pull</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br>hint: Pulling without specifying how to reconcile divergent branches is<br>hint: discouraged. You can squelch this message by running one of the following<br>hint: commands sometime before your next pull:<br>hint: <br>hint:   git config pull.rebase false  # merge (the default strategy)<br>hint:   git config pull.rebase true   # rebase<br>hint:   git config pull.ff only       # fast-forward only<br>hint: <br>hint: You can replace &quot;git config&quot; with &quot;git config --global&quot; to set a default<br>hint: preference for all repositories. You can also pass --rebase, --no-rebase,<br>hint: or --ff-only on the command line to override the configured default per<br>hint: invocation.<br><br></code></pre>



<pre><code>mein-klon $ <b>git log --graph --oneline</b><br><br>*   760efc1 Merge branch 'master' of ../blessed<br>|\  <br>| * 259881a : Second edit after cloning<br>| * bb34664 : First edit after cloning<br>* | e76b4b2 : My local edit<br>|/  <br>* 14acc6c Initial edit before cloning<br>* da83726 Initial edit before cloning<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-push-fetch-pull.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

