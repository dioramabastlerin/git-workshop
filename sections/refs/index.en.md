
# Refs


---


## What is a branch really?


```
$ less .git/refs/heads/advanced-git-en 
```


---


## A branch 


 * just a pointer to a commit hash
 * when a new commit is created
   this pointer will be set to its commit hash
 * the commit itself has no knowledge of the branch
   on which it was created


---


## Ref

The concept of pointers to a node in the commit graph
is used a lot in git.

 * branches
 * remote branches
 * technical references: HEAD, MERGE_HEAD, ..*
 * tags (light weigt vs. heavy weight)


---


When Git transfers data from one repo to another,
a mapping of refs is required:

 * This happens mostly by conventions, e.g.
    `refs/heads/master <-> refs/remotes/origin/master
 * Can be specified as **refspec**
   ```$ git help push section about refspecs```
 * Basic Syntax `<src>:<dst>`
   - Prefix `+` to *force* the update
 * Refs may sometimes, but not always,
   be abbreviated, e.g. `origin/master` instead of `refs/remotes/origin/master`.


---


Hint: Use the full names, when automating things (e.g. Build-Server-Scripts)
   

---


## Reflog

```bash
$ git reflog
$ git log --walk-reflog
$ less .git/logs/refs/heads/master
```


---


## Other things

 * ` git push --delete`
 * `git push --set-upstream`
 * Mirroring
 
 