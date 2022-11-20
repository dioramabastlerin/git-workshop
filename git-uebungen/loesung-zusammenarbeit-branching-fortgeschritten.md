---
layout: page
title: <code>zusammenarbeit-branching-fortgeschritten</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Branch erstellen

Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
und erstelle ein Commit.
Wechsle dann zurück auf den `master` und bearbeite dort `bar`.
Zeige den Commit-Graphen.


<pre><code>repo $ <b>git switch -c feature-a HEAD</b><br><br>Switched to a new branch 'feature-a'<br><br></code></pre>



<pre><code>repo $ <b># Edit file foo at line 7 on branch feature-a by bjoern.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`foo`: Edit file foo at line 7 on branch feature-a by bjoern. &quot;</b><br><br>[feature-a 300f5e9] : Edit file foo at line 7 on branch feature-a by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>repo $ <b>git switch master</b><br><br>Your branch is up to date with 'origin/master'.<br>Switched to branch 'master'<br><br></code></pre>



<pre><code>repo $ <b># Edit file bar at line 3 on branch master by bjoern.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`bar`: Edit file bar at line 3 on branch master by bjoern. &quot;</b><br><br>[master f62ae23] : Edit file bar at line 3 on branch master by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: bar: command not found<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>* 300f5e9 (feature-a) : Edit file foo at line 7 on branch feature-a by bjoern.<br>| * f62ae23 (HEAD -&gt; master) : Edit file bar at line 3 on branch master by bjoern.<br>|/  <br>* 6014eb9 (origin/master) Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 2 - Branch mergen

Merge `feature-a` auf den `master`und
zeige den Commit-Graphen.


<pre><code>repo $ <b>git merge feature-a</b><br><br>Merge made by the 'ort' strategy.<br> foo | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>*   46cbb6c (HEAD -&gt; master) Merge branch 'feature-a'<br>|\  <br>| * 300f5e9 (feature-a) : Edit file foo at line 7 on branch feature-a by bjoern.<br>* | f62ae23 : Edit file bar at line 3 on branch master by bjoern.<br>|/  <br>* 6014eb9 (origin/master) Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 3 - ⭐ Merge analysieren

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.


<pre><code>repo $ <b>git log HEAD^2..HEAD^1</b><br><br>commit f62ae238e18d9154fe369dea5b9ed76c0bbac9f5<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file bar at line 3 on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git log HEAD^1..HEAD^2</b><br><br>commit 300f5e9c1321f67c1f42fb9df9744373b4c25202<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file foo at line 7 on branch feature-a by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^2...HEAD^1</b><br><br>diff --git a/bar b/bar<br>index 36fe753..6ef87e6 100644<br>--- a/bar<br>+++ b/bar<br>@@ -1,7 +1,7 @@<br> line 0 created<br> line 1 created<br> line 2 created<br>-line 3 created<br>+line 3 Edit file bar at line 3 on branch master by bjoern. / line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^1...HEAD^2</b><br><br>diff --git a/foo b/foo<br>index 36fe753..f719df0 100644<br>--- a/foo<br>+++ b/foo<br>@@ -5,7 +5,7 @@ line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br>-line 7 created<br>+line 7 Edit file foo at line 7 on branch feature-a by bjoern. / line 7 created<br> line 8 created<br> line 9 created<br> line 10 created<br><br></code></pre>


## Lösung zu Schritt 4 - ⭐ Merge analysieren

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.


<pre><code>repo $ <b>git log HEAD^2..HEAD^1</b><br><br>commit f62ae238e18d9154fe369dea5b9ed76c0bbac9f5<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file bar at line 3 on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git log HEAD^1..HEAD^2</b><br><br>commit 300f5e9c1321f67c1f42fb9df9744373b4c25202<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file foo at line 7 on branch feature-a by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^2...HEAD^1</b><br><br>diff --git a/bar b/bar<br>index 36fe753..6ef87e6 100644<br>--- a/bar<br>+++ b/bar<br>@@ -1,7 +1,7 @@<br> line 0 created<br> line 1 created<br> line 2 created<br>-line 3 created<br>+line 3 Edit file bar at line 3 on branch master by bjoern. / line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^1...HEAD^2</b><br><br>diff --git a/foo b/foo<br>index 36fe753..f719df0 100644<br>--- a/foo<br>+++ b/foo<br>@@ -5,7 +5,7 @@ line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br>-line 7 created<br>+line 7 Edit file foo at line 7 on branch feature-a by bjoern. / line 7 created<br> line 8 created<br> line 9 created<br> line 10 created<br><br></code></pre>


## Lösung zu Schritt 5 - Remote Branches untersuchen




<pre><code>repo $ <b>git branch -r -vv</b><br><br>  origin/master 6014eb9 Initial edit before cloning<br><br></code></pre>



<pre><code>repo $ <b>git fetch</b><br><br>From ../blessed<br> * [new branch]      feature-x  -&gt; origin/feature-x<br> * [new branch]      feature-y  -&gt; origin/feature-y<br><br></code></pre>



<pre><code>repo $ <b>git branch -r -vv</b><br><br>  origin/feature-x b04a371 : Edit file datei-x at line 3 on branch feature-x by bjoern.<br>  origin/feature-y 3181804 Created file datei-y on branch feature-y by bjoern.<br>  origin/master    6014eb9 Initial edit before cloning<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline ..origin/feature-x</b><br><br>b04a371 : Edit file datei-x at line 3 on branch feature-x by bjoern.<br>9755d66 Created file datei-x on branch feature-x by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline ..origin/feature-y</b><br><br>3181804 Created file datei-y on branch feature-y by bjoern.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-branching-fortgeschritten.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

