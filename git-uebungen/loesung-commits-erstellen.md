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



<pre><code>repo $ <b>git commit -m 'Erste Änderung'</b><br><br>[master 4d47658] Erste Änderung<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git show</b><br><br>commit 4d476588b1609dd9b8be81c48d7c8a88812f34e4<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Erste Änderung<br><br>diff --git a/hallo-welt b/hallo-welt<br>index a92550c..dfb4ae8 100644<br>--- a/hallo-welt<br>+++ b/hallo-welt<br>@@ -1 +1 @@<br>-Hallo Welt<br>\ No newline at end of file<br>+Hallo Welt!<br>\ No newline at end of file<br><br></code></pre>


## Lösung zu Schritt 2 - Commit - automatisches Staging

Bearbeite die Datei `hallo-welt` erneut
und erstelle wieder ein Commit,
dieses mal mal aber mit `-a`.


<pre><code>repo $ <b># Edit file hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Zweite Änderung'</b><br><br>[master eafb6ac] Zweite Änderung<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>


Mit der Option `-a` kann man sich den `add`-Aufruf sparen:


<pre><code>repo $ <b>git log --oneline</b><br><br>eafb6ac Zweite Änderung<br>4d47658 Erste Änderung<br>27d83cb Created file hello-world on branch master by dioramabastlerin.<br>3905551 Created file hallo-welt on branch master by dioramabastlerin.<br><br></code></pre>


## Lösung zu Schritt 3 - Commit - neue Datei

Erstelle `new-world` und bestätige sie mit einem Commit.


<pre><code>repo $ <b># created file 'new-world'</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file new-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add .</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'Neue Datei'</b><br><br>[master ce3bb00] Neue Datei<br> 1 file changed, 1 insertion(+)<br> create mode 100644 new-world<br><br></code></pre>


## Lösung zu Schritt 4 - Commit - Datei löschen

Lösche `hallo-welt` und bestätige dies per Commit.


<pre><code>repo $ <b>rm hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Neue Datei'</b><br><br>[master fde2aa6] Neue Datei<br> 1 file changed, 1 deletion(-)<br> delete mode 100644 hallo-welt<br><br></code></pre>


## Lösung zu Schritt 5 - Commit - Datei verschieben/umbenennen

Benenne die Datei `hello-world` in `renamed-world` um.


<pre><code>repo $ <b>mv hello-world renamed-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add .</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'Neue Datei'</b><br><br>[master bc54655] Neue Datei<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br> rename hello-world =&gt; renamed-world (100%)<br><br></code></pre>


Anmerkung: Wenn wir `git mv`  statt `mv` genutzt hätten, dann wäre das separate `git add` nicht nötig gewesen.

[Zur Aufgabe](aufgabe-commits-erstellen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

