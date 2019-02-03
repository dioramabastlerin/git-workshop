# Continuous Integration

---

## Lernziel

 * **Das Problem** mit den **Merges**
 * **Klassische** Branch- und Release- Modelle
 * Konzept: **Continuous Integration**
 * Die **Herausforderungen** von **CI**
 * Was braucht man für **erfolgreiches CI**?


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

### Woher kommen die Probleme?

 * Dieselben Dateien bearbeitet\
   (überlappende Aufgaben/Verantwortlichkeiten)
 * Änderungen können zusammegeführt werden,\
   passen aber nicht zusammen.
 * Verschiebungen/Umbenennungen
   * von Dateien
   * innerhalb der Dateien
 * Whitespaceänderungen


### Je länger man wartet, desto schlimmer!

---

**Klassische Branching- und Release-Modelle**\
kann man gut mit Git umsetzen, und bekommt

 * unabhängige Featureenwicklung
 * parallele Weiterentwicklung während der Releasephase
 * mehrere Releases plegen
 * Patches über mehrere Release verteilen

(siehe z. B. [GitFlow](https://nvie.com/posts/a-successful-git-branching-model/))

### aber es gibt nichts geschenkt


---

Man erkauft sich dies mit **langlebigen Branches**,\
was zu Merges zwischen weit entfernt liegenden Änderungen führt, und erhält oft

 * Schwierigkeiten bei der Integration
 * stressige Release-Phasen
 * Zähigkeit durch Angst vor Änderungen


---

## Doch dann ...

> ... usually each person integrates at least daily ...
>
>  Martin Fowler, 2006

https://martinfowler.com/articles/continuousIntegration.html


---

## häufig Integrieren

 * Jeder integriert *jeden Tag*
   1. auf dem aktuellen `master` beginnen
   1. entwickeln
   1. auf den `master` bringen (direktes `commit` oder `merge`oder `rebase`)
 * Der `master` wird immer lauffähig gehalten
 * Der `master` ist Maß aller Dinge

---

## Vorteile von CI

 * macht die Probleme klein und überschaubar.
 * Die Beteiligten sind noch anwesend/erreichbar.
 * Refactorings müssen nur den `master` berücksichtigen.
 * Notfalls, kann man alles verwerfen und neu machen.

Klingt gut, aber ...

### es gibt, wie gesagt, nichts geschenkt ...

---

## ... kann da nicht auch was schiefgehen?

---

## Herausforderungen in CI

 * Broken Build
 * Broken Contract
 * Bug
 * Halbfertiges
 * Wenn man alte Versionen braucht


---

## Broken Build


 * Automate the build
   * Every Commit Should Build the Mainline on an Integration Machine
   * Normierung der Build-Umgebung (against: Works on my machine). Entweder zentral oder in autom. Setup.
 * Fix Broken Builds Immediately
 * Keep the Build Fast


---

## Broken API/Contract

 * Branch by Abstraction



---

## Fehler eingeschleust

 * make your build self-testing
 * Test in a Clone of the Production Environment

Benefit: Man hat immer eine nutzbare Version (Release-Fähigkeit)


---

## Halbfertiges

 * MVP
 * Feature-Toggling

---

## Wenn man alte Versionen braucht

 * Abwärtskompatibilität
 * Forward-Fixing
 * Make it Easy for Anyone to Get the Latest Executable
 * update-merges
 * Wer zuletzt merged verliert
 * master protection

---

## Exkurs: Trunk Based Development

https://trunkbaseddevelopment.com/

---

### Also, was brauchen wir? (Teil 1)

## Workflow

 - [ ] Integration auf dem `master` (Workflow)
 - [ ] Fix Broken Builds Immediately
 - [ ] Wer zuletzt merged verliert
 - [ ] Abwärtskompatibilität


---

### Also, was brauchen wir? (Teil 2)

## Build

 - [ ] Automatisierter Build
 - [ ] Normierter Build
 - [ ] Jedes Commit auf `master`
 - [ ] Autom. Test, (Tagging von Erfolgen, speculative Merge oder Build-Blessed Repo)
 - [ ] Integr. Tests in Prod.-naher Umgebung
 - [ ] Push -> Artefakt-Repository
 - [ ] Deplyment automatisieren

---

### Also, was brauchen wir? (Teil 3)

## Techniken/Skills/Patterns

 - [ ] Branch by Abstraction
 - [ ] Feature Toggling
 - [ ] Growing from MVP
 - [ ] update Merging/Rebasing
 - [ ] Forward Fixing