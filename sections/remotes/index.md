


---

### Klonen

Mit dem clone-Befehl können Repositorys geklont werden.
Dabei werden alle Objekte (Blobs, Commits, Branches, Tags) kopiert.
Ein Workspace wird mit dem `HEAD`-Stand initialisiert.

```
$ git clone https://<server>/<pfad>/git-training-protocol.git
```

---

Warum enthält\
das geklonte Repository\
die ganze Historie des Projekts?

---

## Repository URLs

Geben an, wo ein Git-Repository liegt.

```
myrepos/sample                    # Lokaler Pfad zum Repo
myrepos/sample.git                # Lokaler Pfad zu bare Repo

ssh://ich@meinserver/sample.git   # SSH
ich@meinserver:sample.git         # auch SSH

https://ich@meinserver/sample.git # HTTPS
```


Notes:

Es gibt noch mehr Möglichkeiten.



---


### Klonen

Mit dem clone-Befehl können Repositorys geklont werden. Dabei werden alle
Objekte (Blobs, Commits, Branches, Tags) kopiert.

```
$ git clone https://<server>/<pfad>/git-training-protocol.git
```

---

### Klonen - Bare Repository

 * Bare-Repository: `clone --bare` erzeugt ein Repository ohne Workspace

---


### Klonen - Performance

 * Lokale Klone (schnell)
 * Tipp: `--reference` (Schneller klonen bei großen Repos)



---

### Übung

<h2><a href="git-uebungen/aufgabe-repository-klonen.html" target="_blank">Klonen<a></h2>

---

## Remote Repositories

Anzeigen, woher geklont wurde (`origin`)

```bash
git remote -v

git remote add mein-backup /backup/sample-repo.git
```


---

### Erinnerung: Ref - Zeiger auf ein Commit

Branches sind Zeiger auf Commits, z. B. `myfeature`, `master`


---

### Remote Refs

*Remote Refs* repräsentieren den Stand der *Refs*, insbesondere Branches,
im einem anderen Repository beim Klonen bzw. der letzen Synchronisation).

```bash
git show master               # lokaler Stand
git show origin/master        # origin nach letztem Synch
```


---

### Remote Refs - Ahead/Behind

*Remote Refs* ermöglichen, lokale Stände mit fernen Repository zu vergleichen.

```bash
    $ git status

    On branch master
    Your branch is ahead of 'origin/master' by 1 commits.

    $ git diff master origin/master

    $ git log origin/master..master   # ahead
    $ git log master..origin/master   # behind
```


---

### Demo

<h2><a href="git-uebungen/aufgabe-repository-sparse-checkout.html" target="_blank">Sparse Checkout<a></h2>



