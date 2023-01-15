---
layout: page
title: <code>zusammenarbeit-push-rejected</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Lokal Commit(s) erstellen

Bearbeite die Datei `frontend.java` und erstelle (mindestens) ein Commit mit den Änderungen.
Überprüfe danach mit `git status`, ob der Workspace sauber ist.


<pre><code>my-apollo $ <b># Edit file frontend.java at line 1 on branch main by bjoern.</b><br><br><br></code></pre>



<pre><code>my-apollo $ <b>git commit -am &quot;`frontend.java`: Edit file frontend.java at line 1 on branch main by bjoern. &quot;</b><br><br>[main b09dd4a] : Edit file frontend.java at line 1 on branch main by bjoern.<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br>/bin/bash: frontend.java: command not found<br><br></code></pre>


Und jetzt noch eben prüfen, ob `working tree clean` ist.


<pre><code>my-apollo $ <b>git status</b><br><br>On branch main<br>Your branch is ahead of 'origin/main' by 1 commit.<br>  (use &quot;git push&quot; to publish your local commits)<br><br>nothing to commit, working tree clean<br><br></code></pre>


## Lösung zu Schritt 2 - Push versuchen

Versuche jetzt Deine Änderungen zu pushen.


<pre><code>my-apollo $ <b>git push</b><br><br>To ../blessed-apollo.git<br> ! [rejected]        main -&gt; main (fetch first)<br>error: failed to push some refs to '../blessed-apollo.git'<br>hint: Updates were rejected because the remote contains work that you do<br>hint: not have locally. This is usually caused by another repository pushing<br>hint: to the same ref. You may want to first integrate the remote changes<br>hint: (e.g., 'git pull ...') before pushing again.<br>hint: See the 'Note about fast-forwards' in 'git push --help' for details.<br><br></code></pre>


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


<pre><code>my-apollo $ <b>git fetch</b><br><br>From ../blessed-apollo<br>   e9477ea..3076d39  main       -&gt; origin/main<br><br></code></pre>


Die Ausgabe zeigt, dass neue Commit für den `origin/main` geholt wurden

Die `..`-Notation zeigt, welche Commits hinzugekommen sind:


<pre><code>my-apollo $ <b>git log --oneline main..origin/main</b><br><br>3076d39 : Edit file backend.java at line 5 on branch main by anja .<br>eccdd50 : Edit file backend.java at line 1 on branch main by anja .<br><br></code></pre>


Das normale (symmetrische) Diff zeig alle Unterschiede. 
Sowohl das, was du gemacht hast, als auch das, was Anja gemacht hat:"


<pre><code>my-apollo $ <b>git diff --stat HEAD origin/main</b><br><br> backend.java  | 6 ++++--<br> frontend.java | 2 +-<br> 2 files changed, 5 insertions(+), 3 deletions(-)<br><br></code></pre>


Das asymmetrische Diff `...` zeigt nur jene Änderungen,
die Anja gemacht hat
(bezogen auf den letzten gemeinsamen Vorgänger):"


<pre><code>my-apollo $ <b>git diff --stat HEAD...origin/main</b><br><br> backend.java | 6 ++++--<br> 1 file changed, 4 insertions(+), 2 deletions(-)<br><br></code></pre>


## Lösung zu Schritt 4 - Fremde Änderungen integrieren

Integriere die Änderungen mit Pull und sieh Dir dann den Commit-Graphen an.


<pre><code>my-apollo $ <b>git pull</b><br><br>Merge made by the 'ort' strategy.<br> backend.java | 6 ++++--<br> 1 file changed, 4 insertions(+), 2 deletions(-)<br><br></code></pre>


Da *Anja* eine andere Datei (`backend.java`) bearbeitet hat als Du (`frontend.java`),
konnten ihre Änderungen problemlos integriert werden.
Man sieht, dass ein neues Commit entstanden ist,
welches die Stränge zusammenführt.


<pre><code>my-apollo $ <b>git log --graph --oneline</b><br><br>*   afa9054 Merge branch 'main' of ../blessed-apollo<br>|\  <br>| * 3076d39 : Edit file backend.java at line 5 on branch main by anja .<br>| * eccdd50 : Edit file backend.java at line 1 on branch main by anja .<br>* | b09dd4a : Edit file frontend.java at line 1 on branch main by bjoern.<br>|/  <br>* e9477ea Created file frontend.java on branch main by anja .<br>* f2169d3 Created file backend.java on branch main by anja .<br><br></code></pre>


#### Achtung: Beim `pull` kann es Merge-Konflikte geben ...

... wenn beide Seiten dieselben Stellen bearbeitet haben.
Das Auflösen von Merge-Konflikten ist Thema eines folgenden Kapitels.

## Lösung zu Schritt 5 - Erneut pushen

                    


<pre><code>my-apollo $ <b>git push</b><br><br>To ../blessed-apollo.git<br>   3076d39..afa9054  main -&gt; main<br><br></code></pre>


Und siehe da: Jetzt klappt's.

#### Achtung: Falls schon wieder jemand schneller war ...

... und nach blessed-apollo.git gepushed hat,
kann es nochmal ein *Push Reject* geben,
und wir versuchen erneut ein `pull`, dann ein `push`,
solange, bis es klapp.

[Zur Aufgabe](aufgabe-zusammenarbeit-push-rejected.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

