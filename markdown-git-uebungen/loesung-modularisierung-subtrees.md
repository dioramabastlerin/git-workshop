---
layout: page
title: <code>modularisierung-subtrees</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Module als Subtree einbinden

Binde die Module `mod-a.git` und `mod-b.git`
per `subtree add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>subtrees $ <b>git subtree add --prefix=mod-a ../mod-a.git main</b><br><br>git fetch ../mod-a.git main<br>From ../mod-a<br> * branch            main       -&gt; FETCH_HEAD<br>Added dir 'mod-a'<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree add --prefix=mod-b ../mod-b.git main</b><br><br>git fetch ../mod-b.git main<br>From ../mod-b<br> * branch            main       -&gt; FETCH_HEAD<br>Added dir 'mod-b'<br><br></code></pre>



<pre><code>subtrees $ <b>git ls-tree -r HEAD</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-a/anton<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-b/berta<br><br></code></pre>


## Lösung zu Schritt 2 - Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 7 on branch main by bjoern.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 7 on branch main by bjoern. &quot;</b><br><br>[main 778b41f] : Edit file berta at line 7 on branch main by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit 778b41f2ecc72cb002e187200e5b4c03493ef7f6<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file berta at line 7 on branch main by bjoern.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   6380ac8..778b41f  main -&gt; main<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git subtree pull --prefix=mod-b ../mod-b.git main</b><br><br>Merge made by the 'ort' strategy.<br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../mod-b<br> * branch            main       -&gt; FETCH_HEAD<br><br></code></pre>



<pre><code>subtrees $ <b>git show --stat </b><br><br>commit 5d6608357a2be450cdb11c212f2985e144c02414<br>Merge: ab1dcc7 778b41f<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Merge commit '778b41f2ecc72cb002e187200e5b4c03493ef7f6'<br><br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Änderung in ein Modul übertragen

Gehe in `subtrees` ändere `mod-a/anton` und committe.
Übertrage die Änderung per `subtree push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file mod-a/anton at line 3 on branch main by bjoern.</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git commit -am &quot;`mod-a/anton`: Edit file mod-a/anton at line 3 on branch main by bjoern. &quot;</b><br><br>[main 816bfab] : Edit file mod-a/anton at line 3 on branch main by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: mod-a/anton: Permission denied<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree push --prefix=mod-a ../mod-a.git main</b><br><br>git push using:  ../mod-a.git main<br>1/8 (0) [0]<br>2/8 (0) [0]<br>3/8 (0) [0]<br>4/8 (1) [0]<br>5/8 (2) [0]<br>6/8 (3) [0]<br>7/8 (4) [0]<br>8/8 (5) [0]<br>To ../mod-a.git<br>   d967be7..b641f6e  b641f6edf0d961d892939aa28f7d036f474eb2c9 -&gt; main<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit b641f6edf0d961d892939aa28f7d036f474eb2c9<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file mod-a/anton at line 3 on branch main by bjoern.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Übergeordnetes Repo klonen

Klone `subtrees` zu `mysubtrees`.
Untersuche die `HEAD` Verzeichnisstruktur,
und den Commit-graphen.


<pre><code>$ <b>git clone subtrees mysubtrees</b><br><br>Cloning into 'mysubtrees'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubtrees</b><br><br><br></code></pre>


Man sieht, dass die Einbettungen als normale Dateien und Verzeichnisse im `HEAD`-Tree erscheinen


<pre><code>mysubtrees $ <b>git ls-tree -r HEAD .</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob f6f88216419d5191edb38648d3055abd86ce9ae0	mod-a/anton<br>100644 blob d79580ecb5760506dca9f367f55c8ad7955c06e3	mod-b/berta<br><br></code></pre>


Im Commit-Graphen sieht man, woher die Daten kommen.


<pre><code>mysubtrees $ <b>git log --graph --oneline --stat</b><br><br>* 816bfab : Edit file mod-a/anton at line 3 on branch main by bjoern.<br>|  mod-a/anton | 2 +-<br>|  1 file changed, 1 insertion(+), 1 deletion(-)<br>*   5d66083 Merge commit '778b41f2ecc72cb002e187200e5b4c03493ef7f6'<br>|\  <br>| * 778b41f : Edit file berta at line 7 on branch main by bjoern.<br>| |  berta | 2 +-<br>| |  1 file changed, 1 insertion(+), 1 deletion(-)<br>* | ab1dcc7 Add 'mod-b/' from commit '6380ac8847c66e47b766349ceea5fa77df4436ac'<br>|\| <br>| * 6380ac8 Created file berta on branch main by bjoern.<br>|    berta | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>*   b0bb195 Add 'mod-a/' from commit 'd967be7fe6b74e34d2307dd18d2e93dd499e8f2c'<br>|\  <br>| * d967be7 Created file anton on branch main by bjoern.<br>|    anton | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>* 74f11b3 Created file README on branch main by bjoern.<br>   README | 12 ++++++++++++<br>   1 file changed, 12 insertions(+)<br><br></code></pre>



<pre><code>mysubtrees $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-subtrees.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

