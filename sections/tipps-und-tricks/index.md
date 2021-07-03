# Tipps & Tricks


---


## Lernziele

```
    add / reset
```

 * Mit `.gitignore` Dateien ausblendeb
 * Staging bzw. "der Index"
 * Trouble Shooting.
   Verschiedene Szenarien.


---

## Dateien unversioniert lassen
   - `.gitignore` 21


---

## Staging

   - `diff --staged`
   - `reset`
   - Selektives Staging (Hunks)

---

## Commits nachträglich reparieren

```bash
# Commit-Kommentar ändern
git commit --amend

# Vergessene Datei hinzufühgen
git add vergessen
git commit --amend

# Das Commit noch mal versuchen
git reset HEAD~1
```

---

## Sonstiges

   - `reset --hard HEAD`
   - `reflog` 27
     - `log --walk-reflogs`

