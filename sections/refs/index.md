
## Branches, Tags und HEAD

---

### Ref - Ein Name für ein Commit

Ein *Ref* ist ein Zeiger auf ein Commit.
Der Log-Befehl zeigt die Refs idR. mit an, d. h. `--decorate` kann weggelassen werden.

```bash
git log --decorate --oneline

1d8425c (HEAD -> master, tag: testtag) Add content to commits chapter.
bb00978 (origin/master) Add content to repository chapter.
```

---

### Beispiele für Refs

 * `HEAD`
 * `master` (Branch)
 * `feature-a` (Branch)
 * `v1.0.0` (Branch)

Mit der Option `--all` zeig `log` nicht nur die Historie des `HEAD`,
sonder aller Tags und Branches.

```bash
    git log --all --graph
```

---

## Branches und Tags anzeigen

```bash
git branch

git tag
```

---

### `git switch`
 

 * `HEAD` wird auf eine Zielversion gesetzt
 * Alle (versionierten) Dateien im Workspace werden auf den Stand gebracht.
 * `git switch <branch>` wechselt auf einen Branch. `<branch>` ist dann aktiver Branch
 * `git switch --detach <commit>` wechselt auf beliebiege Versionen. Danach ist kein Branch aktiv.
 * *uncommitted Changes* werden mitgenommen


---


### (veraltet)Checkout
 

Der Befehl `checkout`kann sowohl Branches wechseln Versionen wechseln, als auch Datei- und Versionsinhalte austauschen.
Leichter verständlich und weniger fehleranfällig sind jedoch die neueren
Befehle `switch` und `restore`.

