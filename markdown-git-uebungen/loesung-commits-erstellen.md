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



<pre><code>repo $ <b>git commit -m 'Erste Änderung'</b><br><br>[main 8e6f221] Erste Änderung<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>



<pre><code>repo $ <b>git show</b><br><br>commit 8e6f221a4064202a3e00c80e811334dc72d1bd39<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Erste Änderung<br><br>diff --git a/hallo-welt b/hallo-welt<br>index a92550c..dfb4ae8 100644<br>--- a/hallo-welt<br>+++ b/hallo-welt<br>@@ -1 +1 @@<br>-Hallo Welt<br>\ No newline at end of file<br>+Hallo Welt!<br>\ No newline at end of file<br><br></code></pre>


## Lösung zu Schritt 2 - Commit - automatisches Staging

Bearbeite die Datei `hallo-welt` erneut
und erstelle wieder ein Commit,
dieses mal mal aber mit `-a`.


<pre><code>repo $ <b># Edit file hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Zweite Änderung'</b><br><br>[main 6aa80c5] Zweite Änderung<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>


Mit der Option `-a` kann man sich den `add`-Aufruf sparen:


<pre><code>repo $ <b>git log --oneline</b><br><br>6aa80c5 Zweite Änderung<br>8e6f221 Erste Änderung<br>8d91794 Created file datei1 on branch main by bjoern.<br>331ed0e Created file hello-world on branch main by bjoern.<br>f55db71 Created file hallo-welt on branch main by bjoern.<br><br></code></pre>


## Lösung zu Schritt 3 - Commit - neue Datei

Erstelle `new-world` und bestätige sie mit einem Commit.


<pre><code>repo $ <b># created file 'new-world'</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file new-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add new-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'Neue Datei'</b><br><br>[main 35612a2] Neue Datei<br> 1 file changed, 1 insertion(+)<br> create mode 100644 new-world<br><br></code></pre>


## Lösung zu Schritt 4 - Commit - Datei löschen

Lösche `hallo-welt` und bestätige dies per Commit.


<pre><code>repo $ <b>rm hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Datei löschen'</b><br><br>[main 7f7a567] Datei löschen<br> 1 file changed, 1 deletion(-)<br> delete mode 100644 hallo-welt<br><br></code></pre>


## Lösung zu Schritt 5 - ⭐ Add - Dateien rekursiv hinzufügen

Lege eine Datei `superneu` und eine Verzeichnis `sub`mit einer
Datei `auchneu` an füge beide mit *einem* Add-Aufruf hinzu und erstelle
dann ein Commit.


<pre><code>repo $ <b># created file 'superneu'</b><br><br><br></code></pre>



<pre><code>repo $ <b>mkdir sub</b><br><br><br></code></pre>



<pre><code>sub $ <b>cd sub</b><br><br><br></code></pre>



<pre><code>sub $ <b># created file 'auchneu'</b><br><br><br></code></pre>



<pre><code>sub $ <b>cd ..</b><br><br><br></code></pre>


 `.` steht für: *aktuelles Verzeichnis*."
Alle Dateien darin und auch darunter werden hinzugefügt.


<pre><code>repo $ <b>git add .</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Neue Dateien'</b><br><br>[main 9ea75ca] Neue Dateien<br> 2 files changed, 24 insertions(+)<br> create mode 100644 sub/auchneu<br> create mode 100644 superneu<br><br></code></pre>


## Lösung zu Schritt 6 - ⭐ Commit - Datei verschieben/umbenennen

Benenne die Datei `hello-world` in `renamed-world` um
und bestätige dies durch ein Commit.


<pre><code>repo $ <b>mv hello-world renamed-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add renamed-world</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Umbenennen'</b><br><br>[main a8c7bd8] Umbenennen<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br> rename hello-world =&gt; renamed-world (100%)<br><br></code></pre>


Anmerkung: Wenn wir `git mv`  statt `mv` genutzt hätten, dann wäre das separate `git add` nicht nötig gewesen.


<pre><code>repo $ <b>git log --follow --oneline -- renamed-world</b><br><br>a8c7bd8 Umbenennen<br>331ed0e Created file hello-world on branch main by bjoern.<br><br></code></pre>


## Lösung zu Schritt 7 - ⭐ Rename detection

Benenne die Datei `datei1` in `datei2` mit `git mv` um. 
Sorge dafür, dass die *Rename Detection* dies nicht erkennt.


<pre><code>repo $ <b>git mv datei1 datei2</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file datei2</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Böse umbenennen'</b><br><br>[main 2a7ad75] Böse umbenennen<br> 2 files changed, 1 insertion(+), 12 deletions(-)<br> delete mode 100644 datei1<br> create mode 100644 datei2<br><br></code></pre>



<pre><code>repo $ <b>git log --follow --oneline -- datei2</b><br><br>2a7ad75 Böse umbenennen<br><br></code></pre>


[Zur Aufgabe](aufgabe-commits-erstellen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

