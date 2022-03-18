---
layout: page
title: Konfiguration
nav_order: 20
parent: Installation
---

### Git konfigurieren

Zunächst werden Name und Email konfiguriert:

```bash
    $ git config --global user.name <mein-name>
    $ git config --global user.email <meine-email>
```

Die folgenden Einstellungen, damit 

```bash
    $ git config --global pull.rebase false
    $ git config --global merge.conflictStyle diff3
```

(Optional) Editor für Commit-Message etc. konfigurieren, hier z. B. `notepad`:

```bash
    $ git config --global core.editor notepad
```

Tipp: [Beispielkonfigurationen für beliebte Editoren](https://git-scm.com/book/en/v2/Appendix-C%3A-Git-Commands-Setup-and-Config). Auf der Seite etwas nach unten scrollen.

## Diff- und Merge-Tools

### TortoiseGit

```bash
    $ git config --global merge.tool tortoisemerge

    $ git config --global diff.tool tortoisediff
    $ git config --global difftool.tortoisediff.cmd "TortoiseGitMerge \$LOCAL \$REMOTE"
```

Mehr dazu auf [stackoverflow](https://stackoverflow.com/questions/16493368/can-tortoisemerge-be-used-as-a-difftool-with-windows-git-bash)

