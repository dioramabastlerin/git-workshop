---
layout: page
title: <code>intro-commandline</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Navigation in Übungsverzeichnisse

Starte in jenem Verzeichnis, wo `build.zip` entpackt wurde.
Navigiere in das Unterverzeichnis `aufgaben/intro-commandline/hallo`
und sieh Dir den Inhalt der dort liegenden Datei an.
Navigiere dann wieder zurück ins Ursprungsverzeichnis.


<pre><code>.. $ <b>ls</b><br><br>aufgaben<br>loesungen<br><br></code></pre>



<pre><code>.. $ <b>cd aufgaben</b><br><br><br></code></pre>



<pre><code>aufgaben $ <b>cd intro-commandline</b><br><br><br></code></pre>



<pre><code>intro-commandline $ <b>cd hallo</b><br><br><br></code></pre>



<pre><code>hallo $ <b>ls</b><br><br>herzlich-willkommen.txt<br><br></code></pre>



<pre><code>hallo $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>intro-commandline $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>aufgaben $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 2 - Git-Version prüfen

Gib aus, welche Version von Git installiert ist.


<pre><code>hallo $ <b>git version</b><br><br>git version 2.37.3<br><br></code></pre>


## Lösung zu Schritt 3 - Hilfe

Zeige die Hilfeseite zum `log`-Befehl an.


<pre><code>hallo $ <b>git help log</b><br><br>GIT-LOG(1)                                                        Git Manual                                                        GIT-LOG(1)<br><br>NAME<br>    git-log - Show commit logs<br>...<br><br></code></pre>


## Lösung zu Schritt 4 - `less` und lange Ausgaben

Wenn Sie `git log` ausführen, sollen 99 Commits angezeigt werden.
Weil diese nicht in ein Terminalfenster passt,
wird der `less`-Viewer geöffnet. Schliessen sie ihn.
Nutzen sie dann `less some-file.txt` um eine Datei im `less`-Modus anzusehen.


<pre><code>repo $ <b>git log</b><br><br>commit 5e14e1dc688e7a2cd02c9ccad3dedf397d407e2e (HEAD -&gt; main)<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file some-file.txt at line 3 on branch main by bjoern.<br><br>commit 41984e9ac879b9b56c8e91228a8d5887bca228fd<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file some-file.txt at line 3 on branch main by bjoern.<br><br>commit 99399d263ccc8fe4a1bc59a49c93147b17115518<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file some-file.txt at line 3 on branch main by bjoern.<br><br>commit df80cb240781a015f2f0ad62a48fc42964fdfe8b<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file some-file.txt at line 3 on branch main by bjoern.<br>:<br><br></code></pre>


Quit with `q`


<pre><code>repo $ <b>less some-file.txt</b><br><br>Editet in Commit 99<br>line 1 created<br>line 2 created<br>line 3 created<br>line 4 created<br>line 5 created<br>line 6 created<br>line 7 created<br>line 8 created<br>line 9 created<br>line 10 created<br>line 11 created<br><br></code></pre>


Quit with `q`

## Lösung zu Schritt 5 - Arbeitsverzeichnis anzeigen

Prüfe die User-Konfiguration:

    $ git config user.name
    $ git config user.email
    $ git config pull.rebase
    $ git config merge.conflictStyle
    $ git config --global init.defaultBranch 

Konfiguriere Sie Benutzername und -Email, 
sofern noch nicht gesetzt:

    $ git config --global user.name mein-name
    $ git config --global user.email meine-email

Die folgenden Konfigurationen wurden beim Aufzeichnen der 
Musterlösung genutzt.
Es ist empfehlenswert sie für diesen Workshop setzen:

    $ git config --global pull.rebase false 
    $ git config --global merge.conflictStyle diff3
    $ git config --global init.defaultBranch main



<pre><code>repo $ <b>git config --global user.name mein-name</b><br><br><br></code></pre>



<pre><code>repo $ <b>git config --global user.email meine-email</b><br><br><br></code></pre>



<pre><code>repo $ <b>git config --global pull.rebase false </b><br><br><br></code></pre>



<pre><code>repo $ <b>git config --global merge.conflictStyle diff3</b><br><br><br></code></pre>



<pre><code>repo $ <b>git config --global init.defaultBranch main</b><br><br><br></code></pre>


## Lösung zu Schritt 6 - ⭐ Historie

Blättern sie die 🡅-Taste mehrfach und drücken dann enter,
um einen der vorigen Befehle erneut auszuführen.
Tippen sie `strg+r` und geben sie dann `conflict`ein,
um den Befehl zum Setzen von `merge.conflictStyle` erneut auszuführen.


<pre><code>repo $ <b>git config --global user.email meine-email</b><br><br><br></code></pre>



<pre><code>repo $ <b>git config --global merge.conflictStyle diff3</b><br><br><br></code></pre>


## Lösung zu Schritt 7 - ⭐ Git-Editor konfigurieren

Konfigurieren Sie einen Editor für git.
[Tipps dazu](https://git-scm.com/book/en/v2/Appendix-C%3A-Git-Commands-Setup-and-Config).f
Testen Sie dann mit `git config -e`, ob es geklappt hat.


<pre><code>repo $ <b>git config --global core.editor notepad</b><br><br><br></code></pre>



<pre><code>repo $ <b>git config -e</b><br><br><br></code></pre>


## Lösung zu Schritt 8 - ⭐ Arbeitsverzeichnis

Geben Sie aus, in welchem Arbeitzverzeichnis Sie sich gerade befinden.
Für Windows-User: Testen sie den Befehl auch mit der Option `-W`.


<pre><code>repo $ <b>pwd</b><br><br>/workspace/git-workshop/build/git-uebungen/loesungen/intro-commandline/repo<br><br></code></pre>


## Lösung zu Schritt 9 - ⭐ Anwendungen öfnnen

Öffnen Sie die Datei `some-file.txt` mit der Standaranwendung.
Öffnen Sie einen Datei-Explorer im aktuellen Arbeitsverzeichnis.


<pre><code>repo $ <b>start some-file.txt</b><br><br><br></code></pre>



<pre><code>repo $ <b>start .</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-intro-commandline.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

