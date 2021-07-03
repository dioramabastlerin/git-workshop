
## Branch

 * Branches sind lokal, werden nicht direkt übertragen
 * Remote Tracking Branches sind Stellvertreter
    git branch -r
 * Pull holt Änderungen von einem Remote Branch in den aktiven Branch
 * Push überschreibt einen Branch in einem andern Repository
   (sofern erlaubt).


---

## Branch

```bash
git pull other-repo other-branch
git push other-repo other-branch
```

Wenn ein *upstream* konfiguriert ist, kann man die Parameter weglassen

```bash
git push --set-upstream origin new-branch

git pull
git push
```


