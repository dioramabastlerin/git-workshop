# Commits
_________________________________________

## Lernziel

```
    status / add / commit -a / rm / stash / revert
```

 * Lokale entwickeln können
 * Commits **erstellen**
 * Neue, bereits versionierte und zu löschende Dateien
 * **Object Storage, Tree, Blob:** Verstehen, wie Git Commits speichert?
 * **Oops!:** Was tun, wenn etwas schief geht?


_________________________________________

## Datei bearbeiten

   - `status` 13
   - `diff` 19
   - `add`, Staging 14,18
   - `commit <file>` 12,15
   - `commit -a` 12,15

_________________________________________

## Neue Datei

   - `add <file>`
   - `add <dir>`
   - `add .`


_________________________________________

 * Exkurs: Trees and the Object Storage
    ![Trees and the Object Storage](abb/trees-and-object-storage.jpg)
   - Zeigen, woraus ein Commit-Hash gebildet wird
     `git log --pretty=raw`

_________________________________________


## Neues Verzeichnis

_________________________________________

## Neues Repo

 * `init` 12
 * Ad-Hoc-Versionierung

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

