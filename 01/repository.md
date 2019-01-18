# Repository

_________________________________________

## Lernziel

Was findet man in einem Git-Repository? Wie untersucht man es?

 * Repository
    * Commit-Graph: Commit, Tree, File (Blob)
    * Branches und Tags (Refs)
 * Workspace

_________________________________________

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

_________________________________________

## Übung

Mit dem `clone`-Befehl kopieren wir ein Git-Repository auf unseren Rechner, um es zu untersuchen.

```bash
    git clone <server-url>/git-workshop.git
    cd git-workshop
    ls -lah
```

_________________________________________


Zwei Dinge sind aufgetaucht:

 1. Das **Repository**

    (in `git-workshop/.git`)

 1. Der **Workspace**

    (alle anderen Dateien unter `git-workshop`)


_________________________________________

## Repository

Damit Git **dezentral** (unabhängig vom Server) arbeiten kann enthälte es eine Datenbank

 * alle **Versionen** aller Dateien über die **volle Historie**

Außerdem:

 * **Metadaten**: Wer? Wann?
 * Projektdaten
   - **Branches** (falls parallel an verschiedenen Versionen gearbeitet wird)
   - **Releases** (genannt *Tags*)

_________________________________________


### Commits/Revisions

Ein Versionsstand eines Projekts wird *Commit* (Synonym *Revision*) genannt.

In einem Repository ist immer (höchsten) ein Commit aktiv, dieses nennt man `HEAD`.


_________________________________________

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

_________________________________________

Ein *Commit* enthält

 * **Tree**

   Exakter Stand aller versionierten Dateien in ihren Verzeichnissen

 * **Metadaten**

     - Autor und Zeitpunkt der Änderung
     - Message: Beschreibung der Änderung
     - Parent(s): Vorgängerversion(en)

 * **Revision Hash**

   Prüfsumme über Dateinhalten und Struktur, Autor, Zeitpunkt, Commit-Kommentar und Parent-Revision.

_________________________________________


### Historie

Die Historie die die Menge aller Vorfahren eines Commits.

`git log master` zeigt alle Commits, die zur Entstehung des aktuellen Master-Standas beigetragen haben.

Die Historie kann verzweigungen enthalten,
z. B. wenn mehrere Entwickler beteiligt waren.

`git log --graph` stellt diese Verzweigungen dar.

_________________________________________



### Was ist drin, im Repository?

 * `log` 15: Zeigt die Historie, die zum aktuellen Commit geführt hat.
 * `--oneline`
     - `log -3` Ausgabe limitieren
     - `log <file>`
   - Aktuelle Version und Vorgänger: `HEAD`, `HEAD~1`, `HEAD~2`
 * `--all`, `--graph`


_________________________________________

##  Diff

Der diff-Befehl kann die Dateien (Trees) beliebiger Commits vergleichen.

    git diff 7ac0f3 2f43cd

  - `diff 1a8a2 9f5c3  -- inhalt.md`, nur eine Datei
  - `diff 1a8a2 9f5c3  --stat`, Anzahl geänderter Zeilen je Datei
  - `difftool`

Optionen: `-b/--ignore-space-change`, `--word-diff`

_________________________________________

##  Weitere Befehle zum untersuchen der Historie

   - `show` zeigt Details zu einem Commit
      - `show 9f5c3`
      - `show 1a8a24a:protokoll.md`
   - `ls-tree` zeigt Verzeichnisinhalte im Commit-Tree


_________________________________________

## Workspace

Hier kann man Folgendes finden:

 * Dateien des gerade akutellen Projektstandes (`HEAD`)
   - die können lokal bearbeitet werden
 * noch unversionierte Dateien
 * von Git ignorierte Dateien




_________________________________________

## Checkout: Repository -> Workspace

Bestimmte Version ausgewählter Dateien wieder herstellen:


    git checkout 83fe378 -- foo.bar

Um Änderungen im Workspace zu verwerfen, wird der checkout-Befehl mit dem
Argument HEAD verwendet. ACHTUNG: „checkout HEAD“ ohne Dateiname verwirft nichts.

    git checkout HEAD foo.txt #Änderung einer Datei verwerfen
    git checkout HEAD . #Alle geänderten und gelöschten Dateien wiederherstellen

_________________________________________

## Checkout

Auf eine ältere Version zurückgehen

    git checkout 83fe378

ACHTUNG: `Detached HEAD`-State! Man kann die Version verwenden, aber nicht sinnvoll weiterbearbeiten. Dazu benötigt man einen Branch (späteres Kapitel).

_________________________________________

### Ref - Zeiger auf ein Commit

Ein *Ref* ist ein Zeiger auf ein Commit.
Der Log-Befehl zeigt die Refs idR. mit an, d. h. `--decorate` kann weggelassen werden.

```bash
git log --decorate --oneline

1d8425c (HEAD -> master, tag: testtag) Add content to commits chapter.
bb00978 (origin/master) Add content to repository chapter.
```


z. B.

 * `HEAD`
 * `master` (Branch)
 * weitere Branches
 * Tags

_________________________________________

## Branches und Tags anzeigen

```bash
git branch

git tag
```
_________________________________________

## Elementare Begriffe

Mitreden, wo es um Git geht
   * Repository
   * Workspace
   * Klon
   * Commit, Revision, Revision-Hash
   * Tree
   * Ref, Branch, Tag

_________________________________________

## Übung: Repository untersuchen

Starten mit:

     git checkout uebung/repository

_________________________________________

## Übung: Fragen

 * Was ist im aktuellen Commit passiert?
 * Wie oft wurde `content.md` geändert?
 * Wann wurde etwas zusammengefühqqrt?
 * Welches Datei wurde im "Ur-Commit" hinzugefügt?
 * Welcher "seltsame Autor" hat beigetragen?

_________________________________________

## Übung: Anworten

 * Was ist im aktuellen Commit passiert?
   "Workflow:"-Prefix aus Überschrift entfernt
 * Wie oft wurde `content.md` geändert?
   In 5 Commits (ein davon Erstellung)
 * Wann wurde etwas zusammengefühqqrt?
   `2bd9289`, 25.9.2018 18:42 Uhr
 * Welches Datei wurde im "Ur-Commit" hinzugefügt?
   `.gitmodules` (in `b4db3f6`)
 * Welcher "seltsame Autor" hat beigetragen?
   Torsten Test (`git log --pretty="%an" | uniq`)

_________________________________________

## Nach der Übung

    git checkout master
