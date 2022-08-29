### Oops - Unterbrechung!

Angefangene Änderungen kann man jederzeit mit `stash` wegsichern.

    git stash
    git stash -u
    git stash --all
    git stash -m "bugfix started"
    git stash pop


---

### Oops - fehlerhaftes Commit


    git revert  HEAD~3


---


### Oops - versehentlich geändert

Vorsicht: Dateien werden überschrieben!

Datei versehentlich geändert

    git restore foo

Alte Version einer Datei wieder herstellen

    git restore -s HEAD~3 -- foo

Ganzen Workspace zurücksetzen

    git stash -u

