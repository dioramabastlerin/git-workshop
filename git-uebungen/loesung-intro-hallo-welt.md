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


## Lösung zu Schritt 3 - Das erste Repo

Erstellen sie ihr erstes Repository mit den folgenden Befehlen:

    $ git init myrepo
    $ cd myrepo
    $ echo 'welt' >hallo    # erzeugt eine Datei
    $ git add hallo
    $ git commit -m 'Hallo Welt!'
    $ git log


<pre><code>$ <b>git init myrepo </b><br><br>Initialized empty Git repository in /workspace/git-workshop/build/git-uebungen/loesungen/intro-hallo-welt/myrepo/.git/<br>hint: Using 'master' as the name for the initial branch. This default branch name<br>hint: is subject to change. To configure the initial branch name to use in all<br>hint: of your new repositories, which will suppress this warning, call:<br>hint: <br>hint: 	git config --global init.defaultBranch &lt;name&gt;<br>hint: <br>hint: Names commonly chosen instead of 'master' are 'main', 'trunk' and<br>hint: 'development'. The just-created branch can be renamed via this command:<br>hint: <br>hint: 	git branch -m &lt;name&gt;<br><br></code></pre>



<pre><code>$ <b>cd myrepo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>echo 'welt' &gt;hallo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git add hallo</b><br><br><br></code></pre>



<pre><code>myrepo $ <b>git commit -m 'Hallo Welt!'</b><br><br>[master (root-commit) 48dd4eb] Hallo Welt!<br> 1 file changed, 1 insertion(+)<br> create mode 100644 hallo<br><br></code></pre>


Glückwunsch: Sieh sehen Ihr erstes Commit in Ihrem ersten Git-Repository!


<pre><code>myrepo $ <b>git log</b><br><br>commit 48dd4eb91e6605e068edb66c2653303502812e3b<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Hallo Welt!<br><br></code></pre>



<pre><code>myrepo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-intro-hallo-welt.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

