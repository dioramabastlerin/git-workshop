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

 * macht die **Probleme klein** und überschaubar.
 * Die Beteiligten sind noch **erreichbar**.
 * **Refactorings** müssen **nur `master`** berücksichtigen.
 * Notfalls, kann man alles **verwerfen und neu machen**.
 * Man kann **jederzeit liefern**.

Klingt gut, aber ...

### es gibt, wie gesagt, nichts geschenkt ...

---

## ... kann da nicht auch was schiefgehen?

---

## Herausforderungen in CI

 * Broken Build
 * Bug
 * Broken Contract
 * Halbfertiges
 * Wenn man alte Versionen braucht


---

## Broken Build

> Jeder soll jederzeit, ausgehend vom `master`,\
> einen Feature/ein Bugfix beginnen können.

 * Automatisiere den Build!
   * Jedes `master`-Commit löst einen Build-aus
 * Normiere die Build-Umgebung!
   * Reduziere Abhängigkeiten von lokaler Installation
   * Gegen: Works on my machine.
   * Durch Zentralisierung, durch automatisiertes Setup und/oder Container.
 * Repariere den `master` sofort!
   * Zweithöchste Prio nach **Production Outages**
 * Halte den Build-Prozess schnell! (<< 15 Minuten)
 * Schütze den `master`!
 * Wer zuletzt merged verliert!

---

## Fehler eingeschleust

> Der `master` soll jederzeit lauffähig sein.

 * Test mit jedem Build!
   - Unit-Tests, Integrations-Tests. Ggf. auch Last- und Performance-Tests.
 * Test in einem Klon der Produktionsumgebung!
   - Jeder Unterschied zwischen PROD und TEST ist ein zusätzliches Risiko.

### Wieviel Test ist genug?

> Traust Du Dich den `master`/
> blind nach Production zu releasen?


Notes:

Benefit: Man hat immer eine nutzbare Version (Release-Fähigkeit)


---

## Broken API/Contract

> Zwinge Deine Kollegen nicht zu (sofortigen) Änderungen!

 * Branch by Abstraction
   - Sorge dafür, dass bestehender Conde lauffähig bleibt
   - Nutze hierzu Vererbung, Konfiguration, Feature-Toggles
   - Nutze Deprecation-Markierungen
   - Tipp: Teste die alte und die neue Implementierung parallel und vergleiche die Ergebnisse.
     - evtl. kannst Du die neue Implementierung in PROD parallel mitlaufen lassen, bevor du deren Ergebnisse nutzt.

Notes:

Beispiel: Umstellung von Double auf Fraction

---

## Halbfertiges

> Der `master` ist jederzeit Releasefähig!
> Unfertige Features dürfen dies nicht einschränken!

 * Strebe erst ein MVP an!
   - Minimum Viable Product:\
     Eine möglichst einfache Implementierug für einen wertwollen Teil des angestrebten Nutzens.
 * Nutze Feature-Toggling!

---

## Wenn man alte Versionen braucht

 * Bleibe Abwärtskompatibel!
 * Nutze Forward-Fixing!
   - Vermeide es, Produktionsprobleme durch Rollout alter Versionen zu beheben.
 * Nutze Blue/Green- oder Incremental-Rollouts!
 * Mache es leicht die aktuellste Version zu nutzen und zu integrieren!

---

## Exkurs: Trunk Based Development

https://trunkbaseddevelopment.com/


---

## Exkurs: Git und Gradle

[Git und Gradle](https://kapitel26.github.io/git/2014/05/20/git-und-gradle.html)


---

### Also, was brauchen wir? (Teil 1)

## Workflow/Regeln

 * Häufige Integration auf dem `master` (Workflow)
 * Repariere den `master` sofort!
 * Halte den Build-Prozess schnell!
 * Wer zuletzt merged verliert!
 * Bleibe Abwärtskompatibel!
 * Mache es leicht die aktuellste Version zu nutzen und zu integrieren!


---

### Also, was brauchen wir? (Teil 2)

## Build & Deployment

 * Automatisiere den Build!
 * Normiere die Build-Umgebung!
 * Halte den Build-Prozess schnell!
 *  Schütze den `master`
 * Test mit jedem Build!
 * Test in einem Klon der Produktionsumgebung!
 * Nutze Blue/Green- oder Incremental-Rollouts!



---

### Also, was brauchen wir? (Teil 3)

## Techniken/Skills/Patterns

 * Branch by Abstraction
 * Strebe erst ein MVP an!
 * Nutze Feature-Toggling!
 * Nutze Forward-Fixing!

