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


 * ![Zentral vs. dezentral](abb/zentral-dezentral.jpg)


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

Was ist drin, im Repository?

 * `log` 15: Zeigt die Historie, die zum aktuellen Commit geführt hat.
 * `--oneline`
     - `log -3` Ausgabe limitieren
     - `log <file>`
   - Aktuelle Version und Vorgänger: `HEAD`, `HEAD~1`, `HEAD~2`
 * `--all`, `--graph`
 * *Revision Hashes* werden als Prüfsumme von Dateinhalten und Struktur, Autor, Zeitpunkt, Commit-Kommentar und Parent-Revision gebildet.

_________________________________________

Der `diff`-Befehl mit `HEAD`-Argument zeigt die Änderungen des
Workspace zum dem letzten Commit (`HEAD` ist der symbolische Name
des letzten Commit).

    git diff HEAD

Der diff-Befehl kann auch auf eine einzelne Datei angewendet werden.

    git diff HEAD foo.txt

Der diff-Befehl kann auch beliebige Commits vergleichen.

    git diff 7ac0f3 2f43cd

_________________________________________

##  Diff

   - `diff`
      - `diff 9f5c3`, vergleicht mit HEAD
      - `diff 1a8a2 9f5c3`, 2 Versionen vergleichen
      - `diff 1a8a2 9f5c3  -- inhalt.md`, nur eine Datei
      - `diff --word-diff`
   - `difftool`


_________________________________________

##  Weitere Befehle zum untersuchen der Historie

   - `show` zeigt Details zu einer Version
      - `git show 9f5c3`
      - `show 1a8a24a:protokoll.md`
   - `ls-tree`

_________________________________________

## Checkout: Repository -> Workspace

Bestimmt Version ausgewählter Dateien wieder herstellen:


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
   * Commit
   * Revision, Revision-Hash
   * `master`

