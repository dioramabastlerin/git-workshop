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


<pre><code>$ <b>git init application </b><br><br>Initialized empty Git repository in /workspace/git-workshop/build/git-uebungen/loesungen/modularisierung-repositorys-zusammenfuehren/application/.git/<br><br></code></pre>



<pre><code>$ <b>cd application</b><br><br><br></code></pre>


Wir erzeugen ein erstes Commmit, damit der `subtree`-Befehl ausgeführt werden kann.


<pre><code>application $ <b># created file 'README'</b><br><br><br></code></pre>



<pre><code>application $ <b>git add README</b><br><br><br></code></pre>



<pre><code>application $ <b>git commit -am &quot;Created file README on branch main by bjoern. &quot;</b><br><br>[main (root-commit) 74f11b3] Created file README on branch main by bjoern.<br> 1 file changed, 12 insertions(+)<br> create mode 100644 README<br><br></code></pre>


Dann fügen wir die Repos mit `subtree` hinzu:


<pre><code>application $ <b>git subtree add --prefix=backend ../backend.git main</b><br><br>git fetch ../backend.git main<br>From ../backend<br> * branch            main       -&gt; FETCH_HEAD<br>Added dir 'backend'<br><br></code></pre>



<pre><code>application $ <b>git subtree add --prefix=ui ../ui.git main</b><br><br>git fetch ../ui.git main<br>From ../ui<br> * branch            main       -&gt; FETCH_HEAD<br>Added dir 'ui'<br><br></code></pre>


Man sieht ui und backend wurden mitsamt Historie zusammengeführt:


<pre><code>application $ <b>git ls-tree -r --name-only HEAD</b><br><br>README<br>backend/src/Backend.java<br>backend/test/BackendTest.java<br>ui/src/UI.java<br>ui/test/UITest.java<br><br></code></pre>



<pre><code>application $ <b>git log --oneline --graph</b><br><br>*   f04721f Add 'ui/' from commit '5791af8bb65732c78dd9802fdac6bfe0aa218ced'<br>|\  <br>| * 5791af8 Created file UITest.java on branch main by bjoern.<br>| * 292845b Created file UI.java on branch main by bjoern.<br>*   05a8fee Add 'backend/' from commit '4abbae294702c64cd9532bf814f7cb76db9fb963'<br>|\  <br>| * 4abbae2 Created file BackendTest.java on branch main by bjoern.<br>| * 9ed7ede Created file Backend.java on branch main by bjoern.<br>* 74f11b3 Created file README on branch main by bjoern.<br><br></code></pre>



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



<pre><code>gesamt $ <b>git commit -m 'backend-Verzeichnis angelegt'</b><br><br>[main 0487992] backend-Verzeichnis angelegt<br> 2 files changed, 0 insertions(+), 0 deletions(-)<br> rename {src =&gt; backend/src}/Backend.java (100%)<br> rename {test =&gt; backend/test}/BackendTest.java (100%)<br><br></code></pre>


Inhalt des UI-Repository in einen lokalen Branch `uimain` holen:


<pre><code>gesamt $ <b>git remote add ui ../ui/</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git fetch ui</b><br><br>From ../ui<br> * [new branch]      main       -&gt; ui/main<br><br></code></pre>



<pre><code>gesamt $ <b>git checkout -b uimain ui/main</b><br><br>branch 'uimain' set up to track 'ui/main'.<br>Switched to a new branch 'uimain'<br><br></code></pre>


UI-Dateien in Unterverzeichnis verschieben:


<pre><code>gesamt $ <b>mkdir ui</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git mv src test ui</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git commit -m 'ui-Verzeichnis angelegt'</b><br><br>[uimain 43e14db] ui-Verzeichnis angelegt<br> 2 files changed, 0 insertions(+), 0 deletions(-)<br> rename {src =&gt; ui/src}/UI.java (100%)<br> rename {test =&gt; ui/test}/UITest.java (100%)<br><br></code></pre>


`uimain` integrieren:


<pre><code>gesamt $ <b>git checkout main</b><br><br>Your branch is ahead of 'origin/main' by 1 commit.<br>  (use &quot;git push&quot; to publish your local commits)<br>Switched to branch 'main'<br><br></code></pre>



<pre><code>gesamt $ <b>git merge uimain --allow-unrelated-histories</b><br><br>Merge made by the 'ort' strategy.<br> ui/src/UI.java      | 12 ++++++++++++<br> ui/test/UITest.java | 12 ++++++++++++<br> 2 files changed, 24 insertions(+)<br> create mode 100644 ui/src/UI.java<br> create mode 100644 ui/test/UITest.java<br><br></code></pre>


Man sieht ui und backend wurden mitsamt Historie zusammengeführt:


<pre><code>gesamt $ <b>git ls-tree -r --name-only HEAD</b><br><br>backend/src/Backend.java<br>backend/test/BackendTest.java<br>ui/src/UI.java<br>ui/test/UITest.java<br><br></code></pre>



<pre><code>gesamt $ <b>git log --oneline --graph</b><br><br>*   dc42d8d Merge branch 'uimain'<br>|\  <br>| * 43e14db ui-Verzeichnis angelegt<br>| * 5791af8 Created file UITest.java on branch main by bjoern.<br>| * 292845b Created file UI.java on branch main by bjoern.<br>* 0487992 backend-Verzeichnis angelegt<br>* 4abbae2 Created file BackendTest.java on branch main by bjoern.<br>* 9ed7ede Created file Backend.java on branch main by bjoern.<br><br></code></pre>



<pre><code>gesamt $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-repositorys-zusammenfuehren.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

