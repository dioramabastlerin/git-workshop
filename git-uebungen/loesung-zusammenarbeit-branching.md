---
layout: page
title: <code>zusammenarbeit-branching</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Branch erstellen

Erstelle einen Branch `feature-a` und bearbeite die Datei `foo`.
Wechsle zurück auf den `master` und bearbeite dort `bar`.
Zeige den Commit-Graphen.


<pre><code>repo $ <b>git branch feature-a HEAD</b><br><br><br></code></pre>



<pre><code>repo $ <b>git checkout feature-a</b><br><br>Switched to branch 'feature-a'<br><br></code></pre>



<pre><code>repo $ <b># Edit file foo at line 7 on branch feature-a by bstachmann.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`foo`: Edit file foo at line 7 on branch feature-a by bstachmann. &quot;</b><br><br>[feature-a 85aa0ce] : Edit file foo at line 7 on branch feature-a by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>repo $ <b>git checkout master</b><br><br>Your branch is up to date with 'origin/master'.<br>Switched to branch 'master'<br><br></code></pre>



<pre><code>repo $ <b># Edit file bar at line 3 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`bar`: Edit file bar at line 3 on branch master by bstachmann. &quot;</b><br><br>[master 228f19d] : Edit file bar at line 3 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: bar: command not found<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>* 85aa0ce (feature-a) : Edit file foo at line 7 on branch feature-a by bstachmann.<br>| * 228f19d (HEAD -&gt; master) : Edit file bar at line 3 on branch master by bstachmann.<br>|/  <br>* c9ab46a (origin/master) Initial edit before cloning<br>* 70e994a Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 2 - Branch mergen

Merge `feature-a` auf den `master`und
zeige den Commit-Graphen.


<pre><code>repo $ <b>git merge feature-a</b><br><br>Merge made by the 'recursive' strategy.<br> foo | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --all --oneline --graph --decorate</b><br><br>*   a82a0ab (HEAD -&gt; master) Merge branch 'feature-a'<br>|\  <br>| * 85aa0ce (feature-a) : Edit file foo at line 7 on branch feature-a by bstachmann.<br>* | 228f19d : Edit file bar at line 3 on branch master by bstachmann.<br>|/  <br>* c9ab46a (origin/master) Initial edit before cloning<br>* 70e994a Initial edit before cloning<br><br></code></pre>


## Lösung zu Schritt 3 - Merge analysieren

Zeige, welche Commits vom `master` im Merge hinzugekommen sind.
Zeige, welche Commits von `feature-a` im Merge hinzugekommen sind.
Zeige ebenfalls die Änderungen (Diffs) für beide Seiten.


<pre><code>repo $ <b>git log HEAD^2..HEAD^1</b><br><br>commit 228f19d1a8ad2c2bc75ae73e083c25731fffa114<br>Author: bstachmann &lt;egal&gt;<br>Date:   Thu Jul 8 20:41:20 2021 +0200<br><br>    : Edit file bar at line 3 on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git log HEAD^1..HEAD^2</b><br><br>commit 85aa0ce83f6152109b4866062c9eabf8f5f9d046<br>Author: bstachmann &lt;egal&gt;<br>Date:   Thu Jul 8 20:41:20 2021 +0200<br><br>    : Edit file foo at line 7 on branch feature-a by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^2...HEAD^1</b><br><br>diff --git a/bar b/bar<br>index 36fe753..01cfa77 100644<br>--- a/bar<br>+++ b/bar<br>@@ -1,7 +1,7 @@<br> line 0 created<br> line 1 created<br> line 2 created<br>-line 3 created<br>+line 3 Edit file bar at line 3 on branch master by bstachmann. / line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br><br></code></pre>



<pre><code>repo $ <b>git diff HEAD^1...HEAD^2</b><br><br>diff --git a/foo b/foo<br>index 36fe753..50c9a7f 100644<br>--- a/foo<br>+++ b/foo<br>@@ -5,7 +5,7 @@ line 3 created<br> line 4 created<br> line 5 created<br> line 6 created<br>-line 7 created<br>+line 7 Edit file foo at line 7 on branch feature-a by bstachmann. / line 7 created<br> line 8 created<br> line 9 created<br> line 10 created<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-branching.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

