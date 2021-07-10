

Branch-Modelle
==============


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

Trunk-Based
-----------------------------

![Gitflow trunk](abb-branching-strategie-trunk.png)

* Stabiler Master-Branch. Häufige Releases.

---

## Multiple Releases

---

![Multiple](abb-release-produkte.png)

---

Multiple Releases mit Patterns
------------------------------

![Multiple](abb-release-produkte-patterns.png)

---



Backports
---------

![Backport](abb-release-produkte-backport.png)

 * Cherry-Pick zum Kopieren des Bugfix.

---


Continuous Delivery Modell
--------------------------

![CD](abb-release-continuous-delivery.png)

 * Nur ein Quality Gate in Git.

---

Code-Reviews
============

Hat man nur ein Quality-Gate und geht sofort in Produktion, <BR>
dann sind gute Code-Reviews wichtig.

---

#### Das Problem

Bei Feature-Branches entstehen oft so viele Änderungen, dass das Review am Ende sehr komplex wird.


#### Lösungsansatz

Nicht "das Diff" am Ende des Branches reviewen<br/>
sondern jedes Commit einzeln.

---

#### Das Problem dabei

Zu viele Commits. Oft zu unstrukturiert.

#### Lösung dazu

*Interactive Rebasing* schafft eine lesbare Historie.
