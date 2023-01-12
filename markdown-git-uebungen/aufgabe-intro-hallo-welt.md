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

## Kurze Info zu den ersten Git-Befehlen

* `git init` Erstellt ein neues Git-Repository.
* `git add <datei(en)>` Datei(-änderungen) für das nächsten Commit hinzufügen.
* `git commit -m <beschreibung>` Erstellt ein Commit.
* `git log` zeigt Commits an.


<!--UEB-Hallo Git!--><h2>Schritt 1 - Das erste Repo</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/intro-hallo-welt`.

Erstellen sie ihr erstes Repository mit den folgenden Befehlen:

    $ git init myrepo
    $ cd myrepo
    $ echo 'welt' >hallo    # erzeugt eine Datei
    $ git add hallo
    $ git commit -m 'Hallo Welt!'
    $ git log

[Zur Lösung](loesung-intro-hallo-welt.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

