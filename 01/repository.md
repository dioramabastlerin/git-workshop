# Repository

_________________________________________

## Lernziel

```
    log / show / diff
```

 * Repository
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
 * *Revision Hashes* werden als Prüfsumme von Dateinhalten und Struktur, Autor, Zeitpunkt, Commit-Kommentar und Parent-Revision gebildet.

_________________________________________

##  Weitere Befehle zum untersuchen der Historie

   - `show` zeigt Details zu einer Version
      - `git show 9f5c3`
      - `show 1a8a24a:protokoll.md`
   - `ls-tree`
   - `checkout` von Revisionen 22 (26,38)
_________________________________________

##  Weitere Befehle zum Untersuchen der Historie

   - `diff`
      - `diff 9f5c3`, vergleicht mit HEAD
      - `diff 1a8a2 9f5c3`, 2 Versionen vergleichen
      - `diff 1a8a2 9f5c3  -- inhalt.md`, nur eine Datei
      - `diff --word-diff`
   - `difftool`

_________________________________________

## Elementare Begriffe

Mitreden, wo es um Git geht
   * Repository
   * Workspace
   * Klon
   * Commit
   * Revision, Revision-Hash
   * `master`

