
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




