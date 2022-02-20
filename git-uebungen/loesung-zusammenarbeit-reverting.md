---
layout: page
title: <code>zusammenarbeit-reverting</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - TODO erstellen

Erstelle einen Branch `feature-a`, bearbeite die Datei `foo`
und erstelle ein Commit.
Wechsle dann zurück auf den `master` und bearbeite dort `bar`.
Zeige den Commit-Graphen.


<pre><code>repo $ <b>git revert -m 1 HEAD</b><br><br>Removing from-feature<br>[master f69e7c1] Revert &quot;Merge branch 'feature'&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 12 deletions(-)<br> delete mode 100644 from-feature<br><br></code></pre>



<pre><code>repo $ <b># Edit file README at line 3 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`README`: Edit file README at line 3 on branch master by bstachmann. &quot;</b><br><br>[master 08f9ffd] : Edit file README at line 3 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: README: command not found<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --graph</b><br><br>* 08f9ffd : Edit file README at line 3 on branch master by bstachmann.<br>* f69e7c1 Revert &quot;Merge branch 'feature'&quot;<br>*   c0ed48b Merge branch 'feature'<br>|\  <br>| * 63b3c1b Created file from-feature on branch feature by bstachmann.<br>* | 36b243c : Edit file README at line 2 on branch master by bstachmann.<br>|/  <br>* 29363b8 Created file README on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git switch feature</b><br><br>Switched to branch 'feature'<br><br></code></pre>



<pre><code>repo $ <b># Edit file from-feature at line 1 on branch feature by bstachmann.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`from-feature`: Edit file from-feature at line 1 on branch feature by bstachmann. &quot;</b><br><br>[feature 25a2c99] : Edit file from-feature at line 1 on branch feature by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: from-feature: command not found<br><br></code></pre>



<pre><code>repo $ <b>git switch master</b><br><br>Switched to branch 'master'<br><br></code></pre>



<pre><code>repo $ <b>git merge feature</b><br><br>CONFLICT (modify/delete): from-feature deleted in HEAD and modified in feature. Version feature of from-feature left in tree.<br>Automatic merge failed; fix conflicts and then commit the result.<br><br></code></pre>



<pre><code>repo $ <b>git merge --abort</b><br><br><br></code></pre>



<pre><code>repo $ <b>git revert HEAD~1</b><br><br>[master 6f1a7e7] Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br> Date: Thu Jul 29 00:00:00 2021 +0000<br> 1 file changed, 12 insertions(+)<br> create mode 100644 from-feature<br><br></code></pre>



<pre><code>repo $ <b>git merge feature</b><br><br>Merge made by the 'recursive' strategy.<br> from-feature | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git log --oneline --graph</b><br><br>*   36681bd Merge branch 'feature'<br>|\  <br>| * 25a2c99 : Edit file from-feature at line 1 on branch feature by bstachmann.<br>* | 6f1a7e7 Revert &quot;Revert &quot;Merge branch 'feature'&quot;&quot;<br>* | 08f9ffd : Edit file README at line 3 on branch master by bstachmann.<br>* | f69e7c1 Revert &quot;Merge branch 'feature'&quot;<br>* | c0ed48b Merge branch 'feature'<br>|\| <br>| * 63b3c1b Created file from-feature on branch feature by bstachmann.<br>* | 36b243c : Edit file README at line 2 on branch master by bstachmann.<br>|/  <br>* 29363b8 Created file README on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-zusammenarbeit-reverting.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

