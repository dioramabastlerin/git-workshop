---
layout: page
title: <code>intro-hallo-welt</code>
parent: Aufgaben

---
# Übung - Hallo Git!

Hier geht es darum, ein Gefühl dafür zu bekommen,
wie die Git-Kommandozeile funktioniert.
Führen sie die vorgegebenen Kommandos aus und schauen Sie,
was passiert.
Was die Kommandos genau tun, erfahren Sie im Verlauf des Seminars.

## Tipps

* Für Windows Nutzer:
  - Nutzen sie die **Git-Bash**-Kommandozeile dann können sie Beispiele
    genau wie hier angegeben ausführen.
  - Verwenden sie '/' statt '\', für Verzeichnispfade.
  - Wenn Sie mit der Windows-Kommandozeile CMD arbeiten,
    müssen sie ggf. kleiner Änderungen vornehmen,
    damit die Beispiele funktionieren.


<!--UEB-Hallo Git!--><h2>Schritt 1 - Hilfe</h2>

Starte im Verzeichnis `aufgaben/intro-hallo-welt`.

Lassen Sie sich die Hilfeseite zum `log`-Befehl ausgeben.

<!--UEB-Hallo Git!--><h2>Schritt 2 - Setup</h2>

Starte im Verzeichnis `aufgaben/intro-hallo-welt`.

Konfigurieren Sie Benutzername und Email:

    $ git config --global user.name mein-name
    $ git config --global user.email meine-email

<!--UEB-Hallo Git!--><h2>Schritt 3 - Das erste Repos</h2>

Starte im Verzeichnis `aufgaben/intro-hallo-welt`.

Erstellen sie ihr erstes Repository mit den folgenden Befehlen:

    $ git init myrepo
    $ cd myrepo
    $ echo 'welt' >hallo    # erzeugt eine Datei
    $ git add hallo
    $ git commit -m 'Hallo Welt!'
    $ git log

[Zur Lösung](loesung-intro-hallo-welt.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

