---
layout: page
title: <code>intro-hallo-welt</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Hilfe

Lassen Sie sich die Hilfeseite zum `log`-Befehl ausgeben.


<pre><code>$ <b>git help log</b><br><br>GIT-LOG(1)                                                        Git Manual                                                        GIT-LOG(1)<br><br>NAME<br>       git-log - Show commit logs<br>...<br><br></code></pre>


## Lösung zu Schritt 2 - Setup

Konfigurieren Sie Benutzername und Email:

    $ git config --global user.name mein-name
    $ git config --global user.email meine-email


<pre><code>$ <b>git config --global user.name mein-name</b><br><br><br></code></pre>



<pre><code>$ <b>git config --global user.email meine-email</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Das erste Repos

Erstellen sie ihr erstes Repository mit den folgenden Befehlen:

    $ git init myrepo
    $ cd myrepo
    $ echo 'welt' >hallo    # erzeugt eine Datei
    $ git add hallo
    $ git commit -m 'Hallo Welt!'
    $ git log


<pre><code>$ <b>git init myrepo </b><br><br>Initialized empty Git repository in /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/intro-hallo-welt/myrepo/.git/<br><br></code></pre>



<pre><code>$ <b>cd myrepo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>echo 'welt' &gt;hallo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git add hallo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git commit -m 'Hallo Welt!'</b><br><br>[master (root-commit) fcc7316] Hallo Welt!<br> 1 file changed, 1 insertion(+)<br> create mode 100644 hallo<br><br></code></pre>


Glückwunsch: Sieh sehen Ihr erstes Commit in Ihrem ersten Git-Repository!


<pre><code>myrepo $ <b>git log</b><br><br>commit fcc7316cba2e95b02deb46555482d85ad9f73cc9<br>Author: bstachmann &lt;egal&gt;<br>Date:   Sun Jul 4 14:40:47 2021 +0200<br><br>    Hallo Welt!<br><br></code></pre>



<pre><code>myrepo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-intro-hallo-welt.md){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

