## `git diff`

Vergleicht die *Trees* (Datei- und Verzeichnisbäume) zweier Commits,
und zeigt, welche unterschiede es gibt.

```bash
$ git diff <commit1> <commit2>           
```

Gibt man nur ein Commit an, wird mit dem Workspace verglichen.

```bash
$ git diff <commit>             
```

Ohne Commit wird der Workspace mit `HEAD` verglichen.
Lokale Änderungen werden gezeigt.

```bash
$ git diff
```


---


### `git-diff` Ausgabeformat

Exakte Beschreibung der Unterschiede. Maschinenlesbar.
Menschenlesbar (einigermaßen).

```diff
diff --git a/average.kts b/average.kts
index 4cd02bf..7eb87f2 100644
--- a/average.kts
+++ b/average.kts
@@ -1,6 +1,6 @@
 if(args.isEmpty())
     throw RuntimeException("No arguments given!")
 
-val s = args.map{ it.toInt() }.sum()
+val summe = args.map{ it.toInt() }.sum()
 
-println("The average is ${s/args.size}")
+println("The average is ${summe/args.size}")
```

---


### Alternative diff-Formate

* Mit `--word-diff` werden Änderungen an Texten klarer dargestellt.

* Mit `--stat` werden nicht die Inhalte der Änderungen sondern nur die Anzahl geänderter Zeilen gezeigt.

* Mit `git difftool` kann man exteren Diff-Tools (z.B. `kdiff3`) öffnen.


