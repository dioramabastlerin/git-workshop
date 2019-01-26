# Continuous Integration

---

## Lernziel

 * Was macht Sourcecode Integration so schwierig?
 * Konzept: Continuous Integration

---

<!-- .slide: data-background-image="continuous-integration/complicated-merge-resolution.png" -->

## Hölle? Hölle? Hölle?

# `git merge`


---

## Übung: Ein vertrackter Merge

In der Historie steckt ein kniffliger Merge.
Wiederhole den Merge und versuche einige der Konflikte aufzulösen?
Was fällt Dir dabei auf?

```bash
# Vorbereitung
git show --stat interesting-merge
git config --global merge.conflictstyle diff3

# Den Merge nochmal durchführen
git checkout interesting-merge^1
git merge interesting-merge^2

git mergetool  # oder verwende deine IDE
```

---

Was verursacht Schwierigkeiten beim Merge?

 * Dieselben Dateien bearbeitet
 * Verschiebungen im Code
 * Whitespaceänderungen
 * Textersetzungen (z. B. zur Umbenennung)
 * Überlappende Aufgaben/Verantwortlichkeiten
 * Inkonsistenz in Änderungen

