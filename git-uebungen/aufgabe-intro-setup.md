---
layout: page
title: <code>intro-setup</code>
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
    müssen sie ggf. kleiner Änderungen vornehmen,
    damit die Beispiele funktionieren.

* TODO
  - cd, ls, ll
  - less/notepad/editor
  - git verseion



<pre><code>$ <b>mkdir hallo</b><br><br><br></code></pre>



<pre><code>hallo $ <b>cd hallo</b><br><br><br></code></pre>



<pre><code>hallo $ <b># created file 'README.md'</b><br><br><br></code></pre>



<pre><code>hallo $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd ../..</b><br><br><br></code></pre>


<!--UEB-Das `git`-Kommando!--><h2>Schritt 1 - Navigation in Übungsverzeichnisse</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/<unknown>`.

Starte in jenem Verzeichnis, wo `build.zip` entpackt wurde.
Navigiere in das Unterverzeichnis `aufgaben/intro-setup/hallo`
und sieh Dir den Inhalt der dort liegenden Datei an.
Navigiere dann wieder zurück ins Ursprungsverzeichnis.


<pre><code>.. $ <b>cd ..</b><br><br><br></code></pre>


<!--UEB-Das `git`-Kommando!--><h2>Schritt 2 - Git-Version prüfen</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/intro-setup`.

Gib aus, welche Version von Git installiert ist.

<!--UEB-Das `git`-Kommando!--><h2>Schritt 3 - Hilfe</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/intro-setup`.

Lassen Sie sich die Hilfeseite zum `log`-Befehl ausgeben.

<!--UEB-Das `git`-Kommando!--><h2>Schritt 4 - Setup</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/intro-setup`.

Konfigurieren Sie Benutzername und Email:

    $ git config --global user.name mein-name
    $ git config --global user.email meine-email

[Zur Lösung](loesung-intro-setup.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

