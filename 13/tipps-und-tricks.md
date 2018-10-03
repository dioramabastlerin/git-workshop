# Tipps & Tricks

## Lernziele

```
    add / reset
```

 * Mit `.gitignore` Dateien ausblendeb
 * Staging bzw. "der Index"
 * Trouble Shooting.
   Verschiedene Szenarien.


_________________________________________

## Dateien unversioniert lassen
   - `.gitignore` 21
_________________________________________

## Staging

   - `diff --staged`
   - `reset`
   - Selektives Staging (Hunks)

_________________________________________

## Oops
   - Datei zu früh "geaddet"
     - `reset` 22,23,27
     - `reset HEAD protokoll.md`
   - Schon committed und doch falsch
     - `--amend` 23
   - Commit nochmal wiederholen
     - `git reset HEAD~1`
   - `reset --hard HEAD`
   - `reflog` 27
     - `log --walk-reflogs`

