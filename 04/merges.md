# Merges

_________________________________________


## Lernziele

```bash
  merge | mergetool | HEAD | MERGE_HEAD
  log --all --graph | log branchA..branchB
```

 * `master` vs. `origin/master`
 * Commit-Graph
 * 3-Wege-Merge
 * Mergekonflikte
 * Fast-Forwards

_________________________________________

### Wie entstehen Verzweigungen im Commit-Graphen

 1. Zwei Entwickler A und B klonen ein Repository
 1. Beide erstellen ein Commit
    (mit dem selben Vorgänger)
 1. B pushed zuerst (und gewinnt!)
 1. A versucht zu pushen, aber ...


_________________________________________

### Push rejected

Grundregel:

> Beim Push muss das alte Commit Vorfahr des neuen sein!

Anders ausgedrückt: Es dürfen keine Commits in der Historie verloren gehen. Alle Commits, die vorher da waren, müssen auch in der neuen Historie enthalten sein.

-> Der Konflikt muss jetzt lokal aufgelöst werden!

_________________________________________

### Merge

    git fetch

    git merge origin/master

### Pull = Fetch + Merge

    git pull

_________________________________________

### Wie funktioniert ein Merge

3-Wege-Merge

![Push, Pull and Merge Conflicts](push-pull-merge.jpg)

_________________________________________

### Eigenschaften des Merge

Aus dem Merge entsteht idR. ein Commit:

 * Commit hat 2 Parents (mind.)
 * 3-Wege-Merge
   * Alle Änderungen seit dem Common-Ancestor werden zusammengeführt
   * Textabschnitte (Hunks) werden hinzugefügt, geändert oder gelöschtt.
   * Keinerlei Garantie, dass Änderungen zusammenpassen!
 * Das Merge Commit kann frei bearbeitet werden (`--no-commit`, dann manuelles commit)


_________________________________________

### Merge - Fast Forward


Wenn sich auf einer Seite des Merges nichts getan hat, macht Git idR. ein *fast-forward*:

![Fast-Forward](fast-forward.jpg)

    git merge --no-ff

    git merge --ff-only

 `fast-forward` 34


_________________________________________

### Merge - Konflikt

       - `config --global merge.conflictStyle diff3`
       - Konflikte 41
       - `checkout` 38
       - `--ours`, `--theirs` 32
       - `merge --abort`
       - `mergetool` 32

`merge` 29,30,31,43,44

_________________________________________

     * Techniken, die helfen, Probleme mit
Merges zu mildern:
       ![Merges mildern](abb/merges-mildern.jpg)
     * Wenn man einem Merge per `revert` rückgängig macht,
       muss man dieses später mit einem weiteren `revert` rückgängig machen,
       um die Änderungen zu reaktivieren:
       ![Reverting Merges](abb/reverting-merges.jpg)
     * [Merges und  widersprüchliche Umbenennungen](renames-und-merges.md)

       - `diff HEAD^1`
       - `diff HEAD^2`


_________________________________________

# Pull
   - `pull` 44




