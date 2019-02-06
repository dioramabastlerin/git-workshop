## Workshop

# Git


von René Preißel und Bjørn Stachmann

---



<font size="4">
  <a href="/?print-pdf">Print</a> / <a href="/?print-pdf&showNotes=true">Print with speaker notes
  </a>
</font>
<p/>
<font size="4">
  <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img   alt="Creative Commons Lizenzvertrag" style="border-width:0" src="00/cc-by-nc-nd.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Namensnennung - Nicht kommerziell - Keine Bearbeitungen 4.0 International Lizenz
  </a>.
</font>

---


### Inhalte

 * Stichworte für den Trainer
 * Whiteboardskizzen
 * Beispiele für typische Git-Kommandoaufrufe

Dies ist jedoch keine durchängig lesbare Anleitung für Git, ...

---

... eine solche finden Sie zum Beispiel in unserem Buch.

<img src="git-buch.png" width="20%"/>

Weitere Informationen finden Sie in unserem Blog [Kapitel 26](http://kapitel26.github.io).

---

## Zeiten

| 1. Tag               | 2. Tag               |
|----------------------|----------------------|
|  8:30 - 9:30         |  8:30 - 9:30         |
|  9:45 - 10:45        |  9:45 - 10:45        |
| 11:00 - 12:00        | 11:00 - 12:00        |
|     Mittag           |     Mittag           |
| 13:00 - 14:15        | 13:00 - 14:15        |
| 14:30 - 15:45        | 14:30 - 16:00        |
| 16:00 - 17:00        |                      |

---


# Inhalte

(ESC-Taste drücken)

---


# Setup

---

## Lernziel für diesen Abschnitt

```
    config / help
```

 * Wissen, wie Git entstanden ist
 * Wichtige Eigenschaften von Git kennen:

   Dezentral, Performant, Robust, Validierbar, Open-Source + breites Tooling
 * Setup: Git installieren, konfigurieren und Hilfe finden

---


> Subversion used to say it is CVS done right:
> with that slogan there is nowhere you can go.
>
> There is no way to do CVS right.

(Linus Torvalds, Mai 2007)


---


Initiale Entwicklung durch Linus Torvalds (ab 2005)

Versionsverwaltung für die Linux Kernel Entwicklung

 * Viele Entwickler
 * Viele Patches
 * Parallele Entwicklung

---


## Warum Git?

  * Robustes und einfaches Branching und Merging
  * Dezentraler Ansatz
  * Performant, auch bei sehr vielen Quelltexten
  * Sehr flexibler Workflow
  * Open-Source
  * Easy to Contribute
  * Quasi-Standard: Viele Projekte und Unternehmen nutzen Git

---

## Setup


 1. Git icd nstallieren
 2. Git-Hoster aufrufen, z. B. Gitlab
 3. User anlegen bzw. einloggen, falls User schon vorhanden ist.
 4. `git-workshop`-Projekt klonen.

---

 ## Setup

 ```
    config --global user.email <Ihre-Email>
    config --global user.name <Ihr-Name>
 ```

### Credential Helper

[Git Credential Helper](https://kapitel26.github.io/git/2012/12/03/Passwoerter-verwalten.html)

---


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

---

# Git Starter

[Git Starter Kit](/git-starter-kit.html)


