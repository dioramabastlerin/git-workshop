# Renames, Moves and Copies

---

## Rename detection

The data model of git
knows nothing about renames ...

---

... but git will find them if you want:

```bash
    git log --find-renames=90% -- some-files
    git log -M -- some-file
    git log -M99% --some-file
    
    git log --find-copies=90% -- some-files
    git log -C -- some-files
    git log -C --find-copies-harder
    
    git annotate -C -M some-file
    git merge
```    
    
