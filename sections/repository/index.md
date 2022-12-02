## Repository und Workspace


---

## Repository

**Git** ist eine Versionsverwaltung.

Ein Git-**Repository** ein ist eine (hochspezialisierte) Datenbank zur Archivierung von Entwicklungsständen eines Softwareprojekts.

Das Repository enthält sämtliche Versionen aller Dateien des Projekts inklusive Information über Autoren und Zeitpunkte von Änderugen.


---


### Ein Projektverzeichnis enthält:


Das **Repository** (es liegt in `.git`).
Es enthält die gesamte Historie des Projekts:
Alle Versionen aller Dateien, Informationen über Autoren und Änderungszeitpunkt, alle Verzweigungen (*Branches*) und *Tags*.

Der **Workspace** (alle anderen Dateien und Verzeichnisse, die nicht in `.git` liegen)
enthält die Dateien und Verzeichnisse *eines Versionsstandes* (genannt  `HEAD`) zur Bearbeitung.


---


## Demo: `git-workshop` zeigen


---

### Der Workspace

umfasst alle Dateien und Verzeichnisse des Projekts

 * **versionierte Dateien**
   Dateien, die in der aktuellsten Git-Revision des Projekts,
   `HEAD` genannt, schon bekannt sind.
 * **unversionierte Dateien**
   Neue Dateien, die Git "noch nicht kennt".
 * **ignorierte Dateien**
   Die gar nicht versioniert werden sollen (Stichwort: `.gitignore`)


---


### Repository

Damit Git **dezentral** (unabhängig vom Server) arbeiten kann,
enthält es eine Datenbank
mit der **gesamten Historie** eines Projekts.

 * alle Versionen aller Dateien
 * Metadaten: Autor, Zeitpunkt
 * Branches: Ermöglichen parallele Entwicklungsstränge
 * Markierte Versionen, genannt Tags


---


### Begriffe 

**Commit,Revision** ist ein eingefrorener Versionsstand über *alle Dateien des Projekts*.

**`HEAD`** steht für jenes *Commit*, das im Workspace bearbeitet wird.

**Log**: Die Historie. Bezeichnet die Menge aller Commits, die zur Entstehung des `HEAD` beigetragen haben.


---


### Der `Log`-Befehl zeigt die Commits

```bash
    $ git log --oneline

    909af6d (HEAD -> master) Fix obsolete text on page
    02d3329 fix typo in link
    38efbcb Enable offline use
    28e7071 Enable offline use
    6721664 Overwork repository chapter
    2ca78c1 Remove duplicated slides
    330fd73 Fix missing git before command
    ...
```
Erkenntnis: Das von uns geklonte Repository enthält die ganze Historie des Projekts.


---


## Commits und Revision-Hashes


---


Das Git-Repository speichert Versionen (auch Revisions genannt) des Projekts
in Form von *Commits*. Jedes Commit wiederum hat

 * **Tree** - "Snapshot" aller Dateien und Verzeichnisse zu eine Zeitpunkt
 * **Metadaten** - Zeitpunkt der Änderung, Autor und Beschreibung der Änderung
 * **Parent(s)** - Vorgängerversion(en)
 * **Revision Hash** - die "Versionsnummer" von Git
   Prüfsumme über alle oben angegebenen Informationen.

---

### `HEAD`

bezeichnet das aktuelle Commit,/
ist bei vielen Befehlen Default-Wert\
und kann oft weggelassen werden.

---

## Befehle zum Untersuchen von Commits

```bash
    # show zeigt detaillierte Informationen zu Commits
    git show HEAD                # Infos zum HEAD-Commit
    git show                     # ebenso
    git show HEAD:README         # Inhalt einer Datei
    git show --pretty=raw HEAD   # Was Git in der DB hat

    # ls-tree listet Verzeichnisse auf untersuchen
    git ls-tree -r HEAD
    git ls-tree --abbrev HEAD src/main/java
```

---

### Revision-Hashes

Die Versionsnummern von Git

Versionen können in Git über ihre Revision-Hashes
oder über symbolische Namen (Refs) angesprochen werden.

```bash
    # Revision Hashes
    git show f6be3b8913aa0ff3daa2be27bd55032316545545
    git show f6be3b      # es darf abgekürzt werden

    # Refs
    git show HEAD        # "aktuelle" Version
    git show master      # ein Branch
    git show v1.0.0      # ein Tag
```

---

## Das Log

---

Bis auf das Allererste haben alle Commits einen Parent.
Die Menge aller Vorfahren eines Commits, z. B. `master,` nennt man **das Log**.
Es sind also alle Commits, die zur Entstehung des aktuellen Commits beigetragen haben.

`git log master`

---

Der Log-Befeht biete zahlreiche Optionen. Hier ein paar nützliche Beispiele:

```bash
    # log zeigt die Historie
    git log HEAD
    git log HEAD -- README    # Historie einer Datei
    git log --oneline HEAD
    git log --graph HEAD

    git show HEAD~2          # vorvorletztet Commit
```

Tipp: Mit `~` kann man Vorfahren adressieren.

---

### Der Commit-Graph

Das Log kann Verzweigungen enthalten und Zusammenführungen (Merges) enthalten,
z. B. wenn mehrere Entwickler parallel gearbeitet haben.

```
* | 5c65d40 Notizen zur Wiederholung
* | 040bb7d Zeitplan für early birds hinzugefügt
|/  
* b1fae20 Fixup
* 4137535 Add some aufgaben
* 8f900ba Refactor: Split git intro 
*   351872f Merge branch 'master' 
|\  
| * c81fde8 Update index.en.md
* | 9bf4c61 Add workshop: Git basics and best practices
|/  
* 5f58070 Modify link to edit files on github
```

---

Die Option `--graph` kann dies darstellen:

```bash
    git log --graph             # Graphen darstellen
    git log --graph --oneline   #  
    git log --graph --all       #

```

---

###  Vergleichen von Commits mit Diff

Der diff-Befehl kann die Dateien (Trees) beliebiger Commits vergleichen.

```bash
    # diff vergleicht zwei Commits
    git diff HEAD~4 HEAD
    git diff 1a8a2 9f5c3 -- inhalt.md   # einzelne Datei
    git diff 1a8a2 9f5c3 --stat         # Überblick
    git diff 1a8a2 9f5c3 --word-diff    # Wortweiser Vergleich f. Texte
    git diff 1a8a2 9f5c3 -b             # Whitespace-Änderunen ausblenden
    git diff HEAD~3                     # Vergleich mit HEAD

    # externes tool nutzen
    git difftool HEAD~4 HEAD
```


---

### `git blame`: Wer war's?

Zeigt, für jede Zeile, in welchem Commit diese zuletzt bearbeitet wurde.

```
38da02ef foo (bjoern 2021-04-02 14:54:30 +0000 1) Erste Zeile
9bb1b769 foo (bjoern 2021-07-29 17:03:01 +0000 2) Zeile zwei
21c9ad44 foo (bjoern 2021-07-29 17:15:19 +0000 3) Schluss
```

 * `-M -C -C -C` Zeigt bei kopierten/verschobenen Zeilen aus anderen Dateien das "Ursprungscommit". 
 * `-w` erkennt Zeilen trotz Whitespace-Änderungen wieder.

---

## Branches, Tags und HEAD

---

### Ref - Ein Name für ein Commit

Ein *Ref* ist ein Zeiger auf ein Commit.
Der Log-Befehl zeigt die Refs idR. mit an, d. h. `--decorate` kann weggelassen werden.

```bash
git log --decorate --oneline

1d8425c (HEAD -> master, tag: testtag) Add content to commits chapter.
bb00978 (origin/master) Add content to repository chapter.
```

---

### Beispiele für Refs

 * `HEAD`
 * `master` (Branch)
 * `feature-a` (Branch)
 * `v1.0.0` (Branch)

Mit der Option `--all` zeig `log` nicht nur die Historie des `HEAD`,
sonder aller Tags und Branches.

```bash
    git log --all --graph
```

---

## Branches und Tags anzeigen

```bash
git branch

git tag
```

---

### `git switch`
 

 * `HEAD` wird auf eine Zielversion gesetzt
 * Alle (versionierten) Dateien im Workspace werden auf den Stand gebracht.
 * `git switch <branch>` wechselt auf einen Branch. `<branch>` ist dann aktiver Branch
 * `git switch --detach <commit>` wechselt auf beliebiege Versionen. Danach ist kein Branch aktiv.
 * *uncommitted Changes* werden mitgenommen


---


### (veraltet)Checkout
 

Der Befehl `checkout`kann sowohl Branches wechseln Versionen wechseln, als auch Datei- und Versionhalte austauschen.
Leichter verständlich und weniger fehleranfällig sind jedoch die neueren
Befehle `switch` und `restore`.

---

### Übung

<h2><a href="git-uebungen/aufgabe-repository-untersuchen.html" target="_blank">Repository untersuchen<a></h2>


---


### Zusammenfassung (Begriffe)

   * Repository
   * Workspace
   * Klon
   * Commit, Revision, Revision-Hash
   * Tree
   * Ref, Branch, Tag

---

### Zusammenfassung (Befehle)

```bash
    # Commit-Graph
    git log
    git show
    git diff

    # Refs
    git branch -v
    git tag

    # Workspace
    git switch
    git restore
    (git checkout)
```


