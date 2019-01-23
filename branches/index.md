
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
    git checkout new-branch
```

```
    git checkout -b new-branch
```


Notes:

Bei Verwendung von Worktree, gibt es einen aktiven Branch je Worktree.

---

    git branch --merged

    git branch -d

---

## Eigenschaften von Branches

 * beweglicher Zeiger auf Commit.
 * (max.) ein Branch ist *aktiv*
   - checkout wechselt den aktiven Bracnh
 * Beim Commit wird der aktive Branch weiter gesetzt.
 * Branches sind lokal
