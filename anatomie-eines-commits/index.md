# Anatomie eines Commits


---

Das Herz von Git ist der sogenannte **Object Store**,
eine Datenbank, in der 
 
 * Inhalte von Dateien (**Blob**)
 * Verzeichnisse (**Tree**)\
   Auflistungen von Dateien
 * **Commits**\
   mitsamt Metadaten
   
gespeichert werden.


---


![Commit Trees](commits-im-object-store.svg)

[Download](commits-im-object-store.svg)

---


![Commit Trees](commits-im-object-store.png)


---

Was genau ist in einem Commit enthalten?

    git log --pretty=raw

Insbesondere sind die (Posix) Permissions enthalten, nicht aber die Timestamps.


---

## Verzeichnisse

Verzeichnisse werden in Git nicht explizit versioniert.

Ein Verzeichnis muss mindestens eine Datei enthalten.

Ggf. legt man ein hidden File an, z. B. `.gitkeep`


---

### Übung: Commits erstellen

Starten sie im *Übungsverzeichnis* (wo sie das Zip-Archiv mit den
Übungen entpackt haben).
Öffnen sie die Anleitung im *Browser* (mit dem Kommando `start` auf
Windows, `xdg-open` auf Ubuntu,`open` auf MacOs).
**Achtung!** Es ist wichtig, die Übungen im *angegebenen
Startverzeichnis* zu beginnen. Achten Sie auf die Beschreibung:

    $ cd git-uebungen-<Zeitstempel z. B. 202005252000>
    $ start aufgaben/XX-commits-erstellen/index.html 
    $ cd aufgaben/<angegebenes Startverzeichnis>

Folgen Sie dann den weiteren Anweisungen.


---


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

