---
layout: page
title: <code>zusammenarbeit-integration-von-aenderungen</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Fast-Forward beim Pull

Im einfachste Fall haben wir selber gar nichts gemacht,
und wollen nur die Änderungen von Anja übernehmen.

Führe ein Pull durch.

Lasse Dir Status und den Commit-Graphen zeigen.


<pre><code>fast-forward $ <b>git pull</b><br><br>Updating d19e196..0478186<br>Fast-forward<br> average.kts | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../origin-for-merge-samples<br>   d19e196..0478186  master     -&gt; origin/master<br><br></code></pre>


Git signalisiert, dass ein Fast-Forward durchgeführt wurde.

Der Graph zeigt, dass keine Verzweigung entstanden ist und kein Merge notwendig war.


<pre><code>fast-forward $ <b>git log --graph --oneline --decorate</b><br><br>* 0478186 (HEAD -&gt; master, origin/master, origin/HEAD) Verwende double Werte statt int<br>* d19e196 Created file average.kts on branch master by anja .<br>* 85d6809 Created file README.md on branch master by anja .<br><br></code></pre>


## Lösung zu Schritt 2 - Merge erzwingen beim Pull

Auch dieses haben wir nichtnichts gemacht,
und wollen nur die Änderungen von Anja übernehmen.

Führe ein Pull mit `--no-ff` durch.

Lasse Dir Status und den Commit-Graphen zeigen.


<pre><code>no-ff $ <b>git pull --no-ff</b><br><br>Merge made by the 'ort' strategy.<br> average.kts | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../origin-for-merge-samples<br>   d19e196..0478186  master     -&gt; origin/master<br><br></code></pre>


Git signalisiert, dass kein Fast-Forward durchgeführt wurde.

Der Graph zeigt, dass ein Merge enstanden ist.


<pre><code>no-ff $ <b>git log --graph --oneline --decorate</b><br><br>*   36f8c8e (HEAD -&gt; master) Merge branch 'master' of ../origin-for-merge-samples<br>|\  <br>| * 0478186 (origin/master, origin/HEAD) Verwende double Werte statt int<br>|/  <br>* d19e196 Created file average.kts on branch master by anja .<br>* 85d6809 Created file README.md on branch master by anja .<br><br></code></pre>


## Lösung zu Schritt 3 - Integration bei Änderungen in verschiedenen Dateien

1. Bearbeite die Datei `README.md`.
   - Erstelle ein Commit dazu.
   - Prüfe mit `git show`, ob das Commit OK ist.
2. Versuche ein Push
   - Dies wird scheitern, denn Deine Kollegin Bea 
     hat die in der Zwischenzeit die Datei `average.kts`
     bearbeitet und gepushed.
3. Integriere mit Pull
4. Untersuche das Ergebnis, z. B.
   - den Commit-Graphen an
   - die Änderungen, die Anja gemacht hat 
   - die Commits, die Anja gemacht hat


<pre><code>changes-in-different-files $ <b># Edit file README.md</b><br><br><br></code></pre>



<pre><code>changes-in-different-files $ <b>git commit -am &quot;Commited file README.md on branch master by bjoern &quot;</b><br><br>[master 2dc03e3] Commited file README.md on branch master by bjoern<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>changes-in-different-files $ <b>git show</b><br><br>commit 2dc03e342d88517b0aa8a5ac383249fa691fb174<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Commited file README.md on branch master by bjoern<br><br>diff --git a/README.md b/README.md<br>index 8b6805c..28cf676 100644<br>--- a/README.md<br>+++ b/README.md<br>@@ -1 +1 @@<br>-Hallo Wolt!<br>+Hallo Welt!<br><br></code></pre>



<pre><code>changes-in-different-files $ <b>git push</b><br><br>To ../origin-for-merge-samples.git<br> ! [rejected]        master -&gt; master (fetch first)<br>error: failed to push some refs to '../origin-for-merge-samples.git'<br>hint: Updates were rejected because the remote contains work that you do<br>hint: not have locally. This is usually caused by another repository pushing<br>hint: to the same ref. You may want to first integrate the remote changes<br>hint: (e.g., 'git pull ...') before pushing again.<br>hint: See the 'Note about fast-forwards' in 'git push --help' for details.<br><br></code></pre>


Diese Meldung zeigt, dass im `origin` Änderungen vorliegen,
die wir noch nicht integriert haben.


<pre><code>changes-in-different-files $ <b>git pull</b><br><br>Merge made by the 'ort' strategy.<br> average.kts | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../origin-for-merge-samples<br>   d19e196..0478186  master     -&gt; origin/master<br><br></code></pre>


Git hat die Änderungen geholt und ein Merge-Commit erzeugt.


<pre><code>changes-in-different-files $ <b>git log --oneline --graph</b><br><br>*   23c4b7d Merge branch 'master' of ../origin-for-merge-samples<br>|\  <br>| * 0478186 Verwende double Werte statt int<br>* | 2dc03e3 Commited file README.md on branch master by bjoern<br>|/  <br>* d19e196 Created file average.kts on branch master by anja .<br>* 85d6809 Created file README.md on branch master by anja .<br><br></code></pre>



<pre><code>changes-in-different-files $ <b>git diff HEAD^1...HEAD^2</b><br><br>diff --git a/average.kts b/average.kts<br>index 4cd02bf..5ff5b2b 100644<br>--- a/average.kts<br>+++ b/average.kts<br>@@ -1,6 +1,6 @@<br> if(args.isEmpty())<br>     throw RuntimeException(&quot;No arguments given!&quot;)<br> <br>-val s = args.map{ it.toInt() }.sum()<br>+val s = args.map{ it.toDouble() }.sum()<br> <br> println(&quot;The average is ${s/args.size}&quot;)<br><br></code></pre>



<pre><code>changes-in-different-files $ <b>git log HEAD^2..HEAD^1</b><br><br>commit 2dc03e342d88517b0aa8a5ac383249fa691fb174<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Commited file README.md on branch master by bjoern<br><br></code></pre>


Und jetzt können wir erneut ein Push versuchen.


<pre><code>changes-in-different-files $ <b>git push</b><br><br>To ../origin-for-merge-samples.git<br>   0478186..23c4b7d  master -&gt; master<br><br></code></pre>


## Lösung zu Schritt 4 - Integration bei Änderungen in derselben Datei

In diesem Fall bearbeiten wir dieselbe Datei,
die auch Anja bearbeitet hat.
Es wird zu einem Konflikt kommen, 
den wir aulösen müssen.

1. Wir haben schon eine Änderung, die zu einem Konflikt führt,
   vorbereitet und committed. Untersuche diese mit `git show`
2. Führe ein Pull durch.
3. Lasse Dir den Status zeigen und löse den Konflikt.


<pre><code>changes-in-same-files $ <b>git show</b><br><br>commit 15310dc6b4cc7822c1ac04fa17727470f3134ac5<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Refactoring: s in summe umbenennen<br><br>diff --git a/average.kts b/average.kts<br>index 4cd02bf..7eb87f2 100644<br>--- a/average.kts<br>+++ b/average.kts<br>@@ -1,6 +1,6 @@<br> if(args.isEmpty())<br>     throw RuntimeException(&quot;No arguments given!&quot;)<br> <br>-val s = args.map{ it.toInt() }.sum()<br>+val summe = args.map{ it.toInt() }.sum()<br> <br>-println(&quot;The average is ${s/args.size}&quot;)<br>+println(&quot;The average is ${summe/args.size}&quot;)<br><br></code></pre>



<pre><code>changes-in-same-files $ <b>git pull</b><br><br>Auto-merging average.kts<br>CONFLICT (content): Merge conflict in average.kts<br>Automatic merge failed; fix conflicts and then commit the result.<br>From ../origin-for-merge-samples<br>   d19e196..23c4b7d  master     -&gt; origin/master<br><br></code></pre>


Wie erwartet, ist es zu einem Konflikt gekommen.


<pre><code>changes-in-same-files $ <b>git status</b><br><br>On branch master<br>Your branch and 'origin/master' have diverged,<br>and have 1 and 3 different commits each, respectively.<br>  (use &quot;git pull&quot; to merge the remote branch into yours)<br><br>You have unmerged paths.<br>  (fix conflicts and run &quot;git commit&quot;)<br>  (use &quot;git merge --abort&quot; to abort the merge)<br><br>Changes to be committed:<br>	modified:   README.md<br><br>Unmerged paths:<br>  (use &quot;git add &lt;file&gt;...&quot; to mark resolution)<br>	both modified:   average.kts<br><br><br></code></pre>


In der Datei stehen Konfliktmarkierungen.


<pre><code>changes-in-same-files $ <b>cat average.kts</b><br><br>if(args.isEmpty())<br>    throw RuntimeException(&quot;No arguments given!&quot;)<br><br>&lt;&lt;&lt;&lt;&lt;&lt;&lt; HEAD<br>val summe = args.map{ it.toInt() }.sum()<br>||||||| d19e196<br>val s = args.map{ it.toInt() }.sum()<br>=======<br>val s = args.map{ it.toDouble() }.sum()<br>&gt;&gt;&gt;&gt;&gt;&gt;&gt; 23c4b7d2b3f64005c4aae33fe59b0965dcea4e2b<br><br>println(&quot;The average is ${summe/args.size}&quot;)<br><br></code></pre>



<pre><code>changes-in-same-files $ <b># Edit average.kts replacing pattern with val summe = args.map{ it.toDouble() }.sum()</b><br><br><br></code></pre>


Nicht vergessen: Nach dem Bereinigen `git add` aufrufen.


<pre><code>changes-in-same-files $ <b>git add average.kts</b><br><br><br></code></pre>



<pre><code>changes-in-same-files $ <b>git commit -m 'Änderungen von Anja integriert'</b><br><br>[master 2b2ed2f] Änderungen von Anja integriert<br><br></code></pre>


Und hier nochmal der entstandene Graph:


<pre><code>changes-in-same-files $ <b>git log --graph --oneline</b><br><br>*   2b2ed2f Änderungen von Anja integriert<br>|\  <br>| *   23c4b7d Merge branch 'master' of ../origin-for-merge-samples<br>| |\  <br>| | * 0478186 Verwende double Werte statt int<br>| * | 2dc03e3 Commited file README.md on branch master by bjoern<br>| |/  <br>* / 15310dc Refactoring: s in summe umbenennen<br>|/  <br>* d19e196 Created file average.kts on branch master by anja .<br>* 85d6809 Created file README.md on branch master by anja .<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-integration-von-aenderungen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

