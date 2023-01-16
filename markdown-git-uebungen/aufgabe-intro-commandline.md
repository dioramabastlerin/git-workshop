---
layout: page
title: <code>intro-commandline</code>
parent: Aufgaben

---
# Übung - Das `git`-Kommando!

In der ersten Übung geht es darum,
die (Git-) Bash-Kommandozeile und vor allem
das `git`-Kommando kennenzulernen.

## Tipps

* Für Windows-Nutzer:
  - Nutzen sie die **Git-Bash**-Kommandozeile dann können sie Beispiele
    genau wie hier angegeben ausführen.
  - Verwenden sie '/' statt '\', für Verzeichnispfade.
  - Wenn Sie mit der Windows-Kommandozeile `CMD` arbeiten,
    müssen sie ggf. kleinere Änderungen vornehmen,
    damit die Beispiele funktionieren.

* Bash-Kommandos
  - `cd <verzeichnisname>`, wechselt in ein anderes Verzeichnis.
  - `cd ..`, wechselt in das übergeordente Verzeichnis.
    Eine Ebene hoch!
  - `ls` zeigt die Namen der Dateien und Unterverzeichnisse im aktuellen Verzeichnisse.
  - `ll` Wie `ls` nur mit mehr Details.
  - `less`. Inhalt einer Datei anzeigen. Scrollen mit Pfeiltasten. Mit Taste `q` beenden.

* Git-Kommandos
  - `git version` zeigt welche Version von Git installiert ist.
  - `git help <kommando>` zeigt Hilfe.
  - `git config <property>` zeigt Wert aus der Konfiguration an. 
  - `git config set --global <property> <new-value>` 
    setzt einen Wert in der Konfiguration.

[Kurze Intro zur Kommandozeile](../installation/kommandozeile)


<pre><code>$ <b>cd ../..</b><br><br><br></code></pre>


<!--UEB-Das `git`-Kommando!--><h2>Schritt 1 - Navigation in Übungsverzeichnisse</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/<unknown>`.

Starte in jenem Verzeichnis, wo `build.zip` entpackt wurde.
Navigiere in das Unterverzeichnis `aufgaben/intro-commandline/hallo`
und sieh Dir den Inhalt der dort liegenden Datei an.
Navigiere dann wieder zurück ins Ursprungsverzeichnis.


<pre><code>.. $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd hallo</b><br><br><br></code></pre>


<!--UEB-Das `git`-Kommando!--><h2>Schritt 2 - Git-Version prüfen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/intro-commandline/hallo`.

Gib aus, welche Version von Git installiert ist.

<!--UEB-Das `git`-Kommando!--><h2>Schritt 3 - Hilfe</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/intro-commandline/hallo`.

Zeige die Hilfeseite zum `log`-Befehl an.

<!--UEB-Das `git`-Kommando!--><h2>Schritt 4 - Konfiguration von Git</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/intro-commandline/hallo`.

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


<!--UEB-Das `git`-Kommando!--><h2>Schritt 5 - ⭐ Historie</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/intro-commandline/hallo`.

Blättern sie die 🡅-Taste mehrfach und drücken dann enter,
um einen der vorigen Befehle erneut auszuführen.
Tippen sie `strg+r` und geben sie dann `conflict`ein,
um den Befehl zum Setzen von `merge.conflictStyle` erneut auszuführen.


<pre><code>hallo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-intro-commandline.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

