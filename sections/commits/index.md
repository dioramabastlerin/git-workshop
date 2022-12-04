

Speicher speichert Versionenstände \
in **Commits** \
(auch Revisions genannt)


--- 


Ein **Commit** enthält ein

**Tree** ("Snapshot" aller Dateien des Projekts)

und **Metadaten**

 * Beschreibung (Commit-Kommentar)
 * Zeitpunkt der Änderung
 * Autor (ggf. auch Commiter)
 * **Parent(s)** - Verweis auf Vorgängerversion(en)


---

## `git show`

zeigt Informationen zu einem Commit. 

```bash
$ git show           # zeigt das aktuelle Commit

commit 612a0ee90fedcfcfce170e568ba9607f41655f0c (HEAD -> master)
Author: bjoern <kapitel26blog@gmail.com>
Date:   Thu Jul 29 00:00:00 2021 +0000

    Created file und-tschuess on branch master by bjoern.

diff --git a/und-tschuess b/und-tschuess
new file mode 100644
index 0000000..36fe753

 ...

```

Zeigt Revision-Hash, **Metadaten** (Autor, Zeitpunt, Kommentar etc.), und listet dann alle Änderungen zum Vorgänger auf (**diff**)



---

Mit `--stat` zeigt `git show` nur die Anzahl geänderterter Zeilen je Datei an,
aber nicht die Inhalte.

```bash
$ git show --stat
commit 612a0ee90fedcfcfce170e568ba9607f41655f0c (HEAD -> master)
Author: bjoern <kapitel26blog@gmail.com>
Date:   Thu Jul 29 00:00:00 2021 +0000

    Created file und-tschuess on branch master by bjoern.

 und-tschuess | 12 ++++++++++++
 1 file changed, 12 insertions(+)
```

`--oneline` verkürzt die Metadaten zu einer Zeile:

```bash
$ git show --stat --oneline
612a0ee (HEAD -> master) Created file und-tschuess on branch master by bjoern.
 und-tschuess | 12 ++++++++++++
 1 file changed, 12 insertions(+)```
```

---

**Revision Hash** - die "Versionsnummer" von Git
   Prüfsumme über alle oben angegebenen Informationen.

---

### `HEAD`

bezeichnet das aktuelle Commit,/
ist bei vielen Befehlen Defaultwert für parameter\
und kann oft weggelassen werden.

```bash
    git show HEAD                # Infos zum HEAD-Commit
    git show                     # ebenso

---

## Befehle zum Untersuchen von Commits

```bash
    git show HEAD:README         # Inhalt einer Datei
    git show --pretty=raw HEAD   # Was Git in der DB hat

    # ls-tree listet Verzeichnisse auf untersuchen
    git ls-tree -r HEAD
    git ls-tree --abbrev HEAD src/main/java
```


---

## Mit **`~`** Vorfahren adressieren

Jedes Commits (bis auf das erste) hat ein Parent-Commit.


```bash
$ git show HEAD~    # Parent von HEAD
$ git show HEAD~2   # Parent des Parent von HEAD

$ git show fb3982~2   # Parent des Parent von fb3982
```


