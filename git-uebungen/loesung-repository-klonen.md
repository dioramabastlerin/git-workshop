---
layout: page
title: <code>repository-klonen</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Klon durchführen

Erstelle einen Klon von `myfirstrepo` mit dem Namen `myfirstclone`.


<pre><code>$ <b>git clone myfirstrepo myfirstclone</b><br><br>Cloning into 'myfirstclone'...<br>done.<br><br></code></pre>


## Lösung zu Schritt 2 - Klon untersuchen

Schaue die Commits and und
zeige den Origin des Klons `myfirstclone`.
`origin` steht in der Regel für jenes Repository,
von dem geklont wurde.


<pre><code>$ <b>cd myfirstclone</b><br><br><br></code></pre>



<pre><code>myfirstclone $ <b>git log --oneline</b><br><br>0ff0778 Created file bar on branch master by bjoern.<br>dcf127a Created file foo on branch master by bjoern.<br><br></code></pre>



<pre><code>myfirstclone $ <b>git remote -v</b><br><br>origin	/workspace/git-workshop/build/git-uebungen/loesungen/repository-klonen/myfirstrepo (fetch)<br>origin	/workspace/git-workshop/build/git-uebungen/loesungen/repository-klonen/myfirstrepo (push)<br><br></code></pre>



<pre><code>myfirstclone $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Im Klon arbeiten

Erstelle ein Commit und zeige dann den Status.


<pre><code>$ <b>cd myfirstclone</b><br><br><br></code></pre>



<pre><code>myfirstclone $ <b># Edit file foo at line 3 on branch master by bjoern.</b><br><br><br></code></pre>



<pre><code>myfirstclone $ <b>git commit -am &quot;`foo`: Edit file foo at line 3 on branch master by bjoern. &quot;</b><br><br>[master f8652d1] : Edit file foo at line 3 on branch master by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>myfirstclone $ <b>git status</b><br><br>On branch master<br>Your branch is ahead of 'origin/master' by 1 commit.<br>  (use &quot;git push&quot; to publish your local commits)<br><br>nothing to commit, working tree clean<br><br></code></pre>



<pre><code>myfirstclone $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-repository-klonen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

