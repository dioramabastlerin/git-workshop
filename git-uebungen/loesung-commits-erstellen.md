---
layout: page
title: <code>commits-erstellen</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Commit - mit Staging

Bearbeite die Datei `hallo-welt`,
füge sie mit `git add` zum Index hinzu (Staging)
und erstelle ein Commit mit diesen Änderungen.


<pre><code>repo $ <b># Edit file hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'Erste Änderung'</b><br><br>[master 4e171ce] Erste Änderung<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git show</b><br><br>commit 4e171ce6546446388f81eb7c8917bd17387c189a<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Erste Änderung<br><br>diff --git a/hallo-welt b/hallo-welt<br>index a92550c..dfb4ae8 100644<br>--- a/hallo-welt<br>+++ b/hallo-welt<br>@@ -1 +1 @@<br>-Hallo Welt<br>\ No newline at end of file<br>+Hallo Welt!<br>\ No newline at end of file<br><br></code></pre>


## Lösung zu Schritt 2 - Commit - automatisches Staging

Bearbeite die Datei `hallo-welt` erneut
und erstelle wieder ein Commit,
dieses mal mal aber mit `-a`.


<pre><code>repo $ <b># Edit file hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Zweite Änderung'</b><br><br>[master c7795dd] Zweite Änderung<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>


Mit der Option `-a` kann man sich den `add`-Aufruf sparen:


<pre><code>repo $ <b>git log --oneline</b><br><br>c7795dd Zweite Änderung<br>4e171ce Erste Änderung<br>1dffcbb Created file datei1 on branch master by bstachmann.<br>3f5e05f Created file hello-world on branch master by bstachmann.<br>5813cae Created file hallo-welt on branch master by bstachmann.<br><br></code></pre>


## Lösung zu Schritt 3 - Commit - neue Datei

Erstelle `new-world` und bestätige sie mit einem Commit.


<pre><code>repo $ <b># created file 'new-world'</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file new-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add .</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'Neue Datei'</b><br><br>[master b9854e4] Neue Datei<br> 1 file changed, 1 insertion(+)<br> create mode 100644 new-world<br><br></code></pre>


## Lösung zu Schritt 4 - ⭐ Commit - Datei löschen

Lösche `hallo-welt` und bestätige dies per Commit.


<pre><code>repo $ <b>rm hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Datei löschen'</b><br><br>[master 107374c] Datei löschen<br> 1 file changed, 1 deletion(-)<br> delete mode 100644 hallo-welt<br><br></code></pre>


## Lösung zu Schritt 5 - Commit - Datei verschieben/umbenennen

Benenne die Datei `hello-world` in `renamed-world` um.


<pre><code>repo $ <b>mv hello-world renamed-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add .</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'Umbenennen'</b><br><br>[master e979464] Umbenennen<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br> rename hello-world =&gt; renamed-world (100%)<br><br></code></pre>


Anmerkung: Wenn wir `git mv`  statt `mv` genutzt hätten, dann wäre das separate `git add` nicht nötig gewesen.


<pre><code>repo $ <b>git log --follow --oneline -- renamed-world</b><br><br>e979464 Umbenennen<br>3f5e05f Created file hello-world on branch master by bstachmann.<br><br></code></pre>


## Lösung zu Schritt 6 - ⭐ Rename detection

Benenne die Datei `datei1` in `datei2` mit `git mv` um. 
Sorge dafür, dass die *Rename Detection* dies nicht erkennt.


<pre><code>repo $ <b>git mv datei1 datei2</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file datei2</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Böse umbenennen'</b><br><br>[master 73cb906] Böse umbenennen<br> 2 files changed, 1 insertion(+), 12 deletions(-)<br> delete mode 100644 datei1<br> create mode 100644 datei2<br><br></code></pre>



<pre><code>repo $ <b>git log --follow --oneline -- datei2</b><br><br>73cb906 Böse umbenennen<br><br></code></pre>


[Zur Aufgabe](aufgabe-commits-erstellen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

