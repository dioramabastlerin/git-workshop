---
layout: page
title: <code>zusammenarbeit-reverting</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Einzelnes Commit rückgängig machen.

In der Historie wurde die Datei `README.md` in `liesmich`
umbenannt. Mache diese Änderung rückgängig.
Tipp: Das Commit ist als `umbenennung` getagged.


<pre><code>repo $ <b>ls</b><br><br>from-feature<br>liesmich<br><br></code></pre>



<pre><code>repo $ <b>git revert umbenennung</b><br><br>[master d557601] Revert &quot;umbenennen&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br> rename liesmich =&gt; README.md (100%)<br><br></code></pre>



<pre><code>repo $ <b>ls</b><br><br>from-feature<br>README.md<br><br></code></pre>


## Lösung zu Schritt 2 - Merge-Commit rückgängig machen.

In der Historie wurde ein Feature-Branch per Merge integriert.file . Mache dies änderung rückgängig.
Tipp: Das Merge-Commit ist als `feature-merge` getagged. 
Tipp: Am verschinden der Datei `from-feature` kann man den Erfolg erkennen.


<pre><code>repo $ <b>ls</b><br><br>from-feature<br>README.md<br><br></code></pre>



<pre><code>repo $ <b>git revert -m 1 feature-merge</b><br><br>Removing from-feature<br>[master 49f5ddb] Revert &quot;Merge branch 'feature'&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 12 deletions(-)<br> delete mode 100644 from-feature<br><br></code></pre>



<pre><code>repo $ <b>ls</b><br><br>README.md<br><br></code></pre>



<pre><code>repo $ <b>git tag merge-reverted</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Feature-Branch kaputt!?

Ein Revert wird oft genutzt, um ein Feature kurzfristig zurückzunehmen,
z. B. wegen eine Produktionsproblems.
Später möchte man den Feature-Branch korrigieren und dann erneut integrieren. 
Das geht nicht so ohne weiteres.

Im Beispiel hat der Branch `feature` eine Weiterentwicklung erfahren.
Versuche ihn erneut zu Mergen.
Untersuche die Fehlermeldung und den Commit-Graphen.
Tipp: Das gescheiterte Merge kann mit `git merge --abort` abgebrochen werden.


<pre><code>repo $ <b>git merge feature</b><br><br>CONFLICT (modify/delete): from-feature deleted in HEAD and modified in feature. Version feature of from-feature left in tree.<br>Automatic merge failed; fix conflicts and then commit the result.<br><br></code></pre>



<pre><code>repo $ <b>git merge --abort</b><br><br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --graph</b><br><br>* 49f5ddb Revert &quot;Merge branch 'feature'&quot;<br>* d557601 Revert &quot;umbenennen&quot;<br>*   d2188bd Merge branch 'feature'<br>|\  <br>| * d3b0de7 Created file from-feature on branch feature by bstachmann.<br>* | 5f55981 umbenennen<br>|/  <br>* d10a944 Created file README.md on branch master by bstachmann.<br><br></code></pre>


Das Merge scheitert, weil Git Commits,
die schon in der Historie enthalten sind,
nicht erneut merged.
Dies betrifft im Beispiel jenes Commit, 
das die Datei `from-feature` erzeugt.
Es ist in der Historie enthalten und
in einem späteren Commit (Dem Revert) wurde die Datei gelöscht.
Der Mergekonflikt meldet also, dass die Datei auf der einen
Seite bearbeitet und auf der Anderen gelöscht wurde.

## Lösung zu Schritt 4 - Merge-Revert wieder rückgängig machen.

Der Trick ist, das Revert-Commit selbst zu reverten,
dann sind jene Änderungen wieder da,
auf denen die Weiterentwicklung des Features basiert.

Tipp: Das Commit ist als `feature-merge` getagged.


<pre><code>repo $ <b>git revert merge-reverted</b><br><br>[master fd2b608] Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 12 insertions(+)<br> create mode 100644 from-feature<br><br></code></pre>



<pre><code>repo $ <b>git merge feature</b><br><br>Merge made by the 'recursive' strategy.<br> from-feature | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --graph</b><br><br>*   a5195ec Merge branch 'feature'<br>|\  <br>| * 0d09c64 : Weiterentwicklung<br>* | fd2b608 Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br>* | 49f5ddb Revert &quot;Merge branch 'feature'&quot;<br>* | d557601 Revert &quot;umbenennen&quot;<br>* | d2188bd Merge branch 'feature'<br>|\| <br>| * d3b0de7 Created file from-feature on branch feature by bstachmann.<br>* | 5f55981 umbenennen<br>|/  <br>* d10a944 Created file README.md on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --follow -- from-feature</b><br><br>fd2b608 Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br>d557601 Revert &quot;umbenennen&quot;<br>5f55981 umbenennen<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-reverting.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

