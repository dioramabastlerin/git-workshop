### Lernziel (Befehle)

```bash
    # Commits erstellen
    git status
    git diff
    git commit

    # Änderungen hinzufügen (Staging,Index)
    git add
    git commit -a

    # Commits mit Tags markieren
    git tag

    # Oops!
    git stash
    git revert
```

---


 * In jedem Klon wird unabhängig gearbeit.
 * Fast alle Befehle arbeiten lokal (und damit schnell).
 * Nur die Befehle `push`, `pull` und `fetch` übertragen Informationen zwischen den Klonen.
 * Oft erfolgt der Austausch über ein *Blessed Repository*.


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


### `git status`

zeigt, was im Workspace los ist:

  * Welches ist der *aktive Branch*?
  * Wurden Dateien (bezogen auf `HEAD`)
    - gelöscht?
    - bearbeitet?
    - neu angelegt?
  * Außerdem: Mergekonflikte, Synchr.-Status

Notes:

 * Verzeichnisse werden zusammengefasst
 * Außerdem Änderungen bezogen auf HEAD oder Index

---

### Tipp

Vor wichtigen Operationen,

immer checken, ob der Status *clean* ist:

```bash
    $ git status
      ...
    nothing to commit, working tree clean
```

Notes:

 * Hinweise auf stash
 * TODO Hinweis auf Prompts

---

### Aktiver Branch

 * Ein Branch ist ein Zeiger auf ein Commit
 * (max.) 1 Branch ist aktiv
 * Default `master`
 * Neues Commit: Zeiger des aktiven Branches wird aktualisiert.


---


## `git commit`

 * Erstellt ein neues Commit
 * Setzt `HEAD` auf den neue Commit
 * Setzt den aktiven Branch auf das neue Commit


---


### Commit - geänderte Dateien

Einzelne Datei:

    git commit <file>

Alle geänderen Dateien, die bereits versioniert sind.

    git commit -a

Und gleich mit Message:

    git commit -am "Edit some files."


---


### Commit - neue Dateien

Neue Dateien werden beim `commit -a` nicht automatisch übernommen. Sie müssen zunächst angemeldet werden.

    git add <file>

    git add <dir>

    git add .

Dann Commit, wie gehabt.

    git commit


---

### Commit - gelöschte Dateien

    rm my-file # Oder über file explorer

Löschungen werden bei `-a` übernommen:

    git commit -am 'deleted my-file'

Es gibt auch einen Git-Befehl zum Löschen:

    git rm my-file
    git commit -am 'deleted my-file'


---


Git kann Verschiebungen von Dateien erkennen (*Rename Detection*).

Wie geht das?


---

Wenn in einem Commit,

 * eine Datei gelöscht wurde,
 * und eine neue Datei hinzugekommen ist, und
 * die Inhalte (fast) gleich sind,
 
geht Git davon aus, dass eine Datei verschoben wurde.

Die Option `--follow` am `log`-Befehl forciert die *rename detection*.


---


### Commit - Verschieben von Dateien

```
   mv hallo hello       
   git add .
   git commit -m "hallo -> hello"
```

Die Historie von `hello`:

   git log --follow -- hello
   
   

---


###  Tipp: Separate move from change

 1. Move
 1. Commit
 1. Change
 1. Commit


---

## Verzeichnisse

Verzeichnisse werden in Git nicht explizit versioniert.

Ein Verzeichnis muss mindestens eine Datei enthalten.

Ggf. legt man ein hidden File an, z. B. `.gitkeep`

---

### Übung

<h2><a href="markdown-git-uebungen/aufgabe-commits-erstellen.html" target="_blank">Commits erstellen<a></h2>