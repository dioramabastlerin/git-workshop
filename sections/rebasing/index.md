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

Merge und Rebase sind enge Verwandte:

---

![zwei Branches](rebase-01.png)

---

![Mergen](rebase-02.png)

---

![Interpretation](rebase-03.png)

---

![Vorgänger vergessen](rebase-04.png)

---

![Ein rebase](rebase-05.png)

---

### Rebase Beispiel 1

![Rebasing Beispiel vorher](abb-branches-beispiel-rebase-vorher.png)


---

### Rebase Beispiel 2

![Rebasing Beispiel nachher](abb-branches-beispiel-rebase-nachher.png)


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


### Übung

<h2><a href="git-uebungen/aufgabe-zusammenarbeit-rebasing.html" target="_blank">Rebasing<a></h2>





---


## Risiken und Nebenwirkungen

### Probleme mit duplizierten Commits

### (Un-)sichtbarkeit von Integrationen


---

# Interactive Rebasing

---

Code-Reviews
============

Hat man nur ein Quality-Gate und geht sofort in Produktion, <BR>
dann sind gute Code-Reviews wichtig.

---

#### Das Problem

Bei Feature-Branches entstehen oft so viele Änderungen, dass das Review am Ende sehr komplex wird.


#### Lösungsansatz

Nicht "das Diff" am Ende des Branches reviewen<br/>
sondern jedes Commit einzeln.

---

#### Das Problem dabei

Zu viele Commits. Oft zu unstrukturiert.

#### Lösung dazu

*Interactive Rebasing* schafft eine lesbare Historie.



---


### Verwandte Befehle

 * `commit --amend`
 * `reset HEAD~1`
 * `cherry-pick`
 * `filter-branch`


