---
layout: page
title: <code>zusammenarbeit-rebasing</code>
parent: Aufgaben

---
# Übung - Rebasing



Rebasing ist, neben dem Merging, eine weitere Möglichkeit,
Änderung zu integrieren.

## Infos

* `git rebase` 

## Tipps

* `git rebase`
  
## Ausgangssituation



<pre><code>$ <b>git init repo </b><br><br>Initialized empty Git repository in /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/zusammenarbeit-rebasing/repo/.git/<br><br></code></pre>



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>



<pre><code>repo $ <b># created file 'foo'</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add foo</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;Created file foo on branch master by bstachmann. &quot;</b><br><br>[master (root-commit) 84890d5] Created file foo on branch master by bstachmann.<br> 1 file changed, 12 insertions(+)<br> create mode 100644 foo<br><br></code></pre>



<pre><code>repo $ <b># created file 'bar'</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add bar</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;Created file bar on branch master by bstachmann. &quot;</b><br><br>[master e54ac5a] Created file bar on branch master by bstachmann.<br> 1 file changed, 12 insertions(+)<br> create mode 100644 bar<br><br></code></pre>



<pre><code>repo $ <b>git branch feature HEAD</b><br><br><br></code></pre>



<pre><code>repo $ <b>git checkout feature</b><br><br>Switched to branch 'feature'<br><br></code></pre>



<pre><code>repo $ <b># Feature anfangen.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`foo`: Feature anfangen. &quot;</b><br><br>[feature 76ff285] : Feature anfangen.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>repo $ <b># Feature weitermachen.</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`foo`: Feature weitermachen. &quot;</b><br><br>[feature 7baffc3] : Feature weitermachen.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: foo: command not found<br><br></code></pre>



<pre><code>repo $ <b>git checkout master</b><br><br>Switched to branch 'master'<br><br></code></pre>



<pre><code>repo $ <b># Neuerung auf dem master</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;`bar`: Neuerung auf dem master &quot;</b><br><br>[master ca30042] : Neuerung auf dem master<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: bar: command not found<br><br></code></pre>



<pre><code>repo $ <b>git checkout feature</b><br><br>Switched to branch 'feature'<br><br></code></pre>


## Schritt 1 - Feature-Branch per Rebase aktualiseren.

Starte im Verzeichnis `aufgaben/repo`.

Auf dem master gibt es Neuerungen.

Lasse Dir den Commit-Graphen über alle Branches zeigen.

Aktualisiere Deinen Feature-Branch.

Lasse Dir den Commit-Graphen über alle Branches zeigen.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-zusammenarbeit-rebasing.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

