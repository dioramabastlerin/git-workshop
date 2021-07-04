---
layout: page
title: <code>zusammenarbeit-push-fetch-pull</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Änderungen holen

Hole die beiden neuen Commits vom `origin`-Repository,
ohne den lokalen `master` zu verändern.


<pre><code>mein-klon $ <b>git fetch</b><br><br>From ../blessed<br>   b5f62a3..df13ded  master     -&gt; origin/master<br><br></code></pre>


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


<pre><code>mein-klon $ <b>git log master..origin/master</b><br><br>commit df13ded5b1cebb2e4d60d351a115317a6b5fb26b<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:35:16 2021 +0200<br><br>    : Second edit after cloning<br><br>commit ae1311492a2fd746abe7a1815a80fd1c4f0f7d87<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:35:16 2021 +0200<br><br>    : First edit after cloning<br><br></code></pre>


Die `..`-Notation zeigt genau jene Commits,
die in `origing/master` aber noch nicht in `master` enthalten sind.
Etwas kürzer hätte man hier auch auch `git log ..origin/master` schreiben
könne, da wir `master` ja gerade `HEAD` ist.

## Lösung zu Schritt 3 - Änderungen integrieren

Integriere die neuesten Commits vom `origin`-Repository
in den lokalen `master`.


<pre><code>mein-klon $ <b>git pull</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br>warning: Pulling without specifying how to reconcile divergent branches is<br>discouraged. You can squelch this message by running one of the following<br>commands sometime before your next pull:<br><br>  git config pull.rebase false  # merge (the default strategy)<br>  git config pull.rebase true   # rebase<br>  git config pull.ff only       # fast-forward only<br><br>You can replace &quot;git config&quot; with &quot;git config --global&quot; to set a default<br>preference for all repositories. You can also pass --rebase, --no-rebase,<br>or --ff-only on the command line to override the configured default per<br>invocation.<br><br><br></code></pre>



<pre><code>mein-klon $ <b>git log --graph --oneline</b><br><br>*   68d9a7a Merge branch 'master' of ../blessed<br>|\  <br>| * df13ded : Second edit after cloning<br>| * ae13114 : First edit after cloning<br>* | e201273 : My local edit<br>|/  <br>* b5f62a3 Initial edit before cloning<br>* d693a35 Initial edit before cloning<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-push-fetch-pull.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

