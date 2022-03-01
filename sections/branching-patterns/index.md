

# Patterns 

## für Workflows


---


## Feature-Branch

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


## Kollaborations-Branch

### Auf einem Branch zusammenarbeiten

![Rebase auf Feature-Branch](abb-rebase-auf-feature-branch.png)


---

## Kollaborations-Branch

### Auf einem Branch zusammenarbeiten

 * Mehrere Entwickler, 1 Branch
 * Push & Pull 
 * Tipp: `pull --rebase` schafft *lineare Historie*.


---

## Integrations-Branch

![Integrations-Branch](abb-integrationsbranch.png)


---

### Integrations-Branch

 * Integriert Änderungen anderer Branches.
 * Keine direkte Entwicklung auf diesem Branch.
 * Enthält nur **Merges**.
 * **1st-Parent-History** zeigt die Folge der  Integrationen<BR/>
   z. B. "Feature 1", "Bugfix 1", "Feature 2"

---


## Pull-Request

![Pull-Request](abb-pull-request.png)

(Auch Merge-Request genannt.)

---

## Pull-Request

 * separarierte Entwicklung
 * Jemand anderes (Maintainer/Kollege) integriert
 * Quality-Gate: Review, autom. Checks
 * dokumentierte Integration

![Feature Branches](abb-jenkins-pull-requests-stash-config.png)

---


## Staging-Branches

![Staging-Branches](abb-staging-branches.png)

---


## Staging-Branches

Repräsentiert ein Qualitätsniveau, z. B.

 * `develop`: Build-fähig, darf unfertige Features zeigen.
 * `release`: Fertige Features, noch nicht abgenommen
 * `master`: Abgenommene Version für Kunden


---


## Merge-Ketten

![Merge-Ketten](abb-merge-ketten.png)

Merge-Ketten gehen von älteren (upstream) Branches zu neueren Branches.

Durch Merges werden alle Änderungen von einem Branch zum nächsten übertragen.

---

## Backporting

In die andere Richtung,
will man nicht alle Änderungen mitnehmen.

Selektive Übernahme einzelner Änderungen,
z. B. durch Cherry-Pick.

Entkoppelt Releases.

Management erforderlich: Was wurde wo angewandt?
