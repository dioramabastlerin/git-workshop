# Repository!!

<!-- .slide: data-background-image="repository/git-log.png" data-background-opacity="0.7" -->

---

## Lernziel

Was ist ein Git-Repository?\
Und wie findet man heraus,\
was darin enthalten ist?

---

### Themen

 * Repository und Workspace
 * Klone und Dezentralität
 * Commits und Revision-Hashes
 * Das Log
 * Checkout
 * Branches, Tags und HEAD

---

## Repository und Workspace

---

### Übung

Wir untersuchen ein Repository. Der `clone`-Befehl bringt es auf unseren Rechner.
```bash
    $ git clone <server-url>/git-workshop.git
    $ cd git-workshop
    $ ls -lah
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

    (es liegt in `.git`)

 1. Der **Workspace**

   (alle anderen Dateien und Verzeichnisse, die nicht in `.git` liegen)

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

### Übung

Wir lassen uns die Commits zeigen.
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
Erkenntnis: Das von uns geklonte Repositor enthält die ganze Historie ders Projekts.

---

## Klone und Dezentralität

---

Warum enthält\
das geklonte Repository\
die ganze Historie des Projekts?

---

 * (Bild: Dezentraler Austausch mit Push/Pull)
 * In jedem Klon wird unabhängig gearbeit.
 * Fast alle Befehle arbeiten lokal (und damit schnell).
 * Nur die Befehle `push`, `pull` und `fetch` übertragen Informationen zwischen den Klonen.
 * Oft erfolgt der Austausch über ein *Blessed Repository*.

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

## Checkout

---

### Checkout
 
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


### Übung 01: Repository untersuchen

Starten sie im *Übungsverzeichnis* (wo sie das Zip-Archiv mit den
Übungen entpackt haben).
Öffnen sie die Anleitung im *Browser* (mit dem Kommando `start` auf
Windows, `xdg-open` auf Ubuntu,`open` auf MacOs).
**Achtung!** Es ist wichtig, die Übungen im *angegebenen Startverzeichnis*
zu beginnen. Achten Sie auf die Beschreibung:

    $ cd git-uebungen-<Zeitstempel z. B. 202005252000>
    $ start aufgaben/01-repository-untersuchen/index.html
    $ cd aufgaben/<angegebenes Startverzeichnis>

Folgen Sie dann den weiteren Anweisungen.


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
    git checkout
```


