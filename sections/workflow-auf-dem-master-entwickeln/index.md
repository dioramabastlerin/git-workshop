# Workflow
## Auf dem `master` entwickeln

Mehrere Entwickler bearbeiten gemeinsam einen `master`-Branch.

<!-- .slide: data-background-image="workflow-auf-dem-master-entwickeln/trunk-based.png" data-background-opacity="0.4" -->

---

### Pull = Fetch + Merge

```bash
    git pull some-repo some-branch

    # Ohne Parameter: Integration mit dem upstream-Branch, z. B. origin/master
    git pull
```


Was wurde reingemerged?

    log HEAD^2..HEAD
    diff HEAD^2...HEAD


---

### Ablauf

1. Änderungen Abholen
        git pull
1. ggf. Mergekonflikte lösen
1. Entwickeln
1. Änderungen senden
       git push
1. Zurück zu Schritt 1.