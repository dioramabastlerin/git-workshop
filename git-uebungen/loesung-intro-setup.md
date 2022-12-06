---
layout: page
title: <code>intro-setup</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Navigation in Übungsverzeichnisse

Starte in jenem Verzeichnis, wo `build.zip` entpackt wurde.
Navigiere in das Unterverzeichnis `aufgaben/intro-setup/hallo`
und sieh Dir den Inhalt der dort liegenden Datei an.
Navigiere dann wieder zurück ins Ursprungsverzeichnis.


<pre><code>.. $ <b>ls</b><br><br>aufgaben<br>aufgaben.json<br>loesungen<br>ueberblick.html<br>ueberblick.md<br><br></code></pre>



<pre><code>.. $ <b>cd aufgaben</b><br><br><br></code></pre>



<pre><code>aufgaben $ <b>cd intro-setup</b><br><br><br></code></pre>



<pre><code>intro-setup $ <b>cd hallo</b><br><br><br></code></pre>



<pre><code>hallo $ <b>ls</b><br><br>herzlich-willkommen.txt<br><br></code></pre>



<pre><code>hallo $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>intro-setup $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>aufgaben $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 2 - Git-Version prüfen

Gib aus, welche Version von Git installiert ist.


<pre><code>$ <b>git version</b><br><br>git version 2.37.3<br><br></code></pre>


## Lösung zu Schritt 3 - Hilfe

Zeige die Hilfeseite zum `log`-Befehl an.


<pre><code>$ <b>git help log</b><br><br>GIT-LOG(1)                                                        Git Manual                                                        GIT-LOG(1)<br><br>NAME<br>       git-log - Show commit logs<br>...<br><br></code></pre>


## Lösung zu Schritt 4 - Setup

Prüfe die User-Konfiguration:

    $ git config user.name
    $ git config user.email
    $ git config pull.rebase
    $ git config merge.conflictStyle

Konfigurieren Sie Benutzername und Email:

    $ git config --global user.name mein-name
    $ git config --global user.email meine-email
    $ git config --global pull.rebase false 
    $ git config --global merge.conflictStyle diff3


<pre><code>$ <b>git config --global user.name mein-name</b><br><br><br></code></pre>



<pre><code>$ <b>git config --global user.email meine-email</b><br><br><br></code></pre>



<pre><code>$ <b>git config --global pull.rebase false </b><br><br><br></code></pre>



<pre><code>$ <b>git config --global merge.conflictStyle diff3</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-intro-setup.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

