

# Ein Repository klonen und untersuchen

Wir klonen und untersuchen ein Git-Repository. Dabei lernen wir ein paar wichtige Begriffe und Eigenschaften von Git kennen. Außerdem hören wir ein paar Anekdoten über Linus Torvalds und die entstehung von Git.

_________________________________________


## Setup

 * Ggf. etwas Setup
   - Installation, Setup 11,31
 * `git --version`

_________________________________________

## Intro

 * Git Entstehung 5
 * Warum Git? 4

_________________________________________


## Hilfe und Infos zu Git

 * Kommando `git`, `git help`

_________________________________________


## Klonen

 * Setup
   - `config --global user.email egal`
   - `config --global user.name bstachmann`

 * Ein Repository klonen
   - `git clone https://<server>/<pfad>/git-training-protocol.git`
   - `clone` 36, 39
     - `--reference`
   - Anzeigen, woher geklont wurde (`origin`)
     `git remote -v`
 * Zentral vs. Dezentral 6-8
   - ![Zentral vs. dezentral](zentral-dezentral.jpg)
   -  Warum Git? 8

_________________________________________

## Was steht im Repository

 * Historie untersuchen
   - `log` 15: Zeigt die Historie, die zum aktuellen Commit geführt hat.
     - *Revision Hashes* werden als Prüfsumme von Dateinhalten und Struktur, Autor, Zeitpunkt, Commit-Kommentar und Parent-Revision gebildet.
     - `--oneline`
     - `log -3` Ausgabe limitieren
     - `log <file>`
   - Notation: `HEAD`, `HEAD~1`, `HEAD~2`
     Aktuelle Version und Vorgänger

_________________________________________

 * Weitere Befehle zum untersuchen der Historie
   - `show` zeigt Details zu einer Version
      - `git show 9f5c3`
      - `show 1a8a24a:protokoll.md`
   - `ls-tree`
   - `diff`
      - `diff 9f5c3`, vergleicht mit HEAD
      - `diff 1a8a2 9f5c3`, 2 Versionen vergleichen
      - `diff 1a8a2 9f5c3  -- inhalt.md`, nur eine Datei
      - `diff --word-diff`
   - `checkout` von Revisionen 22 (26,38)

_________________________________________

## Elementare Begriffe

Mitreden, wo es um Git geht
   * Repository
   * Workspace
   * Klon
   * Commit
   * Revision, Revision-Hash
   * `master`

