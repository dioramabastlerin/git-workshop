---
layout: page
title: <code>repository-sparse-checkout</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Sparse-Klon durchführen

Erstelle einen Sparse-Klon von `repo` mit dem Namen `myrepo`,
überprüfe, dass nur Top-Level-Dateien in den Workspace geholt wurden.


<pre><code>$ <b>git clone --sparse repo myrepo</b><br><br>Cloning into 'myrepo'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd myrepo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>ls -lR</b><br><br>.:<br>total 4<br>-rw-rw-r-- 1 bjoern bjoern 181 Jul  4 15:53 README.md<br><br></code></pre>



<pre><code>myrepo $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 2 - Verzeichnis hinzufügen

Füge das Verzeichnis `component-a` hinzu .
Überprüfe die neue Konfiguration.
Validiere, dass `component-a` jetzt da ist.


<pre><code>$ <b>cd myrepo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git sparse-checkout add component-a</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git sparse-checkout list</b><br><br>/*<br>!/*/<br>component-a<br><br></code></pre>



<pre><code>myrepo $ <b>git checkout</b><br><br>Your branch is up to date with 'origin/master'.<br><br></code></pre>



<pre><code>myrepo $ <b>ls -lR</b><br><br>.:<br>total 8<br>drwxrwxr-x 2 bjoern bjoern 4096 Jul  4 15:53 component-a<br>-rw-rw-r-- 1 bjoern bjoern  181 Jul  4 15:53 README.md<br><br>./component-a:<br>total 4<br>-rw-rw-r-- 1 bjoern bjoern 181 Jul  4 15:53 foo<br><br></code></pre>



<pre><code>myrepo $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Sparse Checkout deaktivieren

| Deaktiviere Sparse-Checkout und führe erneut ein Checkout durch.
| Validiere, dass jetzt alle Dateien da sind.


<pre><code>$ <b>cd myrepo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git sparse-checkout disable</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git checkout</b><br><br>Your branch is up to date with 'origin/master'.<br><br></code></pre>



<pre><code>myrepo $ <b>ls -lR</b><br><br>.:<br>total 12<br>drwxrwxr-x 2 bjoern bjoern 4096 Jul  4 15:53 component-a<br>drwxrwxr-x 2 bjoern bjoern 4096 Jul  4 15:53 component-b<br>-rw-rw-r-- 1 bjoern bjoern  181 Jul  4 15:53 README.md<br><br>./component-a:<br>total 4<br>-rw-rw-r-- 1 bjoern bjoern 181 Jul  4 15:53 foo<br><br>./component-b:<br>total 4<br>-rw-rw-r-- 1 bjoern bjoern 181 Jul  4 15:53 bar<br><br></code></pre>



<pre><code>myrepo $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Klonen und auschecken

Klone `myrepo`, schränke auf `component-a` ein
und mache ein Checkout. Nutze die `--cone`-Option.


<pre><code>$ <b>git clone --sparse repo myclone</b><br><br>Cloning into 'myclone'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd myclone</b><br><br><br></code></pre>



<pre><code>myclone $ <b>git sparse-checkout init --cone</b><br><br><br></code></pre>



<pre><code>myclone $ <b>git sparse-checkout add component-a</b><br><br><br></code></pre>



<pre><code>myclone $ <b>git checkout</b><br><br>Your branch is up to date with 'origin/master'.<br><br></code></pre>



<pre><code>myclone $ <b>ls -lR</b><br><br>.:<br>total 8<br>drwxrwxr-x 2 bjoern bjoern 4096 Jul  4 15:53 component-a<br>-rw-rw-r-- 1 bjoern bjoern  181 Jul  4 15:53 README.md<br><br>./component-a:<br>total 4<br>-rw-rw-r-- 1 bjoern bjoern 181 Jul  4 15:53 foo<br><br></code></pre>



<pre><code>myclone $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-repository-sparse-checkout.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

