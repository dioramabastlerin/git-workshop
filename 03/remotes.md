# Remotes

_________________________________________

## Lernziel

```
  clone / remote add / remote -v / push / fetch / pull
```

 * Klonen
 * Wie funktiert die Synchronisation zwischen Repos?
 * GitHub, GitLab & Co.

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

    git remote -v

    git remote add mein-backup /backup/sample-repo.git


_________________________________________

### Ref - Zeiger auf ein Commits

Ein *Ref* ist ein Zeiger auf ein Commit, z. B. `HEAD`, `master`, jedes Tag, alle Branches.

    git show-ref --head

Der Log-Befehl zeigt die Refs idR. mit an:

    git log --decorate --oneline

    1d8425c (HEAD -> master, tag: testtag) Add content to commits chapter.
    bb00978 (origin/master) Add content to repository chapter.


_________________________________________

### Remote Refs

*Remote Refs* repräsentieren den Stand der *Refs* im anderen Repositor beim Klonen (bzw. der letzen Synchronisation).

    git show master               # lokaler Stand
    git show origin/master        # origin nach letztem Synch

_________________________________________

### Remote Refs - Ahead/Behind

*Remote Refs* ermöglichen, lokale Stände mit entfernten zu vergleichen.

    $ git status

    On branch master
    Your branch is ahead of 'origin/master' by 1 commits.

    $ git diff master origin/master

    $ git log origin/master..master   # ahead
    $ git log master..origin/master   # behind

_________________________________________

### Synchronisation

    git fetch      # remote -> lokal
    git push       # lokal -> remote

Delta-Übertragung

   1. Vergleicht Remote-Ref mit aktuellem Stand
   1. Ermittelt fehlende Commits, Trees und Blobs und überträgt diese Objekte
   1. Aktualisiert Remote-Refs

_________________________________________


Wie funktioniert die Synchronisation

![Trees and Object Store](abb/trees-and-object-storage.jpg)

_________________________________________

# Push

    git push

Oder genauer:

    git push origin master

Hinweis: `origin master` (Remote + lokaler branch) nicht `origin/master`


_________________________________________

# Pull

   git pull

 1. holt Änderungen aus dem anderen Repository (`git fetch`)
 1. und übernimmt Änderungen in den aktuellen Branch (`git merge`, nächstes Kapitel).

