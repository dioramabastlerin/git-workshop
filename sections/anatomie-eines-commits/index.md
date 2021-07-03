# Anatomie eines Commits

---


```bash
$ git init
$ vim hello
$ git add hello
$ git commit -m "A minimal commit"
```

Wie speichert Git Commits?

---

```
    git show HEAD
    git show HEAD:hello         

    git ls-tree -r HEAD
```


---


### Alles hat einen (SHA1-) Hash!


---


## Git hat eine Datenbank:


## Den Object Store

---


Das Herz von Git ist der sogenannte **Object Store**,
eine Datenbank, in der

* Inhalte von Dateien (**Blob**)
* Verzeichnisse (**Tree**)\
  Auflistungen von Dateien
* **Commits**\
  mitsamt Metadaten

gespeichert werden.


---


### Inspect the Object Store

* `.git/objects`
* Schlüssel sind SHA1-Hashes
* Inhalte zlib-komprimiert
  ```bash
   $ cat 752c104f5f515c0f3b93bd21351f9e1add7e6a | pigz -d
  ```
* Git Plumbing-Kommandos:

   ```bash
   $ git cat-file -t HEAD   # type
   $ git cat-file -s HEAD   # size
   $ git cat-file -p HEAD   # print
   ```

---

### Wichtige Objekttypen

* `blob`
* `tree`
* `commit`


Identische Inhalte werden nur einmal abgelegt.


---


### In den Object Store schreiben

```bash
$ echo 'test content' | git hash-object -w --stdin
``` 

---



![Commit Trees](commits-im-object-store.svg)

[Download](commits-im-object-store.svg)

---


![Commit Trees](commits-im-object-store.png)


---

Was genau ist in einem Commit enthalten?

    git log --pretty=raw

Insbesondere sind die (Posix) Permissions enthalten, nicht aber die Timestamps.



