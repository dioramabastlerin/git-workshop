
### Die Git Kommandozeile


---

## Setup


1. Git [installieren](https://git-scm.com/downloads)
2. Git-Hoster aufrufen, z. B. Gitlab
3. User anlegen bzw. einloggen, falls User schon vorhanden ist.
4. `git-workshop`-Projekt klonen.
   [GitHub](https://github.com/bstachmann/git-workshop) oder
   [GitLab](https://gitlab.com/bjoern.stachmann/git-workshop)


---


 ## Setup

```bash
    $ git config --global user.email <Ihre-Email>
    $ git config --global user.name <Ihr-Name>
```

---


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


Bei GitHub findet man ein [schönes Cheatsheet](https://training.github.com/downloads/de/github-git-cheat-sheet/) zu den wichtigsten Git-Befehlen.

[Git Starter Kit](../../git-starter-kit.md) zeigt (fast) alle Befehle, die in den Übungen genutz werden.



---

## Editor einrichten

```bash
$ git config --global core.editor vim
$ git config --global core.editor nano
$ git config --global core.editor notepad 
$ git config --global core.editor "atom --wait"
$ git config --global core.editor "\"c:\Program Files\Sublime Text 3\subl.exe\" -w -n"
$ git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
```

