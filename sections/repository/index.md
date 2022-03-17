
## Der `clone`-Befehl

Erstellt lokale Kopie eines Git-Repositorys in einem lokalen Verzeichnis,z.B. 

```bash
    $ git clone https://github.com/bstachmann/git-workshop.git
```    

## Klon

Eine solche Kopie nennt man einen **Klon**.

---

### Demo

Wir untersuchen ein Repository. 

```bash
    $ git clone https://github.com/bstachmann/git-workshop.git

    $ cd git-workshop

    $ ll
    ...
    drwxrwxr-x   3 bjoern bjoern 4,0K Aug  9 19:54 css
    drwxrwxr-x   2 bjoern bjoern 4,0K Jun 24 18:20 debugging
    drwxr-xr-x  10 bjoern bjoern 4,0K Aug  9 20:02 .git
    -rw-rw-r--   1 bjoern bjoern    6 Apr 25 22:00 .gitignore
    drwxrwxr-x   3 bjoern bjoern 4,0K Jun 24 18:20 gitlab-ci
    ...
```

---

Zwei Dinge sind aufgetaucht:

 1. Das **Repository**

    (es liegt in `.git` im obersten Verzeichnis des Projekts)

 1. Der **Workspace**

   (alle anderen Dateien und Verzeichnisse, die nicht in `.git` liegen)

Sonderfall: Repositorys ohne Workspace nennt man *bare Repositorys*.


---


### Repository

Eine Datenbank über die **Historie** des Projekts

### Commits 

Auch **Revisions** oder **Versionen** genannt

 * Snapshots über alle Daten des Projekts
 * **+** Autor, Zeitpunkt, Beschreibungen

 ### `HEAD`

bezeichnet das aktuelle Commit 
und ist Urprung der Dateien im Workspace.

`HEAD` ist bei vielen Befehlen Default-Wert\
und darf weggelassen werden.


---


## Repository untersuchen

 * `git log`: Auflisung von Commits
 * `git branch`: Listet Branche
 * `git tag: Listet Tags (bennante Versionen)
 * `git show`: Details zu *einem* Commit
 * `git ls-tree`: Listet Verzeichnisstruktur eines Commits


---

### Demo: `git log`

```bash
    $ git log --all --graph --oneline

    * 215f901 (feature-a) : Edit file bar
    | * da35f72 (HEAD -> master) Created file und-tschuess 
    | * e639565 (tag: release1.1) : Edit file bar 
    | * a15459c (some-old-branch) : Edit file bar 
    |/  
    * 1614349 : Edit file hallo-welt 
    ...
```

 * `HEAD` ist das aktive Commit.
 * `main` ist der Name eines Branches.
 * `release1.1` ist ein Tag (benannte Version)


---


## `git log` 

Zeigt die Historie des `HEAD`-Commits

 * `--oneline`: Eine Zeile je Commit
 * `--graph`: Graphische Darstellung
 * `--all`: Historie aller Branches und Tags


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


### Begriff: `HEAD`

`HEAD` bezeichnet die vorige Version.

(von vorigem `commit`, oder `checkout`)

```bash
git show HEAD
```


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


## Branches

Branches repräsentieren unterschiedliche Stränge der Entwicklung, z. B. für

 * parallele Entwicklung von Features
 * Pflege von mehreren Release-Lines/Produktgenerationen


Technisch: Branches sind bewegliche Zeiger auf Commits.


---

Anzeigen, welche Branches es *lokal* gibt. 

`*` zeigt, welcher Branch gerade aktiv ist.

```bash
$ git branch
  feature-a
* master
```

`-r` zeigt, welche Branches *Origin* hat.

```bash
$ git branch -r
  origin/main
  origin/feature-b
```

*Origin* ist das Repo von dem man geklont hat.

---

## Branch wechseln

`switch` lädt den Stand eines Branches in den Workspace.

```bash
$ git switch feature-b
Switched to branch 'feature-b'
```

Außerdem wird der Branch zum *aktiven Branch*.


---


## Tags

Tags sind *eingefrorene* Stände des Projekts, z. B. 

 * für Releases, z. B. `v1.0.2`, `v1.0.3`
 * zur Kennzeichnung von Versionen,<br/> z. B. `itest-passed/2022-02-17`


Technisch: Tags sind feste Zeiger auf Commits.


---

`tag` liste die Tags auf:

```bash
$ git tag
release1.0
release1.1
```

---


### Begrif: Ref 

*Ref* ist Oberbegriff für Branches, Tags und `HEAD`.

Ein *Ref* ist ein Zeiger auf ein Commit.
Der Log-Befehl zeigt die Refs an.

```bash
$ git log --oneline

1d8425c (HEAD -> master, tag: testtag) Add content to commits chapter.
bb00978 (origin/master) Add content to repository chapter.
```

Die *Refs* sind in `.git/refs/` abgelegt.


---

## Auf beliebige Versionen wechseln

mit `--detach` kann `switch` kann nicht nur auf Branches, sondern auf beliebige Versionsstände wechseln.


```bash
$ git switch --detach 39a7fe
$ git switch --detach v1.0.3
$ git switch --detach HEAD
$ git switch --detach HEAD~3
```

Allerdings gibt es dann natürlich keinen *aktiven Branch* (`detached head`). 


---


## Checkout

`checkout` kann auf andere Versionen wechseln und/oder Inhalte von Dateien und Verzeichnisse wiederherstellen.

In neuen Versione von Git gibt es dafür zwei separate Befehle:

 * `switch` wechselt den ganzen Workspace und `HEAD` auf eine andere Version/Branch
 * `restore` tauscht gezielt Inhalte von Dateien/Verzeichnissen aus.

---

### Checkout (deprecated)
 
Commit -> Workspace

Einzelne Dateien/Verzeichnisse wieder herstellen:

```bash
    git checkout 83fe378~1 -- foo         # Vorige Version von "foo" in den Workspace bringen
    git checkout 83fe378~1 -- src/        # Vorige Version aller Datein in src
                                            Workspace bringen

    git checkout HEAD -- foo              # Lokale Änderungen an "foo" entfernen
```

Anmerkung: Wenn Dateien oder Verzeichniss angegeben sind, wechselt `HEAD` nicht

**Vorsicht!** Dateien werden ohne Sicherung überschreiben.

---

Auf eine ältere Version zurückgehen

    git checkout 83fe378
    
Anmerkung: Da keine Dateien oder Verzeichniss angegeben sind, wechselt der `HEAD`-Stand.

ACHTUNG: `Detached HEAD`-State! Man kann die Version verwenden, aber nicht sinnvoll weiterbearbeiten. Dazu benötigt man einen Branch (späteres Kapitel).


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


