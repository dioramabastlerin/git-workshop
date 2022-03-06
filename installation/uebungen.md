---
layout: page
title: Übungen vorbereiten
nav_order: 10
parent: Installation
---

Um die erste Übung beginnen zu können tun sie bitte Folgendes:

### Übungen entpacken

Die Übungen liegen jetzt als Zip-Datei vor und können entpackt werden.

    $ unzip build.zip
    $ cd build/git-uebungen/aufgaben/intro-hallo-welt/
    

### Git konfigurieren

Zunächst werden Name und Email konfiguriert:

    $ git config --global user.name <mein-name>
    $ git config --global user.email <meine-email>

Die folgenden Einstellungen, damit 

    $ git config --global pull.rebase false
    $ git config --global merge.conflictStyle diff3

(Optional) Editor für Commit-Message etc. konfigurieren, hier z. B. `notepad`:

    $ git config --global core.editor notepad

Tipp: Auf [dieser Seite](https://git-scm.com/book/en/v2/Appendix-C%3A-Git-Commands-Setup-and-Config) findet man Beispielkonfigurationen einige beliebte Editoren.



