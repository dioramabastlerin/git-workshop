### Lernziel (Befehle)

```bash
    # Branches erstellen
    git branch my-new-branch

    # Branch wechseln
    git switch old-branch
    git switch -c my-new-branch

```

---


**Branches** ermöglichen es, in **einem Repo**,

**parallele Entwicklungsstränge** zu pflegen,

zwischen denen man frei

**hin und her zu wechseln** kann.

Notes:

Genauer: nur einem Klon eines Repos.

Man auch mehrere Entwicklungsstränge öffnen,
indem man in verschieden Verzeichnissen Klone anlegt.
Dann liegt die Verwaltung der Stränge
außerhalb von Git.
Man muss sich merken, welcher Strang wo abgelegt ist.
Bei Branches gibt man den Strängen Namen und kann sie,
auflisten, vergleichen und administrieren.


---

### Branches sind Zeiger auf Commits

![Branch vor dem Commit](abb-branches-beispiel-vorher.png)

#### Ein Branch ist aktiv (hier `release-1.0`)

---

#### Neue Commits gehen auf den aktiven Branch

![Branch nach dem Commit](abb-branches-beispiel-nachher.png)


---

### Branch anlegen

```bash
    # Ein Branch ist ein Zeiger auf ein Commit
    git branch new-branch2 38a8efc72

    # Gibt man nichts an, wird HEAD genommen
    git branch new-branch
```

### Branches zeigen

```bash
    git branch -vv
```


Notes:

`-v`, `-vv` sorgen dafür, dass mehr Details ausgegeben werden.


---

## Branch wechseln

Jedes Repo hat einen **aktiven Branch**.

```
    git switch new-branch
```

```
    git switch -c new-branch
```

Vearaltet, aber weiter nutzbar, `git checkout`

```
    git checkout -b new-branch
```


Notes:

Bei Verwendung von Worktree, gibt es einen aktiven Branch je Worktree.

---

## Eigenschaften von Branches

 * beweglicher Zeiger auf Commit.
 * (max.) ein Branch ist *aktiv*
   - checkout wechselt den aktiven Bracnh
 * Beim Commit wird der aktive Branch weiter gesetzt.
 * Branches sind lokal

---

### `log` und `diff` beim Branching

Diese Befehle erlauben *asymmetrische* Vergleiche:

Was haben wir geändert? Was die Anderen?

```bash
$ # Was haben DIE geändert? 
$ git log <unser-branch>..<deren-branch>    
$ git diff <unser-branch>...<deren-branch>    

$ # Was haben WIR geändert? 
$ git log <deren-branch>..<unser-branch>    
$ git diff <deren-branch>...<unser-branch>   
```

---

### Befehl: `git merge`

Zusammenführen von Änderungen.

```bash
$ git merge <other-branch>
```

integriert die Änderungen von `other-branch` in den aktiven Branch und erstellt ein neues Commit dazu.

*Anmerkung:* Zum Umgang mit Konflikten und Sonderfällen folgt später ein separates Kapiteil über *Merges*.

---

### Übung

<h2><a href="markdown-git-uebungen/aufgabe-zusammenarbeit-branching.html" target="_blank">Branches<a></h2>


