
### Revision-Hashes

Die Versionsnummern von Git

Versionen können in Git über ihre Revision-Hashes
oder über symbolische Namen (Refs) angesprochen werden.

```bash
    # Revision Hashes
    git show f6be3b8913aa0ff3daa2be27bd55032316545545
    git show f6be3b      # es darf abgekürzt werden

    # Refs
    git show HEAD        # "aktuelle" Version
    git show master      # ein Branch
    git show v1.0.0      # ein Tag
```

---

## Das Log


---


### Der `Log`-Befehl zeigt die Commits

```bash
    $ git log --oneline

    909af6d (HEAD -> master) Fix obsolete text on page
    02d3329 fix typo in link
    38efbcb Enable offline use
    28e7071 Enable offline use
    6721664 Overwork repository chapter
    2ca78c1 Remove duplicated slides
    330fd73 Fix missing git before command
    ...
```
Erkenntnis: Das von uns geklonte Repository enthält die ganze Historie des Projekts.



---

Bis auf das Allererste haben alle Commits einen Parent.
Die Menge aller Vorfahren eines Commits, z. B. `master,` nennt man **das Log**.
Es sind also alle Commits, die zur Entstehung des aktuellen Commits beigetragen haben.

`git log master`


---


Der Log-Befeht biete zahlreiche Optionen. Hier ein paar nützliche Beispiele:

```bash
    # log zeigt die Historie
    git log HEAD
    git log HEAD -- README    # Historie einer Datei
    git log --oneline HEAD
    git log --graph HEAD

    git show HEAD~2          # vorvorletztet Commit
```

Tipp: Mit `~` kann man Vorfahren adressieren.


---

### Der Commit-Graph

Das Log kann Verzweigungen enthalten und Zusammenführungen (Merges) enthalten,
z. B. wenn mehrere Entwickler parallel gearbeitet haben.

```
* | 5c65d40 Notizen zur Wiederholung
* | 040bb7d Zeitplan für early birds hinzugefügt
|/  
* b1fae20 Fixup
* 4137535 Add some aufgaben
* 8f900ba Refactor: Split git intro 
*   351872f Merge branch 'master' 
|\  
| * c81fde8 Update index.en.md
* | 9bf4c61 Add workshop: Git basics and best practices
|/  
* 5f58070 Modify link to edit files on github
```

---

Die Option `--graph` kann dies darstellen:

```bash
    git log --graph             # Graphen darstellen
    git log --graph --oneline   #  
    git log --graph --all       #

```

---

###  Vergleichen von Commits mit Diff

Der diff-Befehl kann die Dateien (Trees) beliebiger Commits vergleichen.

```bash
    # diff vergleicht zwei Commits
    git diff HEAD~4 HEAD
    git diff 1a8a2 9f5c3 -- inhalt.md   # einzelne Datei
    git diff 1a8a2 9f5c3 --stat         # Überblick
    git diff 1a8a2 9f5c3 --word-diff    # Wortweiser Vergleich f. Texte
    git diff 1a8a2 9f5c3 -b             # Whitespace-Änderunen ausblenden
    git diff HEAD~3                     # Vergleich mit HEAD

    # externes tool nutzen
    git difftool HEAD~4 HEAD
```


---

### `git blame`: Wer war's?

Zeigt, für jede Zeile, in welchem Commit diese zuletzt bearbeitet wurde.

```
38da02ef foo (bjoern 2021-04-02 14:54:30 +0000 1) Erste Zeile
9bb1b769 foo (bjoern 2021-07-29 17:03:01 +0000 2) Zeile zwei
21c9ad44 foo (bjoern 2021-07-29 17:15:19 +0000 3) Schluss
```

 * `-M -C -C -C` Zeigt bei kopierten/verschobenen Zeilen aus anderen Dateien das "Ursprungscommit". 
 * `-w` erkennt Zeilen trotz Whitespace-Änderungen wieder.

