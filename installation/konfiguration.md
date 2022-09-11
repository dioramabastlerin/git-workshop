---
layout: page
title: Git konfigurieren
nav_order: 10
parent: Installation
permalink: /installation/konfigurieren
---

### Git konfigurieren

Prüfe, ob Username und Email schon gesetzt ist.

```bash
    $ git config --global user.name
    davinci
    $ git config --global user.email
    leonardo.davinci@italia.it
```

Falls nicht, trage Deine Daten so ein:

```bash
    $ git config --global user.name <mein-name>
    $ git config --global user.email <meine-email>
```

### (Optional) Mergeverhalten konfigurieren

Für die Übungen empfehle ich die folgenden Einstellungen, 
damit die Ausgabei bei Euch, denen in den aufgezeichneten Musterlösungen 
entsprechen.

```bash
    $ git config --global pull.rebase false    
    $ git config --global merge.conflictStyle diff3
```

### (Optional) Texteditor

Editor für Commit-Message etc. konfigurieren, hier z. B. `notepad`:

```bash
    $ git config --global core.editor notepad
```

Tipp: [Beispielkonfigurationen für beliebte Editoren](https://git-scm.com/book/en/v2/Appendix-C%3A-Git-Commands-Setup-and-Config). Auf der Seite etwas nach unten scrollen.


### (Optional) Merge- und Difftool auswählen


Und dalls ihr Tortoisegit installiert habt:

```bash
    $ git config --global merge.tool tortoisemerge

    $ git config --global diff.tool tortoisediff
    $ git config --global difftool.tortoisediff.cmd "TortoiseGitMerge \$LOCAL \$REMOTE"
```

Mehr dazu auf [stackoverflow](https://stackoverflow.com/questions/16493368/can-tortoisemerge-be-used-as-a-difftool-with-windows-git-bash)

