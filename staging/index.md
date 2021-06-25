
## Der *Stage*-Bereich 

(aka *Index*)

Git sammelt Änderungen im Stage-Bereich.
`git add` "parkt" sie dort,
bis sie in einem Commit verwendet werden.

    Workspace   -add->   Stage   -commit->   Repository

d.h. wenn ändert man Dateien nach dem `add`,
werden diese beim Commit noch nicht übernommen.


Notes:

Im "Grundzustand" enspricht die Stage dem HEAD-Stand


---


### Tipps zum Staging

Unterschied zwischen Workspace und Stage:

    git diff

Unterschied zwischen Workspace und Stage:

    git diff --staged

Setzt Stage auf den `HEAD`-Stand zurück:

    git restore --staged <file>

Setzt Workspace auf den Stage-Stand zurück:

    git restore <file>




---

## Tags

    git tag v0.1.7


---

### Oops - Unterbrechung!

Angefangene Änderungen kann man jeder zeit mit `stash` wegsichern.

    git stash
    git stash -u
    git stash --all
    git stash -m "bugfix started"
    git stash pop


---

### Oops - fehlerhaftes Commit


    revert  HEAD~3


---


### Oops - versehentlich geändert

Vorsicht: Dateien werden überschrieben!

Datei versehentlich geändert

    git checkout HEAD -- foo

Alte Version einer Datei wieder herstellen

    git checkout HEAD~3 -- foo

Ganzen Workspace zurücksetzen

    git stash -u

