---
layout: page
title: <code>commits-staging</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Staging

Ersetze in der Datei `demo`,
`Fit` durch `Git`.
Füge sie dann zum Stage-Bereich hinzu.
Ersetze dann `doof` durch `toll`.
Lasse dir den Status und die Diffs
für Workspace und Stage zeigen.

Ersetze `Fit` durch `Git`.


<pre><code>repo $ <b># Edit file demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add demo</b><br><br><br></code></pre>


Ersetze `doof` durch `toll`.


<pre><code>repo $ <b># Edit file demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git status</b><br><br>On branch master<br>Changes to be committed:<br>  (use &quot;git restore --staged &lt;file&gt;...&quot; to unstage)<br>	modified:   demo<br><br>Changes not staged for commit:<br>  (use &quot;git add &lt;file&gt;...&quot; to update what will be committed)<br>  (use &quot;git restore &lt;file&gt;...&quot; to discard changes in working directory)<br>	modified:   demo<br><br><br></code></pre>


Die Datei `demo` wird sowohl in `Changes to be committed:` als auch in `Changes not staged for commit` geführt.


<pre><code>repo $ <b>git diff</b><br><br>diff --git a/demo b/demo<br>index 94cc8b3..70971ae 100644<br>--- a/demo<br>+++ b/demo<br>@@ -1,3 +1,3 @@<br> Git<br> ist<br>-doof.<br>+toll.<br><br></code></pre>


Man sieht, dass der Stage-Bereich ein anderes Diff hat, als der Workspace.


<pre><code>repo $ <b>git diff --staged</b><br><br>diff --git a/demo b/demo<br>index af77d0b..94cc8b3 100644<br>--- a/demo<br>+++ b/demo<br>@@ -1,3 +1,3 @@<br>-Fit<br>+Git<br> ist<br> doof.<br><br></code></pre>


## Lösung zu Schritt 2 - Restore - Staging zurücknehmen

Die letzte Änderung soll doch noch nicht in das nächste Commit übernommen werden,
nehme sie zurück. 


<pre><code>repo $ <b>git restore --staged demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git status</b><br><br>On branch master<br>Changes not staged for commit:<br>  (use &quot;git add &lt;file&gt;...&quot; to update what will be committed)<br>  (use &quot;git restore &lt;file&gt;...&quot; to discard changes in working directory)<br>	modified:   demo<br><br>no changes added to commit (use &quot;git add&quot; and/or &quot;git commit -a&quot;)<br><br></code></pre>



<pre><code>repo $ <b>git diff</b><br><br>diff --git a/demo b/demo<br>index af77d0b..70971ae 100644<br>--- a/demo<br>+++ b/demo<br>@@ -1,3 +1,3 @@<br>-Fit<br>+Git<br> ist<br>-doof.<br>+toll.<br><br></code></pre>


Nach dem zurücknehmen ist der Stage-Bereich wieder leer.


<pre><code>repo $ <b>git diff --staged</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Restore - Änderung ganz zurücknehmen

Die Änderungen an `demo` sollen ganz zurückgenommen werden.
Lasse Dir nachher Status und Diffs anzeigen.


<pre><code>repo $ <b>git status</b><br><br>On branch master<br>Changes not staged for commit:<br>  (use &quot;git add &lt;file&gt;...&quot; to update what will be committed)<br>  (use &quot;git restore &lt;file&gt;...&quot; to discard changes in working directory)<br>	modified:   demo<br><br>no changes added to commit (use &quot;git add&quot; and/or &quot;git commit -a&quot;)<br><br></code></pre>



<pre><code>repo $ <b>git restore demo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git status</b><br><br>On branch master<br>nothing to commit, working tree clean<br><br></code></pre>



<pre><code>repo $ <b>git diff</b><br><br><br></code></pre>



<pre><code>repo $ <b>git diff --staged</b><br><br><br></code></pre>


Jetzt sind die Änderungen ganz weg.

## Lösung zu Schritt 4 - ⭐ Restore - Älteren Inhalt einer Datei zurückholen

Die Datei `beispiel` wurde dreimal bearbeitet.
Hole den mittleren Stand zurück und erstelle ein Commit.


<pre><code>repo $ <b>git log --oneline beispiel</b><br><br>c702a2f später<br>c60a910 dazwischen<br>6bfc762 dazwischen<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~9 beispiel</b><br><br><br></code></pre>



<pre><code>repo $ <b>git diff</b><br><br>diff --git a/beispiel b/beispiel<br>index c571ad6..ab1b57f 100644<br>--- a/beispiel<br>+++ b/beispiel<br>@@ -1 +1 @@<br>-Und so endete es.<br>\ No newline at end of file<br>+Dann kam das mit dem Mittelteil.<br>\ No newline at end of file<br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;Mittlerer Stand wiederhergestellt.&quot;</b><br><br>[master 5c89605] Mittlerer Stand wiederhergestellt.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>


## Lösung zu Schritt 5 - ⭐ Restore - Zurückholen älterer Verzeichnisversionen

Im Folder `ufer` wurde ein Spiel gespielt.
Stelle die Spielstände nach, 
indem Du `restore` auf das `ufer`-Verzeichnis anwendest.

Tipp: `ll ufer/*` zeigt die Verzeichnisse des Spiels.

Tipp: Beim `restore` werden unversionierte Dateien nicht abgeräumt.
Man kann sie mit dem `stash`-Befehl abräumen.


<pre><code>repo $ <b>git log --oneline -- ufer/</b><br><br>c211be0 Zug 7<br>12c4f86 Zug 6<br>735d335 Zug 5<br>1406f15 Zug 4<br>1b74b7e Zug 3<br>4d102a7 Zug 2<br>1b2e426 Zug 1<br>3b3f142 Starte spiel<br><br></code></pre>


Zug 1


<pre><code>repo $ <b>git stash -u</b><br><br>No local changes to save<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~7 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>ufer/ost:<br>total 8.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br>ufer/west:<br>total 8.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br><br></code></pre>


Zug 2


<pre><code>repo $ <b>git stash -u</b><br><br>Saved working directory and index state WIP on master: 5c89605 Mittlerer Stand wiederhergestellt.<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~6 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>ufer/ost:<br>total 4.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br><br>ufer/west:<br>total 12K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br></code></pre>


Zug 3


<pre><code>repo $ <b>git stash -u</b><br><br>Saved working directory and index state WIP on master: 5c89605 Mittlerer Stand wiederhergestellt.<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~5 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>ufer/ost:<br>total 12K<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br>ufer/west:<br>total 4.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br><br></code></pre>


Zug 4


<pre><code>repo $ <b>git stash -u</b><br><br>Saved working directory and index state WIP on master: 5c89605 Mittlerer Stand wiederhergestellt.<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~4 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>ufer/ost:<br>total 4.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br><br>ufer/west:<br>total 12K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br></code></pre>


Zug 5


<pre><code>repo $ <b>git stash -u</b><br><br>Saved working directory and index state WIP on master: 5c89605 Mittlerer Stand wiederhergestellt.<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~3 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>ufer/ost:<br>total 12K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br>ufer/west:<br>total 4.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br><br></code></pre>


Zug 6


<pre><code>repo $ <b>git stash -u</b><br><br>Saved working directory and index state WIP on master: 5c89605 Mittlerer Stand wiederhergestellt.<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~2 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>ufer/ost:<br>total 8.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br><br>ufer/west:<br>total 8.0K<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br></code></pre>


Zug 7


<pre><code>repo $ <b>git stash -u</b><br><br>Saved working directory and index state WIP on master: 5c89605 Mittlerer Stand wiederhergestellt.<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~1 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>total 16K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br></code></pre>


Zug 8


<pre><code>repo $ <b>git stash -u</b><br><br>No local changes to save<br><br></code></pre>



<pre><code>repo $ <b>git restore -s HEAD~0 ufer</b><br><br><br></code></pre>



<pre><code>repo $ <b>ll ufer/*</b><br><br>total 16K<br>-rw-r--r-- 1 gitpod gitpod 181  🥬<br>-rw-r--r-- 1 gitpod gitpod 181  🐐<br>-rw-r--r-- 1 gitpod gitpod 181  🐺<br>-rw-r--r-- 1 gitpod gitpod 181  👨‍🌾<br><br></code></pre>


[Zur Aufgabe](aufgabe-commits-staging.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

