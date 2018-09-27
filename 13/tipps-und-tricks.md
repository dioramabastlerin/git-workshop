# Tipps & Tricks

_________________________________________

## Rebasing

_________________________________________

## Datein unversioniert lassen
   - `.gitignore` 21
_________________________________________

## Staging
   - `diff --staged`

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

