# Ein Repository klonen und untersuchen

_________________________________________



Wir klonen und untersuchen ein Git-Repository. Dabei lernen wir ein paar wichtige Begriffe und Eigenschaften von Git kennen. Außerdem hören wir ein paar Anekdoten über Linus Torvalds und die Entstehung von Git.

_________________________________________

## Lernziel

| Konzept              | Begriff              | Befehl               |
|----------------------|----------------------|----------------------|
| Dezentralität        | Klon                 | `help`               |
| Revision Hashes      | Revision             | `config`             |
|                      | Repository           | `clone`              |
|                      | Workspace            | `log`                |
|                      | Commit               | `show`               |
|                      | `master`             | `diff`               |

_________________________________________

## Intro

 * Git Entstehung 5
 * Warum Git? 4

_________________________________________

## Setup

 * Installation, Setup 11,31

```
   git config --global user.email egal
   git config --global user.name bstachmann
```
_________________________________________


## Hilfe und Infos zu Git

```
$ git
$ git --version
$ git help
$ git help log
$ git help --all
$ git help --guides
$ git help revisions
```
_________________________________________


## Zentral vs. Dezentral

 * ![Zentral vs. dezentral](abb/zentral-dezentral.jpg)
 * Warum Git? 6-8

_________________________________________

## Klonen

```
$ git clone https://<server>/<pfad>/git-training-protocol.git
```

 * `clone` 36, 39
 * `--reference` (Schneller klonen bei großen Repos)
 * Anzeigen, woher geklont wurde (`origin`)
   - `git remote -v`
_________________________________________

## Was ist drin, im Repository?

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

