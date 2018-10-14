# Rebasing

_________________________________________

## Lernziele

```
    rebase / rebase --interactive
```

 * Rebasing **dupliziert Commits**
 * **Risiken** und Nebenwirkungen
 * **Interaktives Rebasing**

_________________________________________

### Rebase und Merge sind verwandt

![Rebasing und Merging](11/rebasing-and-merging.png)

_________________________________________

### Rebase - Anwendung

    git rebase newbase

Welche Commits? Wohin?

 * Die neuen Commits entstehen auf `newbase`
 * Dupliziert werden
   - HEAD..newbase
   - Alle Commits in HEAD, die noch
     nicht in newbase enthalten sind

Ohne Parameter: Upstream Branch.

    git rebase

_________________________________________

### Rebase - Konflikte

 * theirs vs. ours

_________________________________________


## Risiken und Nebenwirkungen

### Probleme mit duplizierten Commits

### (Un-)sichtbarkeit von Integrationen

_________________________________________


### Verwandte Befehle

 * `commit --amend`
 * `reset HEAD~1`
 * `cherry-pick`
 * `filter-branch`


_________________________________________


### Anwendung auf **Workflows**

_________________________________________


![Feature Branching mit Rebasing](11/feature-branching-with-rebasing.png)
