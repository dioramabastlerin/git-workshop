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



<pre><code>repo $ <b>git revert umbenennung</b><br><br>[main 4a063fa] Revert &quot;umbenennen&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br> rename liesmich =&gt; README.md (100%)<br><br></code></pre>



<pre><code>repo $ <b>ls</b><br><br>from-feature<br>README.md<br><br></code></pre>


## Lösung zu Schritt 2 - Merge-Commit rückgängig machen.

In der Historie wurde ein Feature-Branch per Merge integriert.file . Mache dies änderung rückgängig.
Tipp: Das Merge-Commit ist als `feature-merge` getagged. 
Tipp: Am verschinden der Datei `from-feature` kann man den Erfolg erkennen.


<pre><code>repo $ <b>ls</b><br><br>from-feature<br>README.md<br><br></code></pre>



<pre><code>repo $ <b>git revert -m 1 feature-merge</b><br><br>[main 0cde08b] Revert &quot;Merge branch 'feature'&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 12 deletions(-)<br> delete mode 100644 from-feature<br><br></code></pre>



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


<pre><code>repo $ <b>git merge feature</b><br><br>CONFLICT (modify/delete): from-feature deleted in HEAD and modified in feature.  Version feature of from-feature left in tree.<br>Automatic merge failed; fix conflicts and then commit the result.<br><br></code></pre>



<pre><code>repo $ <b>git merge --abort</b><br><br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --graph</b><br><br>* 0cde08b Revert &quot;Merge branch 'feature'&quot;<br>* 4a063fa Revert &quot;umbenennen&quot;<br>*   6001692 Merge branch 'feature'<br>|\  <br>| * 2caf490 Created file from-feature on branch feature by bjoern.<br>* | 48619ce umbenennen<br>|/  <br>* 50bb121 Created file README.md on branch main by bjoern.<br><br></code></pre>


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


<pre><code>repo $ <b>git revert merge-reverted</b><br><br>[main 9357146] Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 12 insertions(+)<br> create mode 100644 from-feature<br><br></code></pre>



<pre><code>repo $ <b>git merge feature</b><br><br>Merge made by the 'ort' strategy.<br> from-feature | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --graph</b><br><br>*   834d9f3 Merge branch 'feature'<br>|\  <br>| * dc5cc98 : Weiterentwicklung<br>* | 9357146 Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br>* | 0cde08b Revert &quot;Merge branch 'feature'&quot;<br>* | 4a063fa Revert &quot;umbenennen&quot;<br>* | 6001692 Merge branch 'feature'<br>|\| <br>| * 2caf490 Created file from-feature on branch feature by bjoern.<br>* | 48619ce umbenennen<br>|/  <br>* 50bb121 Created file README.md on branch main by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline from-feature</b><br><br>dc5cc98 : Weiterentwicklung<br>2caf490 Created file from-feature on branch feature by bjoern.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-reverting.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

