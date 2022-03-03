

Branch-Modelle
==============


---


## Trunk-Based Development

![Gitflow trunk](abb-branching-strategie-trunk.png)

* Stabiler Master-Branch. Häufige Releases.

---

## Feature-Branching


---

## Release-Staging

---


## Multiple Releases

---

![Multiple](abb-release-produkte.png)

---

Multiple Releases mit Patterns
------------------------------

![Multiple](abb-release-produkte-patterns.png)

---


Continuous Delivery Modell
--------------------------

![CD](abb-release-continuous-delivery.png)

 * Nur ein Quality Gate in Git.


---


---

Gitflow
-------

![Gitflow pur](abb-branching-strategie-gitflow.png)

---

Gitflow: Feature-Branch
------------------------

![Gitflow feature](abb-branching-strategie-gitflow-patterns-feature-branch.png)

---

Gitflow: Integrations-Branch
-----------------------------

![Gitflow integration](abb-branching-strategie-gitflow-patterns-integrationsbranch.png)

---

Gitflow: Pull-Request
-----------------------------

![Gitflow pr](abb-branching-strategie-gitflow-patterns-pull-requests.png)

---

Gitflow: Staging-Branch
-----------------------------

![Gitflow staging](abb-branching-strategie-gitflow-patterns-staging-branch.png)

---

Gitflow: Merge-Kette
-----------------------------

![Gitflow merge](abb-branching-strategie-gitflow-patterns-merge-kette.png)


---



Backports
---------

![Backport](abb-release-produkte-backport.png)

 * Cherry-Pick zum Kopieren des Bugfix.

---

## Werkzeugkasten

### für Workflows

| Werkzeuge             | Patterns              | Workflows             |
|-----------------------|-----------------------|-----------------------|
| Branch                | Feature-Branch        | Trunk-based Dev.      |
| Merge                 | Kollaborations-Branch | Feature Branching     |
| Cherry-Pick           | Integrations-Branch   | Release Staging       |
| Rebase                | Pull-Request          | Multiple Releases     |
| 1st-Parent-History    | Staging-Branch        | Continuous Delivery   |
|                       | Merge-Ketten          | GitFlow               |
|                       | Backporting           |                       |
