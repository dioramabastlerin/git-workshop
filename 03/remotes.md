# Remotes

_________________________________________

## Lernziel


 * Wie klone ich Repositorys?
 * Repository-URLs und der `origin`
 * `push` und `pull` oder wie synchronisiert man Repos?
 * Hosting: GitHub, GitLab & Co.


_________________________________________

### Lernziel (Befehle)



```bash
    # Commits erstellen
    git clone <repo-url>

    # Remote-Adressen verwalten
    git remote add <name> <repo-url>
    git remote -v

    # Synchronisieren
    git fetch
    git pull
    git push
```

_________________________________________

## Repository URLs

Geben an, wo ein Git-Repository liegt.

```
myrepos/sample                    # Lokaler Pfad zum Repo
myrepos/sample.git                # Lokaler Pfad zu bare Repo

ssh://ich@meinserver/sample.git   # SSH
ich@meinserver:sample.git         # auch SSH

https://ich@meinserver/sample.git # SSH
```


Notes:

Es gibt noch mehr Möglichkeiten.



_________________________________________


### Klonen

Mit dem clone-Befehl können Repository geklont werden. Dabei werden alle
Objekte (Blobs, Commits, Branches, Tags) kopiert.

```
$ git clone https://<server>/<pfad>/git-training-protocol.git
```

_________________________________________


### Klonen - Bare Repository

 * Bare-Repository: `clone --bare` erzeugt ein Repository ohne Workspace

_________________________________________


### Klonen - Performance

 * Lokale Klone (schnell)
 * Tipp: `--reference` (Schneller klonen bei großen Repos)



_________________________________________

## Remote Repositories


Anzeigen, woher geklont wurde (`origin`)

```bash
git remote -v

git remote add mein-backup /backup/sample-repo.git
```


_________________________________________

### Erinnerung: Ref - Zeiger auf ein Commit

Branches sind Zeiger auf Commits, z. B. `myfeature`, `master`


_________________________________________

### Remote Refs

*Remote Refs* repräsentieren den Stand der *Refs*, insbesondere Branches,
im einem anderen Repository beim Klonen bzw. der letzen Synchronisation).

```bash
git show master               # lokaler Stand
git show origin/master        # origin nach letztem Synch
```


_________________________________________

### Remote Refs - Ahead/Behind

*Remote Refs* ermöglichen, lokale Stände mit entfernten zu vergleichen.

```bash
    $ git status

    On branch master
    Your branch is ahead of 'origin/master' by 1 commits.

    $ git diff master origin/master

    $ git log origin/master..master   # ahead
    $ git log master..origin/master   # behind
```


_________________________________________


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


_________________________________________


Wie funktioniert die Synchronisation?

![Trees and Object Store](03/trees-and-object-storage.jpg)

_________________________________________

### Push

```bash
git push
```

Oder genauer:

```bash
git push origin master
```

Achtung: `origin master` (Remote + lokaler branch) nicht `origin/master`


_________________________________________

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

_________________________________________

### Pull

Ein Kombi-Befehl

```bash
git pull
```

 1. Änderungen holen (`git fetch`)
 1. Änderungen im aktuellen Branch übernehmen \
    (`git merge`, nächstes Kapitel).

