# Branches

_________________________________________


## Lernziele

```
  branch / checkout -b
```

 * Wechseln zwischen Branches
 * Repräsentation Branches zeigen auf Commits
 * Remote Branches
 * Upstream Branches

_________________________________________

## Branch


 * beweglicher Zeiger auf Commit.
 * (max.) ein Branch ist *aktiv*
   - checkout wechselt den aktiven Bracnh
 * Beim Commit wird der aktive Branch weiter gesetzt.
 * Branches sind lokal

_________________________________________


    git branch

    git branch new-branch

    git checkout new-branch

    git checkout -b new-branch

_________________________________________

    git branch --merged

    git branch -d

_________________________________________

## Branch

 * Branches sind lokal, werden nicht direkt übertragen
 * Remote Tracking Branches sind stellvertreter
    git branch -r
 * Pull holt Änderungen von einem Remote Branch in den aktiven Branch
 * Push überschreibt einen Branch in einem andern Repository
   (sofern erlaubt).


_________________________________________

## Branch

   git pull other-repo other-branch
   git push other-repo other-branch

Wenn ein *upstream* konfiguriert ist, kann man die Parameter weglassen

   git branch -vv
   git pull
   git push

   git push --set-upstream origin new-branch

