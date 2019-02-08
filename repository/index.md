# Repository

---

## Lernziel

Was findet man in einem Git-Repository? Wie untersucht man es?

 * Dezentralität, Klon
 * Repository
    * Commit-Graph: Commit, Tree, File (Blob)
    * Branches und Tags (Refs)
 * Workspace

---

### Lernziel (Befehle)

```bash
    # Commit-Graph
    git log
    git show
    git diff

    # Refs
    git branch -v
    git tag

    # Workspace
    git checkout
```

---


## Übung

Mit dem `clone`-Befehl kopieren wir ein Git-Repository auf unseren Rechner, um es zu untersuchen.
```bash
    git clone <server-url>/git-workshop.git
    cd git-workshop
    ls -lah
```


---

Zwei Dinge sind aufgetaucht:
 1. Das **Repository**

    (in `git-workshop/.git`)

 1. Der **Workspace**

   (alle anderen Dateien unter `git-workshop`)


---

## Repository

Damit Git **dezentral** (unabhängig vom Server) arbeiten kann,
enthälte es eine Datenbank
mit der **gesamten Historie** eines Projekts.


---

## Inhalte des Repositorys

 * alle **Versionen** aller Dateien über die **volle Historie*
 * **Metadaten**: Wer? Wann?
 * Projektdaten
   - **Branches** (falls parallel an verschiedenen Versionen gearbeitet wird)
   - **Releases** (genannt *Tags*)


---

## Befehle zum Untersuchen von Commits

```bash
    # show zeigt detaillierte Informationen zu Commits
    git show HEAD
    git show HEAD:README         # Inhalt der Datei
    git show --pretty=raw HEAD   # Was Git in der DB hat

    # mit ls-tree kann man den Verzeichnisbaum untersuchen
    git ls-tree -r HEAD
    git ls-tree --abbrev HEAD src/main/java
```

---

# `HEAD`

bezeichnet das aktuelle Commit und
ist bei vielen Befehlen der Default-Wert
und kann oft weggelassen werden.


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
    git show HEAD
    git show master
    git show v1.0.0
```


---

### Ein *Commit* enthält

 * **Tree** - exakter Stand aller versionierten Dateien und Verzeichnisse
 * **Metadaten** - Autor und Zeitpunkt der Änderung, Beschreibung der Änderung
 * **Parent(s)** - Vorgängerversion(en)
 * **Revision Hash** - Prüfsumme über Dateinhalte und Struktur, Autor, Zeitpunkt, Commit-Kommentar und Parent-Revision.

---


### Das Log / die Historie

Bis auf das Erste haben alle Commits einen Parent.
Die Historie ist die Menge aller Vorfahren eines Commits.
Sie kann Verzweigungen enthalten,
z. B. wenn mehrere Entwickler parallel gearbeitet haben.
Mit `~` kann man Vorfahren adressieren.

---

`git log master` zeigt alle Commits, die zur Entstehung des aktuellen Master-Standas beigetragen haben.

```bash
    # log zeigt die Historie
    git log HEAD
    git log HEAD -- README    # Historie einer Datei
    git log --oneline HEAD
    git log --graph HEAD

    git show HEAD~2          # vorvorletztet Commit
```


---

##  Diff

Der diff-Befehl kann die Dateien (Trees) beliebiger Commits vergleichen.

```bash
    # diff vergleicht zwei Commits
    git diff HEAD~4 HEAD
    git diff 1a8a2 9f5c3 -- inhalt.md  # einzelne Datei
    git diff 1a8a2 9f5c3 --stat        # Überblick
    git diff HEAD~3                    # Vergleich mit HEAD


    # externes tool nutzen
    git difftool HEAD~4 HEAD
```

Optionen: `-b/--ignore-space-change`, `--word-diff`


---

## Workspace

Hier kann man Folgendes finden:

 * Dateien des gerade aktuellen Projektstandes (`HEAD`)
   - die können lokal bearbeitet werden
 * noch unversionierte Dateien
 * von Git ignorierte Dateien


---

### Checkout: Repository -> Workspace

Bestimmte Version ausgewählter Dateien wieder herstellen:


    git checkout 83fe378 -- foo.bar

Um Änderungen im Workspace zu verwerfen, wird der checkout-Befehl mit dem
Argument HEAD verwendet. ACHTUNG: „checkout HEAD“ ohne Dateiname verwirft nichts.

    git checkout HEAD foo.txt #Änderung einer Datei verwerfen
    git checkout HEAD . #Alle geänderten und gelöschten Dateien wiederherstellen

---

## Checkout

Auf eine ältere Version zurückgehen

    git checkout 83fe378

ACHTUNG: `Detached HEAD`-State! Man kann die Version verwenden, aber nicht sinnvoll weiterbearbeiten. Dazu benötigt man einen Branch (späteres Kapitel).

---

### Ref - Zeiger auf ein Commit

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

## Elementare Begriffe

Mitreden, wo es um Git geht
   * Repository
   * Workspace
   * Klon
   * Commit, Revision, Revision-Hash
   * Tree
   * Ref, Branch, Tag

---

### Übung: Repository untersuchen

Starten mit:

     git checkout uebung/repository

---

### Übung: Fragen

 * Was ist im aktuellen Commit passiert?
 * Wie oft wurde `content.md` geändert?
 * Wann wurde etwas zusammengeführt?
 * Welches Datei wurde im "Ur-Commit" hinzugefügt?
 * Welcher "seltsame Autor" hat beigetragen?

---

## Übung: Anworten

 * Was ist im aktuellen Commit passiert?
   "Workflow:"-Prefix aus Überschrift entfernt
 * Wie oft wurde `content.md` geändert?
   In 5 Commits (ein davon Erstellung)
 * Wann wurde etwas zusammengeführt?
   `2bd9289`, 25.9.2018 18:42 Uhr
 * Welches Datei wurde im "Ur-Commit" hinzugefügt?
   `.gitmodules` (in `b4db3f6`)
 * Welcher "seltsame Autor" hat beigetragen?
   Torsten Test (`git log --pretty="%an" | uniq`)

---

## Nach der Übung

    git checkout master


