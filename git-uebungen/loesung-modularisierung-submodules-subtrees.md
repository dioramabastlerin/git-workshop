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


<pre><code>subtrees $ <b>git subtree add --prefix=mod-a ../mod-a.git master</b><br><br>git fetch ../mod-a.git master<br>From ../mod-a<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'mod-a'<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree add --prefix=mod-b ../mod-b.git master</b><br><br>git fetch ../mod-b.git master<br>From ../mod-b<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'mod-b'<br><br></code></pre>



<pre><code>subtrees $ <b>git ls-tree -r HEAD</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-a/anton<br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	mod-b/berta<br><br></code></pre>


## Lösung zu Schritt 3 - Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 7 on branch master by dioramabastlerin.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 7 on branch master by dioramabastlerin. &quot;</b><br><br>[master bc7ce2b] : Edit file berta at line 7 on branch master by dioramabastlerin.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit bc7ce2bd104c3ba22f3ba28c6e28de38cffd653f<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file berta at line 7 on branch master by dioramabastlerin.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   250868e..bc7ce2b  master -&gt; master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git subtree pull --prefix=mod-b ../mod-b.git master</b><br><br>Merge made by the 'recursive' strategy.<br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>From ../mod-b<br> * branch            master     -&gt; FETCH_HEAD<br><br></code></pre>



<pre><code>subtrees $ <b>git show --stat </b><br><br>commit b4dc83d75ba4d7b1e1fc5a86d7d3d67752152b1b<br>Merge: 63394d2 bc7ce2b<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Merge commit 'bc7ce2bd104c3ba22f3ba28c6e28de38cffd653f'<br><br> mod-b/berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Änderung in ein Modul übertragen

Gehe in `subtrees` ändere `mod-a/anton` und committe.
Übertrage die Änderung per `subtree push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file mod-a/anton at line 3 on branch master by dioramabastlerin.</b><br><br><br></code></pre>



<pre><code>subtrees $ <b>git commit -am &quot;`mod-a/anton`: Edit file mod-a/anton at line 3 on branch master by dioramabastlerin. &quot;</b><br><br>[master 06007a3] : Edit file mod-a/anton at line 3 on branch master by dioramabastlerin.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: mod-a/anton: Permission denied<br><br></code></pre>



<pre><code>subtrees $ <b>git subtree push --prefix=mod-a ../mod-a.git master</b><br><br>git push using:  ../mod-a.git master<br>1/8 (0) [0]<br>2/8 (0) [0]<br>3/8 (0) [0]<br>3/8 (1) [1]<br>3/8 (1) [2]<br>4/8 (1) [2]<br>5/8 (2) [2]<br>5/8 (3) [3]<br>6/8 (3) [3]<br>7/8 (4) [3]<br>7/8 (5) [4]<br>8/8 (5) [4]<br>To ../mod-a.git<br>   2a1fd2e..782948d  782948d86b5881cf1ab79a7a8c6f7b532bd31405 -&gt; master<br><br></code></pre>



<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit 782948d86b5881cf1ab79a7a8c6f7b532bd31405<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file mod-a/anton at line 3 on branch master by dioramabastlerin.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 5 - Übergeordnetes Repo klonen

Klone `subtrees` zu `mysubtrees`.
Untersuche die `HEAD` Verzeichnisstruktur,
und den Commit-graphen.


<pre><code>$ <b>git clone subtrees mysubtrees</b><br><br>Cloning into 'mysubtrees'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubtrees</b><br><br><br></code></pre>


Man sieht, dass die Einbettungen als normale Dateien und Verzeichnisse im `HEAD`-Tree erscheinen


<pre><code>mysubtrees $ <b>git ls-tree -r HEAD .</b><br><br>100644 blob 36fe7538890f39b90ff7dee1d1f97ba3e3a9350c	README<br>100644 blob 1cdaf802520ad055f9670154cdb71b8a8e887460	mod-a/anton<br>100644 blob a40014824facdfc9a76db777f1151e868f2d1dd0	mod-b/berta<br><br></code></pre>


Im Commit-Graphen sieht man, woher die Daten kommen.


<pre><code>mysubtrees $ <b>git log --graph --oneline --stat</b><br><br>* 06007a3 : Edit file mod-a/anton at line 3 on branch master by dioramabastlerin.<br>|  mod-a/anton | 2 +-<br>|  1 file changed, 1 insertion(+), 1 deletion(-)<br>*   b4dc83d Merge commit 'bc7ce2bd104c3ba22f3ba28c6e28de38cffd653f'<br>|\  <br>| * bc7ce2b : Edit file berta at line 7 on branch master by dioramabastlerin.<br>| |  berta | 2 +-<br>| |  1 file changed, 1 insertion(+), 1 deletion(-)<br>* | 63394d2 Add 'mod-b/' from commit '250868eeee79d102c7b2f49135079a24058b7727'<br>|\| <br>| * 250868e Created file berta on branch master by dioramabastlerin.<br>|    berta | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>*   9adea9a Add 'mod-a/' from commit '2a1fd2e0500ae7d9c303e947d3da0b95e32993e7'<br>|\  <br>| * 2a1fd2e Created file anton on branch master by dioramabastlerin.<br>|    anton | 12 ++++++++++++<br>|    1 file changed, 12 insertions(+)<br>* 4152492 Created file README on branch master by dioramabastlerin.<br>   README | 12 ++++++++++++<br>   1 file changed, 12 insertions(+)<br><br></code></pre>



<pre><code>mysubtrees $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Submodules

# Submodules

## Lösung zu Schritt 7 - Module als Submodule einbinden

Binde die Module `mod-a.git` und `mod-b.git`
per `submodule add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>submodules $ <b>git submodule add  ../mod-a.git mod-a</b><br><br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/submodules/mod-a'...<br>done.<br><br></code></pre>



<pre><code>submodules $ <b>git submodule add  ../mod-b.git mod-b</b><br><br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/submodules/mod-b'...<br>done.<br><br></code></pre>


Man sieht, dass die Module als eigenständige Git-Repositorys mit separatem `.git`-Verzeichnis eingebettet wurden.


<pre><code>submodules $ <b>ls -1 mod-a mod-b</b><br><br>mod-a:<br>anton<br><br>mod-b:<br>berta<br><br></code></pre>


Achtung! Die submodule wurden hinzugefügt, aber es fehlt noch ein Commit.


<pre><code>submodules $ <b>git status</b><br><br>On branch master<br>Changes to be committed:<br>  (use &quot;git restore --staged &lt;file&gt;...&quot; to unstage)<br>	new file:   .gitmodules<br>	new file:   mod-a<br>	new file:   mod-b<br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -m 'add mod-a and mod-b'</b><br><br>[master d1ba418] add mod-a and mod-b<br> 3 files changed, 8 insertions(+)<br> create mode 100644 .gitmodules<br> create mode 160000 mod-a<br> create mode 160000 mod-b<br><br></code></pre>


## Lösung zu Schritt 8 - Subtree: Änderung aus einem Modul übernehmen

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `submodules/mod-b` und hole die Änderungen per `pull` ab.
Sieh Dir das übertragene Commit an.


<pre><code>$ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b># Edit file berta at line 8 on branch master by dioramabastlerin.</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git commit -am &quot;`berta`: Edit file berta at line 8 on branch master by dioramabastlerin. &quot;</b><br><br>[master 5dbfca1] : Edit file berta at line 8 on branch master by dioramabastlerin.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: berta: command not found<br><br></code></pre>



<pre><code>mod-b $ <b>git show --stat </b><br><br>commit 5dbfca121ec036962ce0720cb543ec2a474d2935<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file berta at line 8 on branch master by dioramabastlerin.<br><br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-b $ <b>git push</b><br><br>To ../mod-b.git<br>   bc7ce2b..5dbfca1  master -&gt; master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>



<pre><code>submodules $ <b>cd mod-b</b><br><br><br></code></pre>



<pre><code>mod-b $ <b>git pull</b><br><br>Updating bc7ce2b..5dbfca1<br>Fast-forward<br> berta | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>hint: Pulling without specifying how to reconcile divergent branches is<br>hint: discouraged. You can squelch this message by running one of the following<br>hint: commands sometime before your next pull:<br>hint: <br>hint:   git config pull.rebase false  # merge (the default strategy)<br>hint:   git config pull.rebase true   # rebase<br>hint:   git config pull.ff only       # fast-forward only<br>hint: <br>hint: You can replace &quot;git config&quot; with &quot;git config --global&quot; to set a default<br>hint: preference for all repositories. You can also pass --rebase, --no-rebase,<br>hint: or --ff-only on the command line to override the configured default per<br>hint: invocation.<br>From /workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-b<br>   bc7ce2b..5dbfca1  master     -&gt; origin/master<br><br></code></pre>



<pre><code>mod-b $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git add mod-b</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -am 'updated mod-b'</b><br><br>[master c58d682] updated mod-b<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 9 - Änderung in ein Modul übertragen

Gehe in `subtrees/mod-a` ändere `anton` und committe.
Übertrage die Änderung per `push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.


<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>



<pre><code>submodules $ <b>cd mod-a</b><br><br><br></code></pre>



<pre><code>mod-a $ <b># Edit file anton at line 5 on branch master by dioramabastlerin.</b><br><br><br></code></pre>



<pre><code>mod-a $ <b>git commit -am &quot;`anton`: Edit file anton at line 5 on branch master by dioramabastlerin. &quot;</b><br><br>[master 3b4254f] : Edit file anton at line 5 on branch master by dioramabastlerin.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: anton: command not found<br><br></code></pre>



<pre><code>mod-a $ <b>git push</b><br><br>To /workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-a.git<br>   782948d..3b4254f  master -&gt; master<br><br></code></pre>



<pre><code>mod-a $ <b>cd ..</b><br><br><br></code></pre>


Nicht vergessen: Änderungen am im übergeordenten Repository committen.


<pre><code>submodules $ <b>git add mod-a</b><br><br><br></code></pre>



<pre><code>submodules $ <b>git commit -m 'new version of mod-a'</b><br><br>[master 058840c] new version of mod-a<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd mod-a.git</b><br><br><br></code></pre>



<pre><code>mod-a.git $ <b>git show --stat </b><br><br>commit 3b4254fd7010da5b288320b533ff174fd1292a6c<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file anton at line 5 on branch master by dioramabastlerin.<br><br> anton | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>mod-a.git $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 10 - Übergeordnetes Repo klonen

Klone `submodules` zu `mysubmodules`.
Untersuche die Verzeichnisstruktur.
Vergiß nicht, ein `submodule update` auszuführen.


<pre><code>$ <b>git clone submodules mysubmodules</b><br><br>Cloning into 'mysubmodules'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd mysubmodules</b><br><br><br></code></pre>


Die Modulverzeichnisse sind da aber noch leer:


<pre><code>mysubmodules $ <b>ls -1 mod-a mod-b</b><br><br>mod-a:<br><br>mod-b:<br><br></code></pre>


Jetzt holen wir die Module:


<pre><code>mysubmodules $ <b>git submodule update --init</b><br><br>Submodule path 'mod-a': checked out '3b4254fd7010da5b288320b533ff174fd1292a6c'<br>Submodule path 'mod-b': checked out '5dbfca121ec036962ce0720cb543ec2a474d2935'<br>Submodule 'mod-a' (/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-a.git) registered for path 'mod-a'<br>Submodule 'mod-b' (/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mod-b.git) registered for path 'mod-b'<br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mysubmodules/mod-a'...<br>done.<br>Cloning into '/workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-submodules-subtrees/mysubmodules/mod-b'...<br>done.<br><br></code></pre>



<pre><code>mysubmodules $ <b>ls -1 mod-a mod-b</b><br><br>mod-a:<br>anton<br><br>mod-b:<br>berta<br><br></code></pre>



<pre><code>mysubmodules $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-submodules-subtrees.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

