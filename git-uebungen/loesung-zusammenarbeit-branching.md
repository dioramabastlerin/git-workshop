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


[Zur Aufgabe](aufgabe-zusammenarbeit-branching.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

