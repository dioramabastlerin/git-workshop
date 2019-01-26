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

Je länger, je schlimmer.


---

## Früher so

https://nvie.com/posts/a-successful-git-branching-model/

---

## doch dann

> ... usually each person integrates at least daily ...
>
>  Martin Fowler, 2006


https://martinfowler.com/articles/continuousIntegration.html


---

## Häufiges Integrieren

 * Jeder Integriert jeden Tag
 * Der `master` wird immer lauffähig gehalten
 * Der `master` ist Maß aller Dinge

 * macht die Probleme klein und überschaubar.
 * Die Beteiligten sind noch anwesend/erreichbar.
 * Refactorings müssen nur den `master` berücksichtigen.
 * Notfalls, kann man verwerfen und neu machen.

Klingt gut, aber ...

---

## ... kann da nicht auch was schiefgehen?

---

## Herausforderungen in CI

 * Broken Build
 * Fehler eingeschleust
 * Broken API/Contract
 * Halbfertiges
 * Alte Versionen geraten in Umlauf

---

## Broken Build

 * Automate the build
   * Every Commit Should Build the Mainline on an Integration Machine
   * Normierung der Build-Umgebung (against: Works on my machine). Entweder zentral oder in autom. Setup.
 * Fix Broken Builds Immediately
 * Keep the Build Fast


---

## Fehler eingeschleust

 * make your build self-testing
 * Test in a Clone of the Production Environment

Benefit: Man hat immer eine nutzbare Version (Release-Fähigkeit)

---

## Broken API/Contract

 * Branch by Abstraction

---

## Halbfertiges

 * MVP
 * Feature-Toggling

---

## Alte Versionen geraten in Umlauf

 * Abwärtskompatibilität
 * Forward-Fixing
 * Make it Easy for Anyone to Get the Latest Executable
 * update-merges
 * Wer zuletzt merged verliert

---

## Exkurs: Trunk Based Development

https://trunkbaseddevelopment.com/

---

## Also, was brauchen wir?

 * [ ] Workflow
   - [ ] Integration auf dem `master` (Workflow)
   - [ ] Vereinbarte Regeln
     - [ ] Fix Broken Builds Immediately
     - [ ] Wer zuletzt merged verliert
     - [ ] Abwärtskompatibilität

---

## Also, was brauchen wir?

 * [ ] Automatisierter Build
   - [ ] Normierter Build
   - [ ] Jedes Commit auf `master`
   - [ ] Autom. Test
   - [ ] Integr. Tests in Prod.-naher Umgebung
   - [ ] Push -> Artefakt-Repository
   - [ ] Deplyment automatisieren

---

## Also, was brauchen wir?

 * [ ] Techniken/Skill
   - [ ] Branch by Abstraction
   - [ ] Feature Toggling
   - [ ] Growing from MVP
   - [ ] update Merging/Rebasing
   - [ ] Forward Fixing