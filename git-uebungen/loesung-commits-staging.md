---
layout: page
title: <code>commits-staging</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Staging

Ersetze in der Datei `demo`,
`Fit` durch `Git`.
Füge sie dann zu Stage-Bereich hinzu.
Ersetze dann `doof` durch `toll`.
Lasse dir den Status und die Diffs
für Workspace und Stage zeigen.


<pre><code>repo $ <b># Edit file demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add demo</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git status</b><br><br>On branch master<br>Changes to be committed:<br>  (use &quot;git restore --staged &lt;file&gt;...&quot; to unstage)<br>	modified:   demo<br><br>Changes not staged for commit:<br>  (use &quot;git add &lt;file&gt;...&quot; to update what will be committed)<br>  (use &quot;git restore &lt;file&gt;...&quot; to discard changes in working directory)<br>	modified:   demo<br><br><br></code></pre>



<pre><code>repo $ <b>git diff</b><br><br>diff --git a/demo b/demo<br>index 94cc8b3..70971ae 100644<br>--- a/demo<br>+++ b/demo<br>@@ -1,3 +1,3 @@<br> Git<br> ist<br>-doof.<br>+toll.<br><br></code></pre>


Man sieht, dass der Stage-Bereich ein anderes Diff hat, als der Workspace.


<pre><code>repo $ <b>git diff --staged</b><br><br>diff --git a/demo b/demo<br>index af77d0b..94cc8b3 100644<br>--- a/demo<br>+++ b/demo<br>@@ -1,3 +1,3 @@<br>-Fit<br>+Git<br> ist<br> doof.<br><br></code></pre>


## Lösung zu Schritt 2 - Restore

Zurücknehmen eines Stagings. 


<pre><code>repo $ <b>git restore --staged demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git status</b><br><br>On branch master<br>Changes not staged for commit:<br>  (use &quot;git add &lt;file&gt;...&quot; to update what will be committed)<br>  (use &quot;git restore &lt;file&gt;...&quot; to discard changes in working directory)<br>	modified:   demo<br><br>no changes added to commit (use &quot;git add&quot; and/or &quot;git commit -a&quot;)<br><br></code></pre>



<pre><code>repo $ <b>git diff</b><br><br>diff --git a/demo b/demo<br>index af77d0b..70971ae 100644<br>--- a/demo<br>+++ b/demo<br>@@ -1,3 +1,3 @@<br>-Fit<br>+Git<br> ist<br>-doof.<br>+toll.<br><br></code></pre>


Nach dem zurücknehmen ist der Stage-Bereich wieder leer.


<pre><code>repo $ <b>git diff --staged</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-commits-staging.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

