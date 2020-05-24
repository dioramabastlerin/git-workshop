# Push & Pull


---

## Lernziel

 * `push` und `pull` oder wie synchronisiert man Repos?
 * Hosting: GitHub, GitLab & Co.


---

### Lernziel (Befehle)

```bash
    git fetch
    git pull
    git push
```


---

### Synchronisation

```bash
    git fetch      # remote -> lokal
    git push       # lokal -> remote
```


Delta-Übertragung

   1. Vergleicht Remote-Ref mit aktuellem Stand
   1. Ermittelt fehlende Commits, Trees und Blobs und überträgt diese Objekte
   1. Aktualisiert Remote-Refs

Notes:

Man kann auch mit unrelated Repos synchen.


---


Wie funktioniert die Synchronisation?

![Trees and Object Store](trees-and-object-storage.jpg)

---

### Push

```bash
git push
```

Oder genauer:

```bash
git push origin master
```

Achtung: `origin master` (Remote + lokaler branch) nicht `origin/master`

---

### Fetch

Synchronisiert und aktualisiert die Remote-Refs.

```bash
    git fetch
```

Oder genauer:

```bash
    git fetch origin
```

Berührt weder Workspace, noch den aktiven Branch.

---

### Pull

Ein Kombi-Befehl

```bash
git pull
```

 1. Änderungen holen (`git fetch`)
 1. Änderungen im aktuellen Branch übernehmen \
    (`git merge`, nächstes Kapitel).

---


## Übung

    *-klone-push-fetch-und-pull.aufgaben


---


### Push rejected

> Grundregel: Nie Historie vernichten!

Alle Commits, die vorher in der Historie des Branches waren, müssen es nachher auch noch sein.

Technisch: Beim Push muss das neue Commit Nachfahre des Vorherigen sein!

-> Der Konflikt muss jetzt lokal aufgelöst werden!


---


## Übung

    *-klone-push-rejected.aufgaben
