---
layout: page
title: <code>modularisierung-subtrees</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Module als Subtree einbinden

Binde die Module `mod-a.git` und `mod-b.git`
per `subtree add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>subtrees $ <b>git subtree add --prefix=mod-a ../mod-a.git master</b><br><br>git fetch ../mod-a.git master<br>From ../mod-a<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'mod-a'<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree add --prefix=mod-b ../mod-b.git master</b><br><br>git fetch ../mod-b.git master<br>From ../mod-b<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'mod-b'<br><br></code></pre>



<pre><code>subtrees $ <b>git ls-tree -r HEAD</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-a/anton<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-b/berta<br><br></code></pre>


## Lösung zu Schritt 2 - Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 7 on branch master by bjoern.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 7 on branch master by bjoern. &quot;</b><br><br>[master 0c49fa1] : Edit file berta at line 7 on branch master by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit 0c49fa1d6e2482daab9690f223053d12125bde69<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file berta at line 7 on branch master by bjoern.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   2181f8e..0c49fa1  master -&gt; master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git subtree pull --prefix=mod-b ../mod-b.git master</b><br><br>Merge made by the 'ort' strategy.<br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../mod-b<br> * branch            master     -&gt; FETCH_HEAD<br><br></code></pre>



<pre><code>subtrees $ <b>git show --stat </b><br><br>commit f29f778fdb956642c1c2c0b606dfd0f54652d27a<br>Merge: ae838a9 0c49fa1<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Merge commit '0c49fa1d6e2482daab9690f223053d12125bde69'<br><br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Änderung in ein Modul übertragen

Gehe in `subtrees` ändere `mod-a/anton` und committe.
Übertrage die Änderung per `subtree push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file mod-a/anton at line 3 on branch master by bjoern.</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git commit -am &quot;`mod-a/anton`: Edit file mod-a/anton at line 3 on branch master by bjoern. &quot;</b><br><br>[master b22b25f] : Edit file mod-a/anton at line 3 on branch master by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: mod-a/anton: Permission denied<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree push --prefix=mod-a ../mod-a.git master</b><br><br>git push using:  ../mod-a.git master<br>1/8 (0) [0]<br>2/8 (0) [0]<br>3/8 (0) [0]<br>4/8 (1) [0]<br>5/8 (2) [0]<br>6/8 (3) [0]<br>7/8 (4) [0]<br>8/8 (5) [0]<br>To ../mod-a.git<br>   d0dd96a..2c3dc4e  2c3dc4ec872d63a2596a1fa0ab1e5e0fc4686a2e -&gt; master<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit 2c3dc4ec872d63a2596a1fa0ab1e5e0fc4686a2e<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file mod-a/anton at line 3 on branch master by bjoern.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Übergeordnetes Repo klonen

Klone `subtrees` zu `mysubtrees`.
Untersuche die `HEAD` Verzeichnisstruktur,
und den Commit-graphen.


<pre><code>$ <b>git clone subtrees mysubtrees</b><br><br>Cloning into 'mysubtrees'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubtrees</b><br><br><br></code></pre>


Man sieht, dass die Einbettungen als normale Dateien und Verzeichnisse im `HEAD`-Tree erscheinen


<pre><code>mysubtrees $ <b>git ls-tree -r HEAD .</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob e13f92d21eca3ec53392f17b551e3e9606d55bfb	mod-a/anton<br>100644 blob f2dd148b66cf43c930b5752ca9585eb103eec97d	mod-b/berta<br><br></code></pre>


Im Commit-Graphen sieht man, woher die Daten kommen.


<pre><code>mysubtrees $ <b>git log --graph --oneline --stat</b><br><br>* b22b25f : Edit file mod-a/anton at line 3 on branch master by bjoern.<br>|  mod-a/anton | 2 +-<br>|  1 file changed, 1 insertion(+), 1 deletion(-)<br>*   f29f778 Merge commit '0c49fa1d6e2482daab9690f223053d12125bde69'<br>|\  <br>| * 0c49fa1 : Edit file berta at line 7 on branch master by bjoern.<br>| |  berta | 2 +-<br>| |  1 file changed, 1 insertion(+), 1 deletion(-)<br>* | ae838a9 Add 'mod-b/' from commit '2181f8e707f1c92ffc65b2eaa8d36e0e3080b8a7'<br>|\| <br>| * 2181f8e Created file berta on branch master by bjoern.<br>|    berta | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>*   5f5ea4b Add 'mod-a/' from commit 'd0dd96acea51cf9164d3a48a732e1874728c1ce9'<br>|\  <br>| * d0dd96a Created file anton on branch master by bjoern.<br>|    anton | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>* 0fedcdf Created file README on branch master by bjoern.<br>   README | 12 ++++++++++++<br>   1 file changed, 12 insertions(+)<br><br></code></pre>



<pre><code>mysubtrees $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-subtrees.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

