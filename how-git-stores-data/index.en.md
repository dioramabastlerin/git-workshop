# Object Storage and the commit graph

---

## Goals

How does Git work?

 * What is in a commit?
 * How does git store objects?
 * How does it handle redundancy?
 * A graph of commits?


---


## Just a minimal commit

```bash
$ git init
$ vim hello
$ git add hello
$ git commit -m "A minimal commit"
```


---


## Let's have a closer look

```
    git show HEAD
    git show HEAD:hello         

    git ls-tree -r HEAD
    git ls-tree --abbrev HEAD src/main/java
```


--- 


### Everything has a hash!


---


### Inspect the Object Store

 * `.git/objects`
 * Content is compressed wit zlib
 
   ```bash
    $ cat 752c104f5f515c0f3b93bd21351f9e1add7e6a | pigz -d
   ```
 * Git plumbing command for reading the object store:
    
    ```bash
    $ git cat-file -t HEAD   # type
    $ git cat-file -s HEAD   # size
    $ git cat-file -p HEAD   # print
    ``` 


---


### Add Another Commit


---


### Add a Merge


---


### The commit graph

 * Logging
 * First-Parent-History
 * Simplify-by-decoration


---


### Duplicate the file


---


### Identical content will be stored only once


---


### Add a Directory


---


### Important Types of Objects

 * `blob`
 * `tree`
 * `commit`


---


### Writing to the object store

```bash
$ echo 'test content' | git hash-object -w --stdin
``` 

---

## A problem

 1. Take large file
 1. Add a line
 1. Commt
 1. Repeat often
 
-> You get a huge repository  

---

 
### Pack Files

 * https://git-scm.com/book/en/v2/Git-Internals-Packfiles
 * https://git-scm.com/docs/pack-format


---


### Git Garbage Collection
