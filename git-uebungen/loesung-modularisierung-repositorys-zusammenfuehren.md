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


<pre><code>$ <b>git init application </b><br><br>Initialized empty Git repository in /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-repositorys-zusammenfuehren/application/.git/<br><br></code></pre>



<pre><code>$ <b>cd application</b><br><br><br></code></pre>


Wir erzeugen ein erstes Commmit, damit der `subtree`-Befehl ausgeführt werden kann.


<pre><code>application $ <b># created file 'README'</b><br><br><br></code></pre>



<pre><code>application $ <b>git add README</b><br><br><br></code></pre>



<pre><code>application $ <b>git commit -am &quot;Created file README on branch master by bstachmann. &quot;</b><br><br>[master (root-commit) d055654] Created file README on branch master by bstachmann.<br> 1 file changed, 12 insertions(+)<br> create mode 100644 README<br><br></code></pre>


Dann fügen wir die Repos mit `subtree` hinzu:


<pre><code>application $ <b>git subtree add --prefix=backend ../backend.git master</b><br><br>git fetch ../backend.git master<br>warning: no common commits<br>From ../backend<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'backend'<br><br></code></pre>



<pre><code>application $ <b>git subtree add --prefix=ui ../ui.git master</b><br><br>git fetch ../ui.git master<br>warning: no common commits<br>From ../ui<br> * branch            master     -&gt; FETCH_HEAD<br>Added dir 'ui'<br><br></code></pre>


Man sieht ui und backend wurden mitsamt Historie zusammengeführt:


<pre><code>application $ <b>git ls-tree -r --name-only HEAD</b><br><br>README<br>backend/src/Backend.java<br>backend/test/BackendTest.java<br>ui/src/UI.java<br>ui/test/UITest.java<br><br></code></pre>



<pre><code>application $ <b>git log --oneline --graph</b><br><br>*   f600648 Add 'ui/' from commit '2be56672eaa6a11cd5b9a3ff6683e44a6864bfd7'<br>|\  <br>| * 2be5667 Created file UITest.java on branch master by bstachmann.<br>| * df7c248 Created file UI.java on branch master by bstachmann.<br>*   a64bb67 Add 'backend/' from commit '6bcbdbdc473c7baabbd82e28e53e5a1f622f6ef1'<br>|\  <br>| * 6bcbdbd Created file BackendTest.java on branch master by bstachmann.<br>| * 4807131 Created file Backend.java on branch master by bstachmann.<br>* d055654 Created file README on branch master by bstachmann.<br><br></code></pre>



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



<pre><code>gesamt $ <b>git commit -m 'backend-Verzeichnis angelegt'</b><br><br>[master bb2ac7f] backend-Verzeichnis angelegt<br> 2 files changed, 0 insertions(+), 0 deletions(-)<br> rename {src =&gt; backend/src}/Backend.java (100%)<br> rename {test =&gt; backend/test}/BackendTest.java (100%)<br><br></code></pre>


Inhalt des UI-Repository in einen lokalen Branch `uimaster` holen:


<pre><code>gesamt $ <b>git remote add ui ../ui/</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git fetch ui</b><br><br>warning: no common commits<br>From ../ui<br> * [new branch]      master     -&gt; ui/master<br><br></code></pre>



<pre><code>gesamt $ <b>git checkout -b uimaster ui/master</b><br><br>Branch 'uimaster' set up to track remote branch 'master' from 'ui'.<br>Switched to a new branch 'uimaster'<br><br></code></pre>


UI-Dateien in Unterverzeichnis verschieben:


<pre><code>gesamt $ <b>mkdir ui</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git mv src test ui</b><br><br><br></code></pre>



<pre><code>gesamt $ <b>git commit -m 'ui-Verzeichnis angelegt'</b><br><br>[uimaster 0488234] ui-Verzeichnis angelegt<br> 2 files changed, 0 insertions(+), 0 deletions(-)<br> rename {src =&gt; ui/src}/UI.java (100%)<br> rename {test =&gt; ui/test}/UITest.java (100%)<br><br></code></pre>


`uimaster` integrieren:


<pre><code>gesamt $ <b>git checkout master</b><br><br>Your branch is ahead of 'origin/master' by 1 commit.<br>  (use &quot;git push&quot; to publish your local commits)<br>Switched to branch 'master'<br><br></code></pre>



<pre><code>gesamt $ <b>git merge uimaster --allow-unrelated-histories</b><br><br>Merge made by the 'recursive' strategy.<br> ui/src/UI.java      | 12 ++++++++++++<br> ui/test/UITest.java | 12 ++++++++++++<br> 2 files changed, 24 insertions(+)<br> create mode 100644 ui/src/UI.java<br> create mode 100644 ui/test/UITest.java<br><br></code></pre>


Man sieht ui und backend wurden mitsamt Historie zusammengeführt:


<pre><code>gesamt $ <b>git ls-tree -r --name-only HEAD</b><br><br>backend/src/Backend.java<br>backend/test/BackendTest.java<br>ui/src/UI.java<br>ui/test/UITest.java<br><br></code></pre>



<pre><code>gesamt $ <b>git log --oneline --graph</b><br><br>*   e7a1409 Merge branch 'uimaster'<br>|\  <br>| * 0488234 ui-Verzeichnis angelegt<br>| * 2be5667 Created file UITest.java on branch master by bstachmann.<br>| * df7c248 Created file UI.java on branch master by bstachmann.<br>* bb2ac7f backend-Verzeichnis angelegt<br>* 6bcbdbd Created file BackendTest.java on branch master by bstachmann.<br>* 4807131 Created file Backend.java on branch master by bstachmann.<br><br></code></pre>



<pre><code>gesamt $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-repositorys-zusammenfuehren.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

