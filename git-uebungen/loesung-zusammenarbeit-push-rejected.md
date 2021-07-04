---
layout: page
title: <code>zusammenarbeit-push-rejected</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Lokal Commit(s) erstellen

Bearbeite die Datei `frontend.java` und erstelle (mindestens) ein Commit mit den Änderungen.
Überprüfe danach mit `git status`, ob der Workspace sauber ist.


<pre><code>my-apollo $ <b># Edit file frontend.java at line 1 on branch master by bstachmann.</b><br><br><br></code></pre>



<pre><code>my-apollo $ <b>git commit -am &quot;`frontend.java`: Edit file frontend.java at line 1 on branch master by bstachmann. &quot;</b><br><br>[master 13b73c2] : Edit file frontend.java at line 1 on branch master by bstachmann.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: frontend.java: command not found<br><br></code></pre>


Und jetzt noch eben prüfen, ob `working tree clean` ist.


<pre><code>my-apollo $ <b>git status</b><br><br>On branch master<br>Your branch is ahead of 'origin/master' by 1 commit.<br>  (use &quot;git push&quot; to publish your local commits)<br><br>nothing to commit, working tree clean<br><br></code></pre>


## Lösung zu Schritt 2 - Push versuchen

Versuche jetzt Deine Änderungen zu pushen.


<pre><code>my-apollo $ <b>git push</b><br><br>To ../blessed-apollo.git<br> ! [rejected]        master -&gt; master (fetch first)<br>error: failed to push some refs to '../blessed-apollo.git'<br>hint: Updates were rejected because the remote contains work that you do<br>hint: not have locally. This is usually caused by another repository pushing<br>hint: to the same ref. You may want to first integrate the remote changes<br>hint: (e.g., 'git pull ...') before pushing again.<br>hint: See the 'Note about fast-forwards' in 'git push --help' for details.<br><br></code></pre>


Wie Du siehst, der Push wurde verweigert. 
Anscheinend war Anja schneller,
und hat ihre Änderungen zuerst nach `blessed-apollo.git` gepushed.

## Lösung zu Schritt 3 - (optional) Problem analysieren

Hole zunächt die Änderungen, ohne zu integrieren (`fetch`),
und lasse Dir die Änderungen von *Anja* zeigen.
 
 * Welche Commits hat Anja gemacht (`log`)?
 * Welche Unterschiede gibt es zweichen deiner und Anjas Version (symmetrisches `diff`)?
 * Welche Änderungen hat Anja gemacht (asymmetrisches `diff`)?

`fetch` holt die Daten, ohne den Workspace oder Deine lokalen Branches zu verändern.


<pre><code>my-apollo $ <b>git fetch</b><br><br>From ../blessed-apollo<br>   a4942ef..a18d521  master     -&gt; origin/master<br><br></code></pre>


Die Ausgabe zeigt, dass neue Commit für den `origin/master` geholt wurden

Die `..`-Notation zeigt, welche Commits hinzugekommen sind:


<pre><code>my-apollo $ <b>git log --oneline master..origin/master</b><br><br>a18d521 : Edit file backend.java at line 5 on branch master by anja.<br>6b8593c : Edit file backend.java at line 1 on branch master by anja.<br><br></code></pre>


Das normale (symmetrische) Diff zeig alle Unterschiede. 
Sowohl das, was du gemacht hast, als auch das, was Anja gemacht hat:"


<pre><code>my-apollo $ <b>git diff --stat HEAD origin/master</b><br><br> backend.java  | 4 ++--<br> frontend.java | 2 +-<br> 2 files changed, 3 insertions(+), 3 deletions(-)<br><br></code></pre>


Das asymmetrische Diff `...` zeigt nur jene Änderungen,
die Anja gemacht hat
(bezogen auf den letzten gemeinsamen Vorgänger):"


<pre><code>my-apollo $ <b>git diff --stat HEAD...origin/master</b><br><br> backend.java | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br><br></code></pre>


## Lösung zu Schritt 4 - Fremde Änderungen integrieren

Integriere die Änderungen mit Pull und sieh Dir dann den Commit-Graphen an.


<pre><code>my-apollo $ <b>git pull</b><br><br>Merge made by the 'recursive' strategy.<br> backend.java | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br><br></code></pre>


Da *Anja* eine andere Datei (`backend.java`) bearbeitet hat als Du (`frontend.java`),
konnten ihre Änderungen problemlos integriert werden.
Man sieht, dass ein neues Commit entstanden ist,
welches die Stränge zusammenführt.


<pre><code>my-apollo $ <b>git log --graph --oneline</b><br><br>*   74b79b2 Merge branch 'master' of ../blessed-apollo<br>|\  <br>| * a18d521 : Edit file backend.java at line 5 on branch master by anja.<br>| * 6b8593c : Edit file backend.java at line 1 on branch master by anja.<br>* | 13b73c2 : Edit file frontend.java at line 1 on branch master by bstachmann.<br>|/  <br>* a4942ef Created file frontend.java on branch master by anja.<br>* 87c88b3 Created file backend.java on branch master by anja.<br><br></code></pre>


#### Achtung: Beim `pull` kann es Merge-Konflikte geben ...

... wenn beide Seiten dieselben Stellen bearbeitet haben.
Das Auflösen von Merge-Konflikten ist Thema eines folgenden Kapitels.

## Lösung zu Schritt 5 - Erneut pushen

                    


<pre><code>my-apollo $ <b>git push</b><br><br>To ../blessed-apollo.git<br>   a18d521..74b79b2  master -&gt; master<br><br></code></pre>


Und siehe da: Jetzt klappt's.

#### Achtung: Falls schon wieder jemand schneller war ...

... und nach blessed-apollo.git gepushed hat,
kann es nochmal ein *Push Reject* geben,
und wir versuchen erneut ein `pull`, dann ein `push`,
solange, bis es klapp.

[Zur Aufgabe](aufgabe-zusammenarbeit-push-rejected.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

