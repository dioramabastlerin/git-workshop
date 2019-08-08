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


 1. Git installieren
 2. Git-Hoster aufrufen, z. B. Gitlab
 3. User anlegen bzw. einloggen, falls User schon vorhanden ist.
 4. `git-workshop`-Projekt klonen.

---

 ## Setup

 ```
    git config --global user.email <Ihre-Email>
    git config --global user.name <Ihr-Name>
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

[Git Starter Kit](../git-starter-kit.html)


