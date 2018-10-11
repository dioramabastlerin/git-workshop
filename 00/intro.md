## Workshop

# Git

<font size="4">
<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="00/cc-by-nc-sa-4.0.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">Diese Unterlagen zum Git-Workshop</span> von <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/kapitel26/kapitel26.github.com" property="cc:attributionName" rel="cc:attributionURL">Bjørn Stachmann und René Preißel</a> sind <a href="LICENSE">lizenziert</a> unter einer <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Namensnennung - Nicht-kommerziell - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
</font>

<a href="/?print-pdf">Print</a>

<a href="/?print-pdf&showNotes=true">Print with speaker notes</a>

_________________________________________

### Inhalte

 * Stichworte für den Trainer
 * Whiteboardskizzen
 * Beispiele für typische Git-Kommandoaufrufe

Dies ist jedoch keine durchängig lesbase Anleitung für Git, ...
_________________________________________

... eine solche finden Sie zum Beispiel in unserem Buch.

<img src="00/git-buch.png" width="20%"/>

Weitere Informationen finden Sie in unserem Blog [Kapitel 26](http://kapitel26.github.io).

_________________________________________

## Zeiten

| 1. Tag               | 2. Tag               |
|----------------------|----------------------|
|  9:00 - 10:15        |  9:00 - 10:15        |
| 10:30 - 12:00        | 10:30 - 12:00        |
|     Mittag           |     Mittag           |
| 13:00 - 14:15        | 13:00 - 14:15        |
| 14:30 - 15:45        | 14:30 - 16:30        |
| 16:00 - 17:00        |                      |
| 17:00 - 17:30 *      |                      |

_________________________________________


# Inhalte

(ESC-Taste drücken)

_________________________________________


# Setup

_________________________________________

## Lernziel für diesen Abschnitt

```
    config / help
```

 * Wissen, wie Git entstanden ist
 * Wichtige Eigenschaften von Git kennen
   Dezentral, Performant, Robust, Validierbar, Open-Source + breites Tooling
 * Setup: Git installieren, konfigurieren und Hilfe finden

_________________________________________


> Subversion used to say it is CVS done right:
> with that slogan there is nowhere you can go.
>
> There is no way to do CVS right.

(Linus Torvalds, Mai 2007)


_________________________________________


Initiale Entwicklung durch Linus Torvalds (ab 2005)

Versionsverwaltung für die Linux Kernel Entwicklung

 * Viele Entwickler
 * Viele Patches
 * Parallele Entwicklung

_________________________________________


## Warum Git?

  * Robustes und einfaches Branching und Merging
  * Dezentraler Ansatz
  * Performant, auch bei sehr vielen Quelltexten
  * Sehr flexiblen Workflow
  * Open-Source
  * Easy to Contribute
  * Quasi-Standard: Viele Projekte und Unternehmen nutzen Git

_________________________________________

## Setup

 1. Git installieren
 2. Git-Hoster aufrufen, z. B. Gitlab
 3. User anlegen bzw. einloggen, falls User schon vorhanden ist.
 4. `git-workshop`-Projekt klonen.

_________________________________________

 ## Setup

 ```
    config --global user.email <Ihre-Email>
    config --global user.name <Ihr-Name>
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




