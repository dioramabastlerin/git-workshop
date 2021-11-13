---
layout: page
title: <code>zusammenarbeit-branching</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Branch erstellen

Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
und erstelle ein Commit.
Wechsle dann zurück auf den `master` und bearbeite dort `bar`.
Zeige den Commit-Graphen.


<pre><code>repo $ <b>git switch -c feature-a HEAD</b><br><br>Switched to a new branch 'feature-a'<br><br></code></pre>



<pre><code>repo $ <b># Edit file foo at line 7 on branch feature-a by bstachmann.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`foo`: Edit file foo at line 7 on branch feature-a by bstachmann. &quot;</b><br><br>[feature-a d9a39af] : Edit file foo at line 7 on branch feature-a by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>repo $ <b>git switch master</b><br><br>Your branch is up to date with 'origin/master'.<br>Switched to branch 'master'<br><br></code></pre>



<pre><code>repo $ <b># Edit file bar at line 3 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`bar`: Edit file bar at line 3 on branch master by bstachmann. &quot;</b><br><br>[master 8e64110] : Edit file bar at line 3 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: bar: command not found<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>* d9a39af (feature-a) : Edit file foo at line 7 on branch feature-a by bstachmann.<br>| * 8e64110 (HEAD -&gt; master) : Edit file bar at line 3 on branch master by bstachmann.<br>|/  <br>* 6014eb9 (origin/master) Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 2 - Branch mergen

Merge `feature-a` auf den `master`und
zeige den Commit-Graphen.


<pre><code>repo $ <b>git merge feature-a</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>*   d7d746c (HEAD -&gt; master) Merge branch 'feature-a'<br>|\  <br>| * d9a39af (feature-a) : Edit file foo at line 7 on branch feature-a by bstachmann.<br>* | 8e64110 : Edit file bar at line 3 on branch master by bstachmann.<br>|/  <br>* 6014eb9 (origin/master) Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 3 - ⭐ Merge analysieren

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.


<pre><code>repo $ <b>git log HEAD^2..HEAD^1</b><br><br>commit 8e64110288f6d044c424d80556c9f206eb35d119<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file bar at line 3 on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git log HEAD^1..HEAD^2</b><br><br>commit d9a39af3ddf15d64c9d29a9b760607d3f244f851<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file foo at line 7 on branch feature-a by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^2...HEAD^1</b><br><br>diff --git a/bar b/bar<br>index 36fe753..01cfa77 100644<br>--- a/bar<br>+++ b/bar<br>@@ -1,7 +1,7 @@<br> line 0 created<br> line 1 created<br> line 2 created<br>-line 3 created<br>+line 3 Edit file bar at line 3 on branch master by bstachmann. / line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^1...HEAD^2</b><br><br>diff --git a/foo b/foo<br>index 36fe753..50c9a7f 100644<br>--- a/foo<br>+++ b/foo<br>@@ -5,7 +5,7 @@ line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br>-line 7 created<br>+line 7 Edit file foo at line 7 on branch feature-a by bstachmann. / line 7 created<br> line 8 created<br> line 9 created<br> line 10 created<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-branching.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

