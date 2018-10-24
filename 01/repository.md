# Repository

_________________________________________

## Lernziel

```
    log / show / diff / checkout
```

 * Repository
 * Workspace
 * Dezentralität, Klon
 * Revision Hashes

_________________________________________

# Zentral vs. Dezentral

_________________________________________


 * ![Zentral vs. dezentral](01/zentral-dezentral.jpg)


_________________________________________

## Zentrale Versionsverwaltungen

 * Entwickler-Workspaces enthalten nur die aktuelle Version.
 * Zentrales Repository enthält historische Informationen und verwaltet
Branches und Tags.
 * Alle Commits und Updates erfordern den Zugriff auf einen zentralen Server.

_________________________________________

## Dezentrale Versionsverwaltungen

 * Jeder Entwickler hat einen Workspace und ein vollständiges Repository
 * Commits werden nur lokaldurchgeführt.
 * Zwischen Repositories können Commits mit Pull und Push ausgetauscht
werden.
 * Einzelne Repositories können als „besonders“ definiert werden und
halten den offiziellen Stand („Blessed Repository“).


_________________________________________

## Vorteile

 * Hohe Performance
  Die meisten Operationen finden lokal auf dem Rechner des Entwicklers statt.
 * Offline Fähigkeit
   Commits, Branches, Tags können auch ohneSerververbindung durchgeführt werden.
 * Effiziente Arbeitsweisen
   Lokale Branches und Tags erleichtern den Entwickler-Alltag.
 * Automatische Backups
   Jedes Repository ist gleichzeitig auch ein Backup des gesamten Projektes, inklusive Historie.

_________________________________________

### Was ist drin, im Repository?

 * `log` 15: Zeigt die Historie, die zum aktuellen Commit geführt hat.
 * `--oneline`
     - `log -3` Ausgabe limitieren
     - `log <file>`
   - Aktuelle Version und Vorgänger: `HEAD`, `HEAD~1`, `HEAD~2`
 * `--all`, `--graph`



_________________________________________

## Commit

(Synonym: Revision)


 * **Tree** - exakter Stand aller versionierten Dateien und Verzeichnisse
 * **Metadaten**
     - Autor und Zeitpunkt der Änderung
     - Message: Beschreibung der Änderung
     - Parent(s): Vorgängerversion(en)
 * **Revision Hash** - Prüfsumme von Dateinhalten und Struktur, Autor, Zeitpunkt, Commit-Kommentar und Parent-Revision gebildet.

_________________________________________


### Historie

Die Historie die die Menge aller Vorfahren eines Commits.

`git log master` zeigt alle Commits, die zur Entstehung des aktuellen Master-Standas beigetragen haben.

Die Historie kann verzweigungen enthalten,
z. B. wenn mehrere Entwickler beteiligt waren.

`git log --graph` stellt diese Verzweigungen dar.

_________________________________________

##  Diff

Der diff-Befehl kann die Dateien (Trees) beliebiger Commits vergleichen.

    git diff 7ac0f3 2f43cd

  - `diff 1a8a2 9f5c3  -- inhalt.md`, nur eine Datei
  - `diff 1a8a2 9f5c3  --stat`, Anzahl geänderter Zeilen je Datei
  - `diff --word-diff`
  - `difftool`

_________________________________________

##  Weitere Befehle zum untersuchen der Historie

   - `show` zeigt Details zu einem Commit
      - `show 9f5c3`
      - `show 1a8a24a:protokoll.md`
   - `ls-tree` zeigt Verzeichnisinhalte im Commit-Tree

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

## Elementare Begriffe

Mitreden, wo es um Git geht
   * Repository
   * Workspace
   * Klon
   * Commit, Revision, Revision-Hash
   * Tree

