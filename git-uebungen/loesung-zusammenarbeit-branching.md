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


<pre><code>repo $ <b>git branch feature-a HEAD</b><br><br><br></code></pre>



<pre><code>repo $ <b>git checkout feature-a</b><br><br>Switched to branch 'feature-a'<br><br></code></pre>



<pre><code>repo $ <b># Edit file foo at line 7 on branch feature-a by dioramabastlerin.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`foo`: Edit file foo at line 7 on branch feature-a by dioramabastlerin. &quot;</b><br><br>[feature-a 44bf976] : Edit file foo at line 7 on branch feature-a by dioramabastlerin.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>repo $ <b>git checkout master</b><br><br>Your branch is up to date with 'origin/master'.<br>Switched to branch 'master'<br><br></code></pre>



<pre><code>repo $ <b># Edit file bar at line 3 on branch master by dioramabastlerin.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`bar`: Edit file bar at line 3 on branch master by dioramabastlerin. &quot;</b><br><br>[master 0dbed99] : Edit file bar at line 3 on branch master by dioramabastlerin.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: bar: command not found<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>* 44bf976 (feature-a) : Edit file foo at line 7 on branch feature-a by dioramabastlerin.<br>| * 0dbed99 (HEAD -&gt; master) : Edit file bar at line 3 on branch master by dioramabastlerin.<br>|/  <br>* 6014eb9 (origin/master) Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 2 - Branch mergen

Merge `feature-a` auf den `master`und
zeige den Commit-Graphen.


<pre><code>repo $ <b>git merge feature-a</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>*   619bb9c (HEAD -&gt; master) Merge branch 'feature-a'<br>|\  <br>| * 44bf976 (feature-a) : Edit file foo at line 7 on branch feature-a by dioramabastlerin.<br>* | 0dbed99 : Edit file bar at line 3 on branch master by dioramabastlerin.<br>|/  <br>* 6014eb9 (origin/master) Initial edit before cloning<br>* de06cfe Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 3 - Merge analysieren

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.


<pre><code>repo $ <b>git log HEAD^2..HEAD^1</b><br><br>commit 0dbed9976f12acae724d9859658ea99d6c32ce7f<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file bar at line 3 on branch master by dioramabastlerin.<br><br></code></pre>



<pre><code>repo $ <b>git log HEAD^1..HEAD^2</b><br><br>commit 44bf976c37c8c97be42680cda8cbf1fa09161ecf<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file foo at line 7 on branch feature-a by dioramabastlerin.<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^2...HEAD^1</b><br><br>diff --git a/bar b/bar<br>index 36fe753..c409821 100644<br>--- a/bar<br>+++ b/bar<br>@@ -1,7 +1,7 @@<br> line 0 created<br> line 1 created<br> line 2 created<br>-line 3 created<br>+line 3 Edit file bar at line 3 on branch master by dioramabastlerin. / line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^1...HEAD^2</b><br><br>diff --git a/foo b/foo<br>index 36fe753..46089ea 100644<br>--- a/foo<br>+++ b/foo<br>@@ -5,7 +5,7 @@ line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br>-line 7 created<br>+line 7 Edit file foo at line 7 on branch feature-a by dioramabastlerin. / line 7 created<br> line 8 created<br> line 9 created<br> line 10 created<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-branching.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

