

Patterns & Practices
====================

---


Feature-Branch
--------------


![Feature Branches](abb-feature-branches.png)

Notes:

Das elementarste Pattern

Wichtig: Verknüpfung mit Issue-Tracker

---

### Feature-Branch

 * Entkoppelte Entwicklung
   - weniger Störungen
   - Basis für Reviews
 * Lebenszyklus je Feature
   - ermöglich unabhängige Releases
   - Typisch: Verknüpfung mit Issue-Tracker

---


### Auf einem Feature-Branch gemeinsam arbeiten

![Rebase auf Feature-Branch](abb-rebase-auf-feature-branch.png)

Für Feature-Branches wünscht man sich eine *lineare Historie*. Integration mit `pull --rebase`.

---


Integrations-Branch
-------------------


![Integrations-Branch](abb-integrationsbranch.png)


---

### Integrations-Branch

 * Integriert Änderungen anderer Branches.
 * Keine direkte Entwicklung auf diesem Branch.
 * Enthält nur **Merges**.
 * **1st-Parent-History** zeigt die Folge der  Integrationen<BR/>
   z. B. "Feature 1", "Bugfix 1", "Feature 2"

---


Pull-Request
------------

![Pull-Request](abb-pull-request.png)

(Auch Merge-Request genannt.)

---


### Quality-Gate beim Pull-Request


![Feature Branches](abb-jenkins-pull-requests-stash-config.png)

(Beispiel aus Bitbucket Server, fka Atlassian Stash)


---


Staging-Branches
----------------

![Staging-Branches](abb-staging-branches.png)

---


Staging-Branches
----------------

Jeder Staging-Branches entspricht einem Qualitätsniveau, z. B. `stable` ist getestet und kann jederzeit in Produktion gehen.

---


Merge-Ketten
------------

![Merge-Ketten](abb-merge-ketten.png)

---

Merge-Ketten
------------

Merge-Ketten gehen von älteren Branches zu neueren Branches.

Geht man in die andere Richtung, spricht man von **Backporting**.

Notes:

Backporting erwähnen.
