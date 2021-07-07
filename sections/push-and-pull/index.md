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


---


Nach dem Klone ist alles gleich.

![After Clone](repo-push-1.png)


---

Neue Commits sollen per `push` übertragen werden.

![Before push](repo-push-2.png)


---

Commits wurden übertragen und Refs aktualisiert.

![After push](repo-push-3.png)


---

### Push

#### Übertragt vom *aktiven Branch* zum Remote Repository.

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

#### Holt *alle Branches* vom Remote Repository.

Synchronisiert und aktualisiert Remote-Refs.

Workspace und aktiver Branch bleiben unverändert.

```bash
    git fetch
    git fetch origin
```


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

### Übung

<h2><a href="git-uebungen/aufgabe-zusammenarbeit-push-fetch-pull.html" target="_blank">Übung push, fetch und pull<a></h2>


---

### Push rejected

---


![Push reject 1](push-reject-1.png)

[Push Reject](repo-push-rejected.svg)


--- 


![Push reject 2](push-reject-2.png)


--- 

### Push rejected


> Grundregel: Nie Historie vernichten!

Alle Commits, die vorher in der Historie des Branches waren, müssen es nachher auch noch sein.

Technisch: Beim Push muss das neue Commit Nachfahre des Vorherigen sein!

-> Der Konflikt muss jetzt lokal aufgelöst werden!


---


### Übung

<h2><a href="git-uebungen/aufgabe-zusammenarbeit-push-rejected.html" target="_blank">Push rejected!<a></h2>



