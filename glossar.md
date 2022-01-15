---
layout: page
title: Glossar
nav_order: 85
has_children: false
permalink: /glossar
---
 
### 1st Parent History 

Beim Auflisten der History wird im Falle von Merges im nur der erste Vorgänger berücksichtigt. 

### Aktiver Branch

Es gibt höchten einen aktiven Branch (*Current Branch*). 
Neu Commits werden dem aktiven Branch zugeordnet.
Ist kein Branch aktiv, spricht man vom *Detached Head*.

### Autor

Die Metadaten zum Commit erlauben es Autor und Committer zu unterscheiden.
Der Autor ist derjenige, der den Code geschrieben hat.
Meist sind Autor und Committer jedoch identisch.

### Backport

Wenn man eine Änderung, die in einer neuen Version gemacht wurde,
auf eine ältere Version übertragen wird spricht man von einem Backport.
Die kann man beispielsweise mit dem Cherry-Pick-Befehl machen.

### ⭐ Branch

Ist ein bennannter Strang zur parallelen Enwicklung, z.B. `main` oder `feature-a`.
Anmerkung: In Git ist Branching keine Voraussetzung für parallele Entwicklung. 
Wird in mehreren Klonen eines Repos gearbeitet,
entstehet parallele Entwicklung auch wenn überall auf `main` gearbeitet wird.
 

### ⭐ Commit
### Committer

Die Metadaten zum Commit erlauben es Autor und Committer zu unterscheiden.
Der Committer ist derjenige, der den Code ins Repository integriert hat,
z.B. ein Maintainer der einen Verbesserungsvorschlag (Patch) integriert.
Meist sind Autor und Committer jedoch identisch.

### Continuous Delivery

Automatisiertes Bauen, Testen und Ausliefern von Softfware in einer sogenanten *Build Pipeline*. 
*Pipelines* werden meist durch ein neues Commit oder ein neues Tag angestoßen.

### Checkout

Ein Checkout kopiert den in einer (*Revision*) gespeicherten Datei- und Verzeichnisbaum als Arbeitsdateien in den Workspace.
IdR. ist diese *Revision* dann das neue *Head*.

### Cherry-Pick

Wendet die Änderungen eines frei gewählten Commits 
im aktuellen Branch an.

### CLI

*Command Line Interface* gemeint ist das `git`-Kommando mit seinen Unterbefehlen.
Oft als Abgrenzung zu grafischen Oberflächen (*GUI*).


### Detached Head

Wenn kein Branch aktiv ist, spricht man vom *Detached Head*.
Dies tritt beispielsweise auf, wenn Checkout einer
älteren Version macht, die nicht das oberste Commit eine Branches ist.

### dezentral

In Git ist mit dezentral, dass man in einem einem Klone alle Operationen der Versionierung (Committen, Mergen, Log Untersuchen, etc.) ohne Verbindung zu einem Server durchführen kann. Mit *Push* und *Pull* werden Änderungen zwischen 
beliebigen Klonen ausgetauscht.
Anmerkung: Meist erfolgt der Austausch innerhalb von Teams indirekt über einen zentralen Klon, z. B. bei GitHub oder GitLab.

### Feature-Branch

Branch, der für die Entwicklung eines bestimmten Features vom Haupstrang abgezweigt wird.

### fast-forward



### Gitflow
### History
### Integrations-Branch
### ⭐ Head
### Index
### ⭐ Klon
### ⭐ Merge
### Merge-Request
### Merge-Ketten
### ⭐ Log
### Origin
### Parent 
### ⭐ Push
### ⭐ Pull
### ⭐ Pull-Request
### ⭐ Rebase
### ⭐ Repository
### ⭐ Revision-Hash 
### Remote
### Remote Ref
### Rename Detection
### Ref
### Upstream Branch  
### Staging
### Staging-Branch
### Stash
### Submodule
### Subtree
### Tag
### Tree
### Trunk Based Development
### ⭐ Workspace
### zentral