---
layout: page
title: <code>modularisierung-submodules</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Module als Submodule einbinden

Binde die Module `mod-a.git` und `mod-b.git`
per `submodule add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>submodules $ <b>git submodule add  ../mod-a.git mod-a</b><br><br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/submodules/mod-a'...<br>done.<br><br></code></pre>



<pre><code>submodules $ <b>git submodule add  ../mod-b.git mod-b</b><br><br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/submodules/mod-b'...<br>done.<br><br></code></pre>


Man sieht, dass die Module als eigenständige Git-Repositorys mit separatem `.git`-Verzeichnis eingebettet wurden.


<pre><code>submodules $ <b>ls -1 mod-a mod-b</b><br><br>mod-a:<br>anton<br><br>mod-b:<br>berta<br><br></code></pre>


Achtung! Die submodule wurden hinzugefügt, aber es fehlt noch ein Commit.


<pre><code>submodules $ <b>git status</b><br><br>On branch master<br>Changes to be committed:<br>  (use &quot;git restore --staged &lt;file&gt;...&quot; to unstage)<br>	new file:   .gitmodules<br>	new file:   mod-a<br>	new file:   mod-b<br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -m 'add mod-a and mod-b'</b><br><br>[master 5d6ed29] add mod-a and mod-b<br> 3 files changed, 8 insertions(+)<br> create mode 100644 .gitmodules<br> create mode 160000 mod-a<br> create mode 160000 mod-b<br><br></code></pre>


## Lösung zu Schritt 2 - Subtree: Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `submodules/mod-b` und hole die Änderungen per `pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 8 on branch master by bjoern.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 8 on branch master by bjoern. &quot;</b><br><br>[master 6f239fd] : Edit file berta at line 8 on branch master by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit 6f239fd2acf7bd5ea05e70579fc9827e8c567542<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file berta at line 8 on branch master by bjoern.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   2181f8e..6f239fd  master -&gt; master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>



<pre><code>submodules $ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git pull</b><br><br>Updating 2181f8e..6f239fd<br>Fast-forward<br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From /workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/mod-b<br>   2181f8e..6f239fd  master     -&gt; origin/master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git add mod-b</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -am 'updated mod-b'</b><br><br>[master 32544ac] updated mod-b<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Änderung in ein Modul übertragen

Gehe in `subtrees/mod-a` ändere `anton` und committe.
Übertrage die Änderung per `push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>



<pre><code>submodules $ <b>cd mod-a</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file anton at line 5 on branch master by bjoern.</b><br><br><br></code></pre>



<pre><code>mod-a $ <b>git commit -am &quot;`anton`: Edit file anton at line 5 on branch master by bjoern. &quot;</b><br><br>[master 54a5eef] : Edit file anton at line 5 on branch master by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: anton: command not found<br><br></code></pre>



<pre><code>mod-a $ <b>git push</b><br><br>To /workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/mod-a.git<br>   d0dd96a..54a5eef  master -&gt; master<br><br></code></pre>



<pre><code>mod-a $ <b>cd ..</b><br><br><br></code></pre>


Nicht vergessen: Änderungen am im übergeordenten Repository committen.


<pre><code>submodules $ <b>git add mod-a</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -m 'new version of mod-a'</b><br><br>[master d1c0195] new version of mod-a<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit 54a5eefdaa3628c00cbf903fb8e425590ede4b83<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file anton at line 5 on branch master by bjoern.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Übergeordnetes Repo klonen

Klone `submodules` zu `mysubmodules`.
Untersuche die Verzeichnisstruktur.
Vergiß nicht, ein `submodule update` auszuführen.


<pre><code>$ <b>git clone submodules mysubmodules</b><br><br>Cloning into 'mysubmodules'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubmodules</b><br><br><br></code></pre>


Die Modulverzeichnisse sind da aber noch leer:


<pre><code>mysubmodules $ <b>ls -1 mod-a mod-b</b><br><br>mod-a:<br><br>mod-b:<br><br></code></pre>


Jetzt holen wir die Module:


<pre><code>mysubmodules $ <b>git submodule update --init</b><br><br>Submodule path 'mod-a': checked out '54a5eefdaa3628c00cbf903fb8e425590ede4b83'<br>Submodule path 'mod-b': checked out '6f239fd2acf7bd5ea05e70579fc9827e8c567542'<br>Submodule 'mod-a' (/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/mod-a.git) registered for path 'mod-a'<br>Submodule 'mod-b' (/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/mod-b.git) registered for path 'mod-b'<br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/mysubmodules/mod-a'...<br>done.<br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules/mysubmodules/mod-b'...<br>done.<br><br></code></pre>



<pre><code>mysubmodules $ <b>ls -1 mod-a mod-b</b><br><br>mod-a:<br>anton<br><br>mod-b:<br>berta<br><br></code></pre>



<pre><code>mysubmodules $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-submodules.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

