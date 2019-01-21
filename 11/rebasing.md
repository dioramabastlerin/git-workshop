# Rebasing

---

## Lernziele

```
    rebase / rebase --interactive
```

 * Rebasing **dupliziert Commits**
 * **Risiken** und Nebenwirkungen
 * **Interaktives Rebasing**

---

### Rebase und Merge sind verwandt

![Rebasing und Merging](rebasing-and-merging.png)

---

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

---

### Rebase - Konflikte

 * theirs vs. ours

---


## Risiken und Nebenwirkungen

### Probleme mit duplizierten Commits

### (Un-)sichtbarkeit von Integrationen

---


### Verwandte Befehle

 * `commit --amend`
 * `reset HEAD~1`
 * `cherry-pick`
 * `filter-branch`


---


### Anwendung auf **Workflows**

---


![Feature Branching mit Rebasing](feature-branching-with-rebasing.png)
