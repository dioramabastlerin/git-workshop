

## Commits und Revision-Hashes


---


Das Git-Repository speichert Versionen (auch Revisions genannt) des Projekts
in Form von *Commits*. Jedes Commit wiederum hat

 * **Tree** - "Snapshot" aller Dateien und Verzeichnisse zu eine Zeitpunkt
 * **Metadaten** - Zeitpunkt der Änderung, Autor und Beschreibung der Änderung
 * **Parent(s)** - Vorgängerversion(en)
 * **Revision Hash** - die "Versionsnummer" von Git
   Prüfsumme über alle oben angegebenen Informationen.

---

### `HEAD`

bezeichnet das aktuelle Commit,/
ist bei vielen Befehlen Default-Wert\
und kann oft weggelassen werden.

---

## Befehle zum Untersuchen von Commits

```bash
    # show zeigt detaillierte Informationen zu Commits
    git show HEAD                # Infos zum HEAD-Commit
    git show                     # ebenso
    git show HEAD:README         # Inhalt einer Datei
    git show --pretty=raw HEAD   # Was Git in der DB hat

    # ls-tree listet Verzeichnisse auf untersuchen
    git ls-tree -r HEAD
    git ls-tree --abbrev HEAD src/main/java
```

---

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

