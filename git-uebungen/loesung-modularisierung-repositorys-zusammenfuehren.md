---
layout: page
title: <code>modularisierung-repositorys-zusammenfuehren</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Zusammenführen `git subtree`


1. Erstelle ein Repo `application` mit einem Commit.
2. Füge `backend.git` in einem Unterverzeichnis `backend` hinzu.
3. Füge `ui.git` in einem Unterverzeichnis `ui` hinzu.
4. Untersuche Verzeichnissstruktur und Commit-Graphen


<pre><code>$ <b>git init application </b><br><br>Initialized empty Git repository in /workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-repositorys-zusammenfuehren/application/.git/<br>hint: Using 'master' as the name for the initial branch. This default branch name<br>hint: is subject to change. To configure the initial branch name to use in all<br>hint: of your new repositories, which will suppress this warning, call:<br>hint: <br>hint: 	git config --global init.defaultBranch &lt;name&gt;<br>hint: <br>hint: Names commonly chosen instead of 'master' are 'main', 'trunk' and<br>hint: 'development'. The just-created branch can be renamed via this command:<br>hint: <br>hint: 	git branch -m &lt;name&gt;<br><br></code></pre>



<pre><code>$ <b>cd application</b><br><br><br></code></pre>


Wir erzeugen ein erstes Commmit, damit der `subtree`-Befehl ausgeführt werden kann.


<pre><code>application $ <b># created file 'README'</b><br><br><br></code></pre>



<pre><code>application $ <b>git add README</b><br><br><br></code></pre>



<pre><code>application $ <b>git commit -am &quot;Created file README on branch master by bstachmann. &quot;</b><br><br>[master (root-commit) 29363b8] Created file README on branch master by bstachmann.<br> 1 file changed, 12 insertions(+)<br> create mode 100644 README<br><br></code></pre>


Dann fügen wir die Repos mit `subtree` hinzu:


<pre><code>application $ <b>git subtree add --prefix=backend ../backend.git master</b><br><br>git fetch ../backend.git master<br>From ../backend<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'backend'<br><br></code></pre>



<pre><code>application $ <b>git subtree add --prefix=ui ../ui.git master</b><br><br>git fetch ../ui.git master<br>From ../ui<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'ui'<br><br></code></pre>


Man sieht ui und backend wurden mitsamt Historie zusammengeführt:


<pre><code>application $ <b>git ls-tree -r --name-only HEAD</b><br><br>README<br>backend/src/Backend.java<br>backend/test/BackendTest.java<br>ui/src/UI.java<br>ui/test/UITest.java<br><br></code></pre>



<pre><code>application $ <b>git log --oneline --graph</b><br><br>*   608b560 Add 'ui/' from commit '55a52a8efd5f78d14d6e2ee07efc998c44ccf0e4'<br>|\  <br>| * 55a52a8 Created file UITest.java on branch master by bstachmann.<br>| * 17c40bc Created file UI.java on branch master by bstachmann.<br>*   6797edd Add 'backend/' from commit '36e250e57101711119a587ec6b7a612f97f892f3'<br>|\  <br>| * 36e250e Created file BackendTest.java on branch master by bstachmann.<br>| * 2e72ce4 Created file Backend.java on branch master by bstachmann.<br>* 29363b8 Created file README on branch master by bstachmann.<br><br></code></pre>



<pre><code>application $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 2 - Zusammenführen mit `fetch`, `mv` und `merge`

UI und Backend sollen in einem neuen Klon `gesamt` zusammengeführt werden.
Folge den Anweisungen im Kapitel *"Kleine Projekte zusammenführen"*.
Untersuche dann Verzeichnissstruktur und Commit-Graphen


<pre><code>$ <b>git clone backend gesamt</b><br><br>Cloning into 'gesamt'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd gesamt</b><br><br><br></code></pre>


Backend-Dateien in Unterverzeichnis verschieben:


<pre><code>gesamt $ <b>mkdir backend</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git mv src test backend</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git commit -m 'backend-Verzeichnis angelegt'</b><br><br>[master 30d70e0] backend-Verzeichnis angelegt<br> 2 files changed, 0 insertions(+), 0 deletions(-)<br> rename {src =&gt; backend/src}/Backend.java (100%)<br> rename {test =&gt; backend/test}/BackendTest.java (100%)<br><br></code></pre>


Inhalt des UI-Repository in einen lokalen Branch `uimaster` holen:


<pre><code>gesamt $ <b>git remote add ui ../ui/</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git fetch ui</b><br><br>From ../ui<br> * [new branch]      master     -&gt; ui/master<br><br></code></pre>



<pre><code>gesamt $ <b>git checkout -b uimaster ui/master</b><br><br>branch 'uimaster' set up to track 'ui/master'.<br>Switched to a new branch 'uimaster'<br><br></code></pre>


UI-Dateien in Unterverzeichnis verschieben:


<pre><code>gesamt $ <b>mkdir ui</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git mv src test ui</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git commit -m 'ui-Verzeichnis angelegt'</b><br><br>[uimaster ea420e3] ui-Verzeichnis angelegt<br> 2 files changed, 0 insertions(+), 0 deletions(-)<br> rename {src =&gt; ui/src}/UI.java (100%)<br> rename {test =&gt; ui/test}/UITest.java (100%)<br><br></code></pre>


`uimaster` integrieren:


<pre><code>gesamt $ <b>git checkout master</b><br><br>Your branch is ahead of 'origin/master' by 1 commit.<br>  (use &quot;git push&quot; to publish your local commits)<br>Switched to branch 'master'<br><br></code></pre>



<pre><code>gesamt $ <b>git merge uimaster --allow-unrelated-histories</b><br><br>Merge made by the 'ort' strategy.<br> ui/src/UI.java      | 12 ++++++++++++<br> ui/test/UITest.java | 12 ++++++++++++<br> 2 files changed, 24 insertions(+)<br> create mode 100644 ui/src/UI.java<br> create mode 100644 ui/test/UITest.java<br><br></code></pre>


Man sieht ui und backend wurden mitsamt Historie zusammengeführt:


<pre><code>gesamt $ <b>git ls-tree -r --name-only HEAD</b><br><br>backend/src/Backend.java<br>backend/test/BackendTest.java<br>ui/src/UI.java<br>ui/test/UITest.java<br><br></code></pre>



<pre><code>gesamt $ <b>git log --oneline --graph</b><br><br>*   3715c64 Merge branch 'uimaster'<br>|\  <br>| * ea420e3 ui-Verzeichnis angelegt<br>| * 55a52a8 Created file UITest.java on branch master by bstachmann.<br>| * 17c40bc Created file UI.java on branch master by bstachmann.<br>* 30d70e0 backend-Verzeichnis angelegt<br>* 36e250e Created file BackendTest.java on branch master by bstachmann.<br>* 2e72ce4 Created file Backend.java on branch master by bstachmann.<br><br></code></pre>



<pre><code>gesamt $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-repositorys-zusammenfuehren.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

