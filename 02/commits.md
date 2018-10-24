# Commits
_________________________________________

## Lernziel

    status / add / commit -a / tag
    stash / revert

 * Commits **erstellen**
 * Woraus besteht ein Commit?
   Commit Description, Tree, Blob
 * Tags vergeben
 * **Oops!:**
   Was tun, falls doch mal was schief geht?

_________________________________________

## Status

Status abfragen nach Änderungen:

  * Aktiver Branch (und ggf. Synchronisationsstatus)
  * Datei gelöscht,
  * Datei geändert,
  * Datei neu angelegt
  * Ggf. Staging- und Mergekonfliktinformationen (spätere Abschnitte)

_________________________________________


Der `diff`-Befehl nur einem Parameter vergleicht geine Revision
mit dem *Workspace*,

bei `HEAD`  mit dem aktuellen Commit. HEAD ist Default, wenn keine Revision angegeben ist.

    git diff HEAD

Der diff-Befehl kann auch auf eine einzelne Datei angewendet werden.

    git diff HEAD -- foo.txt


_________________________________________

### Aktiver Branch

 * Ein Branch ist ein Zeiger auf ein Commit
 * (max.) 1 Branch ist aktiv
 * Default `master`
 * Neues Commit: Zeiger des aktiven Branches wird aktualisiert.

_________________________________________

### Commit - geänderte Dateien

Einzelne Datei:

    git commit <file>

Alle geänderen Dateien, die bereits versioniert sind.

    git commit -a

Und gleich mit Message:

    git commit -am "Edit some files."
_________________________________________

### Commit - neue Dateien

Neue Dateien werden beim `commit -a` nicht automatisch übernommen. Sie müssen zunächst angemeldet werden.

    git add <file>

    git add <dir>

    git add .

Dann Commit, wie gehabt.

    git commit
_________________________________________

### Commit - gelöschte Dateien

    rm my-file # Oder über file explorer

Löschungen werden bei `-a` übernommen:

    git commit -am 'deleted my-file'

Es gibt auch einen Git-Befehl zum Löschen:

    git rm my-file
    git commit -am 'deleted my-file'

_________________________________________

## Umbenennen und verschieben

   - `mv`
   - `log --follow`, `-M`
   - Tipp: Separate move from Change
     1. Move
     1. Commit
     1. Change
     1. Commit

_________________________________________

Was genau ist in einem Commit enthalten?

    git log --pretty=raw

_________________________________________

Commit Trees

![Commit Trees](02/commit-tree.jpg)

_________________________________________


## Verzeichnisse

Verzeichnisse verden in Git nicht explizit versioniert.

Ein Verzeichnis muss mindestens eine Datei enthalten.

Ggf. legt man ein hidden File an, z. B. `.gitkeep`

_________________________________________

## Tags

    git tag v0.1.7

_________________________________________

### Oops - Unterbrechung!

Angefangene Änderungen kann man jeder zeit mit `stash` wegsichern.

    git stash
    git stash -u
    git stash --all
    git stash -m "bugfix started"
    git stash pop


_________________________________________

### Oops - fehlerhaftes Commit


    revert  HEAD~3

_________________________________________

### Oops - versehentlich geändert

Vorsicht: Dateien werden überschrieben!

Datei versehentlich geändert

    git checkout HEAD -- foo

Alte Version einer Datei wieder herstellen

    git checkout HEAD~3 -- foo

Ganzen Workspace zurücksetzen

    git stash -u

