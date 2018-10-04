# Commits
_________________________________________

## Lernziel

```
    status / add / commit -a / rm / stash / revert / tag
```

 * Lokale entwickeln können
 * Commits **erstellen**
 * Neue, bereits versionierte und zu löschende Dateien
 * Tags vergeben
 * **Object Storage, Tree, Blob:** Verstehen, wie Git Commits speichert?
 * **Oops!:** Was tun, wenn etwas schief geht?


_________________________________________

## Status

Status abfragen nach Änderungen:

  * Aktiver Branch (und ggf. Synchronisationsstatus)
  * Datei gelöscht,
  * Datei geändert,
  * Datei neu angelegt
  * Ggf. Staging- und Mergekonfliktinformationen (spätere Abschnitte)

_________________________________________

### Bearbeitete Datei committen

Einzelne Datei

    git commit <file>

Geänderte bereits versionierten Dateien

    git commit -a

Und gleich mit Message:

    git commit -am "Edit some files."
_________________________________________

## Neue Dateien

    git add <file>

    git add <dir>

    git add .

Dann Commit, wie gehabt.

_________________________________________

## Gelöschte Datei



_________________________________________

 * Exkurs: Trees and the Object Storage
    ![Trees and the Object Storage](abb/trees-and-object-storage.jpg)
   - Zeigen, woraus ein Commit-Hash gebildet wird
     `git log --pretty=raw`

_________________________________________


## Neues Verzeichnis

_________________________________________

## Oops

   - Unterbrechung
     - `stash` 66
     - `stash list`
     - `stash pop`
     - `stash -u`

_________________________________________

## Oops

   - Datei sollte gar nicht geändert werden.
     - `checkout` 22,26
       - lokale Änderungen?!
     - `git checkout HEAD~2  protokoll.md`
   - War falsch, aber schon länger her
     - `revert`
   - Änderungen Rückgängig 22,23
   - Vorsicht mit `reset`

_________________________________________

## Löschen von Dateien

   - `rm`

_________________________________________

## Umbenennen und verschieben

   - `mv`
   - `log --follow`, `-M`
   - Tipp: Separate move from Change
     1. Move
     1. Commit
     1. Change
     1. Commit

