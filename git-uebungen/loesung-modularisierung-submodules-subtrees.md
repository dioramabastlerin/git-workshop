---
layout: page
title: <code>modularisierung-submodules-subtrees</code>
parent: Lösungen

---
## Lösung zu Subtrees

# Subtrees

## Lösung zu Schritt 2 - Module als Subtree einbinden

Binde die Module `mod-a.git` und `mod-b.git`
per `subtree add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>subtrees $ <b>git subtree add --prefix=mod-a ../mod-a.git master</b><br><br>git fetch ../mod-a.git master<br>warning: no common commits<br>From ../mod-a<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'mod-a'<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree add --prefix=mod-b ../mod-b.git master</b><br><br>git fetch ../mod-b.git master<br>warning: no common commits<br>From ../mod-b<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'mod-b'<br><br></code></pre>



<pre><code>subtrees $ <b>git ls-tree -r HEAD</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-a/anton<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-b/berta<br><br></code></pre>


## Lösung zu Schritt 3 - Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 7 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 7 on branch master by bstachmann. &quot;</b><br><br>[master 514c54c] : Edit file berta at line 7 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit 514c54c9da598eb16391c225b908d6742d29161d<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:40:53 2021 +0200<br><br>    : Edit file berta at line 7 on branch master by bstachmann.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   83afffb..514c54c  master -&gt; master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git subtree pull --prefix=mod-b ../mod-b.git master</b><br><br>Merge made by the 'recursive' strategy.<br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../mod-b<br> * branch            master     -&gt; FETCH_HEAD<br><br></code></pre>



<pre><code>subtrees $ <b>git show --stat </b><br><br>commit 7a8eb9e00b9ceb07fc21adc055a0db01ad81df06<br>Merge: 21bda08 514c54c<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:40:53 2021 +0200<br><br>    Merge commit '514c54c9da598eb16391c225b908d6742d29161d'<br><br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Änderung in ein Modul übertragen

Gehe in `subtrees` ändere `mod-a/anton` und committe.
Übertrage die Änderung per `subtree push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file mod-a/anton at line 3 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git commit -am &quot;`mod-a/anton`: Edit file mod-a/anton at line 3 on branch master by bstachmann. &quot;</b><br><br>[master 6abb42e] : Edit file mod-a/anton at line 3 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: mod-a/anton: Permission denied<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree push --prefix=mod-a ../mod-a.git master</b><br><br>git push using:  ../mod-a.git master<br>1/8 (0) [0]<br>2/8 (0) [0]<br>3/8 (0) [0]<br>3/8 (1) [1]<br>3/8 (1) [2]<br>4/8 (1) [2]<br>5/8 (2) [2]<br>5/8 (3) [3]<br>6/8 (3) [3]<br>7/8 (4) [3]<br>7/8 (5) [4]<br>8/8 (5) [4]<br>To ../mod-a.git<br>   c66d310..3448cc7  3448cc7b98202df60c8e3e990d8415c2feb08fc3 -&gt; master<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit 3448cc7b98202df60c8e3e990d8415c2feb08fc3<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:40:53 2021 +0200<br><br>    : Edit file mod-a/anton at line 3 on branch master by bstachmann.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 5 - Übergeordnetes Repo klonen

Klone `subtrees` zu `mysubtrees`.
Untersuche die `HEAD` Verzeichnisstruktur,
und den Commit-graphen.


<pre><code>$ <b>git clone subtrees mysubtrees</b><br><br>Cloning into 'mysubtrees'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubtrees</b><br><br><br></code></pre>


Man sieht, dass die Einbettungen als normale Dateien und Verzeichnisse im `HEAD`-Tree erscheinen


<pre><code>mysubtrees $ <b>git ls-tree -r HEAD .</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob 2075e0a18eb61b8b29c4697dac2cc9b9f6e91da1	mod-a/anton<br>100644 blob 7eb8cd97b1c65c3eadaa79f3054a63a3cac42551	mod-b/berta<br><br></code></pre>


Im Commit-Graphen sieht man, woher die Daten kommen.


<pre><code>mysubtrees $ <b>git log --graph --oneline --stat</b><br><br>* 6abb42e : Edit file mod-a/anton at line 3 on branch master by bstachmann.<br>|  mod-a/anton | 2 +-<br>|  1 file changed, 1 insertion(+), 1 deletion(-)<br>*   7a8eb9e Merge commit '514c54c9da598eb16391c225b908d6742d29161d'<br>|\  <br>| * 514c54c : Edit file berta at line 7 on branch master by bstachmann.<br>| |  berta | 2 +-<br>| |  1 file changed, 1 insertion(+), 1 deletion(-)<br>* | 21bda08 Add 'mod-b/' from commit '83afffb66102fcba990d486ae7bdb69215c07b38'<br>|\| <br>| * 83afffb Created file berta on branch master by bstachmann.<br>|    berta | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>*   f43845e Add 'mod-a/' from commit 'c66d31003b40a5fe82c188cc70cd90ed6cb28027'<br>|\  <br>| * c66d310 Created file anton on branch master by bstachmann.<br>|    anton | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>* a87d5ac Created file README on branch master by bstachmann.<br>   README | 12 ++++++++++++<br>   1 file changed, 12 insertions(+)<br><br></code></pre>



<pre><code>mysubtrees $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Submodules

# Submodules

## Lösung zu Schritt 7 - Module als Submodule einbinden

Binde die Module `mod-a.git` und `mod-b.git`
per `submodule add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>submodules $ <b>git submodule add  ../mod-a.git mod-a</b><br><br>Cloning into '/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/submodules/mod-a'...<br>done.<br><br></code></pre>



<pre><code>submodules $ <b>git submodule add  ../mod-b.git mod-b</b><br><br>Cloning into '/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/submodules/mod-b'...<br>done.<br><br></code></pre>


Man sieht, dass die Module als eigenständige Git-Repositorys mit separatem `.git`-Verzeichnis eingebettet wurden.


<pre><code>submodules $ <b>ls -lah mod-a mod-b</b><br><br>mod-a:<br>total 16K<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  4 14:40 .<br>drwxrwxr-x 5 bjoern bjoern 4,0K Jul  4 14:40 ..<br>-rw-rw-r-- 1 bjoern bjoern  254 Jul  4 14:40 anton<br>-rw-rw-r-- 1 bjoern bjoern   30 Jul  4 14:40 .git<br><br>mod-b:<br>total 16K<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  4 14:40 .<br>drwxrwxr-x 5 bjoern bjoern 4,0K Jul  4 14:40 ..<br>-rw-rw-r-- 1 bjoern bjoern  248 Jul  4 14:40 berta<br>-rw-rw-r-- 1 bjoern bjoern   30 Jul  4 14:40 .git<br><br></code></pre>


Achtung! Die submodule wurden hinzugefügt, aber es fehlt noch ein Commit.


<pre><code>submodules $ <b>git status</b><br><br>On branch master<br>Changes to be committed:<br>  (use &quot;git restore --staged &lt;file&gt;...&quot; to unstage)<br>	new file:   .gitmodules<br>	new file:   mod-a<br>	new file:   mod-b<br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -m 'add mod-a and mod-b'</b><br><br>[master 549d66a] add mod-a and mod-b<br> 3 files changed, 8 insertions(+)<br> create mode 100644 .gitmodules<br> create mode 160000 mod-a<br> create mode 160000 mod-b<br><br></code></pre>


## Lösung zu Schritt 8 - Subtree: Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `submodules/mod-b` und hole die Änderungen per `pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 8 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 8 on branch master by bstachmann. &quot;</b><br><br>[master 0d85341] : Edit file berta at line 8 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit 0d853418fad59f47c057083e3d1cb801bd8ec412<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:40:54 2021 +0200<br><br>    : Edit file berta at line 8 on branch master by bstachmann.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   514c54c..0d85341  master -&gt; master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>



<pre><code>submodules $ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git pull</b><br><br>Updating 514c54c..0d85341<br>Fast-forward<br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-b<br>   514c54c..0d85341  master     -&gt; origin/master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git add mod-b</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -am 'updated mod-b'</b><br><br>[master 3190895] updated mod-b<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 9 - Änderung in ein Modul übertragen

Gehe in `subtrees/mod-a` ändere `anton` und committe.
Übertrage die Änderung per `push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>



<pre><code>submodules $ <b>cd mod-a</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file anton at line 5 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>mod-a $ <b>git commit -am &quot;`anton`: Edit file anton at line 5 on branch master by bstachmann. &quot;</b><br><br>[master 3e880f9] : Edit file anton at line 5 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: anton: command not found<br><br></code></pre>



<pre><code>mod-a $ <b>git push</b><br><br>To /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-a.git<br>   3448cc7..3e880f9  master -&gt; master<br><br></code></pre>



<pre><code>mod-a $ <b>cd ..</b><br><br><br></code></pre>


Nicht vergessen: Änderungen am im übergeordenten Repository committen.


<pre><code>submodules $ <b>git add mod-a</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -m 'new version of mod-a'</b><br><br>[master 4f9a460] new version of mod-a<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit 3e880f9f79e3e18c4467455c8f44ae0db00ffd9b<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:40:54 2021 +0200<br><br>    : Edit file anton at line 5 on branch master by bstachmann.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 10 - Übergeordnetes Repo klonen

Klone `submodules` zu `mysubmodules`.
Untersuche die Verzeichnisstruktur.
Vergiß nicht, ein `submodule update` auszuführen.


<pre><code>$ <b>git clone submodules mysubmodules</b><br><br>Cloning into 'mysubmodules'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubmodules</b><br><br><br></code></pre>


Die Modulverzeichnisse sind da aber noch leer:


<pre><code>mysubmodules $ <b>ls -lah mod-a mod-b</b><br><br>mod-a:<br>total 8,0K<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  4 14:40 .<br>drwxrwxr-x 5 bjoern bjoern 4,0K Jul  4 14:40 ..<br><br>mod-b:<br>total 8,0K<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  4 14:40 .<br>drwxrwxr-x 5 bjoern bjoern 4,0K Jul  4 14:40 ..<br><br></code></pre>


Jetzt holen wir die Module:


<pre><code>mysubmodules $ <b>git submodule update --init</b><br><br>Submodule path 'mod-a': checked out '3e880f9f79e3e18c4467455c8f44ae0db00ffd9b'<br>Submodule path 'mod-b': checked out '0d853418fad59f47c057083e3d1cb801bd8ec412'<br>Submodule 'mod-a' (/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-a.git) registered for path 'mod-a'<br>Submodule 'mod-b' (/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-b.git) registered for path 'mod-b'<br>Cloning into '/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mysubmodules/mod-a'...<br>done.<br>Cloning into '/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mysubmodules/mod-b'...<br>done.<br><br></code></pre>



<pre><code>mysubmodules $ <b>ls -lah mod-a mod-b</b><br><br>mod-a:<br>total 16K<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  4 14:40 .<br>drwxrwxr-x 5 bjoern bjoern 4,0K Jul  4 14:40 ..<br>-rw-rw-r-- 1 bjoern bjoern  321 Jul  4 14:40 anton<br>-rw-rw-r-- 1 bjoern bjoern   30 Jul  4 14:40 .git<br><br>mod-b:<br>total 16K<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  4 14:40 .<br>drwxrwxr-x 5 bjoern bjoern 4,0K Jul  4 14:40 ..<br>-rw-rw-r-- 1 bjoern bjoern  315 Jul  4 14:40 berta<br>-rw-rw-r-- 1 bjoern bjoern   30 Jul  4 14:40 .git<br><br></code></pre>



<pre><code>mysubmodules $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-submodules-subtrees.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

