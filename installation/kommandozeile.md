---
layout: page
title: Kommandozeile
description: Arbeiten mit der Bash
nav_order: 30
parent: Installation
---

Git ist vom Design her ein Kommandozeilen-Tool. Es gibt hervorragende grafische User Interfaces für Git.
Aber auch diese führen letzlich nur die zugrundeliegenden Kommandozeilenbefehle aus.
Um Git gut zu verstehen empfiehlt es sich daher die zentralen Git-Befehle kennen zu lernen.

## Navigation und Überblick in Verzeichnissen

 * `.` aktuelles Verzeichnis
 * `..` Parent-Verzeichnis

```bash
 # Dateien und Unterverzeichnisse auflisen
 $ ll
 $ ll foo/

 # Aktuelles Verzeichnis anzeigen
 $ pwd

 ## In das übergeordnete Verzeichnis gehen
 $ cd ..
 $ cd foo
```

## Autocomplete und Kommandohistorie

Oft genügt es wenige Zeichen zu tippen und dann vervollständigen zu lassen

 * 1x Tab-Taste: Vervollständigt Kommando/Dateipfad/Branch/... sofern eindeutig.
 * 2x Tab-Taste: Zeigt die Optionen, wenn nicht eindeutig.

Mit den **Cursor-Tasten Hoch/Runter** kann man **vorherige Kommandos** zurückholen und ggf. bearbeiten und dann mit der Enter-Taste erneut ausführen.

Profi-Tipp: Mit strg+r kann man in den vorherigen befehlen suchen. Mit Enter kann man das Sucherergbnis übernehmen, mit erneutem strg+r kann man erneut suchen, mit der ecs-Taste kann man die Suche abbrechen.

## Scrollen in langen ausgaben

Wenn Git-Befehle lange Ausgaben macheen kommt mann in einen Scroll/Blätter-Modus.

```bash
 $ git log
...
commit 71ca726ddcbbedc901e8a8b1cc3a5b5a346c96b5
Author: bstachmann <bstachmann@yahoo.de>
Date:   Thu Mar 17 19:25:00 2022 +0000

    Directory Listings aktualisiert.

:
```
Man erkennt den Scroll-Modus am "`:`" in der letzten Zeile.

 * Hoch/Runter-Taste zum Scrollen
 * **q**-Taste zum Beeden

## Git-Bash auf Windows

```bash
    # Aktuellen Pfad in Windows-Syntax ausgeben
    $ pwd -W

    # Explorer öffnen
    $ start . 
    $ start index.html
```