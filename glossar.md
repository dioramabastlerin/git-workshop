---
layout: page
title: Glossar
nav_order: 85
has_children: false
permalink: /glossar
---
 
### 1st Parent History 

Beim Auflisten der History wird im Falle von Merges im nur der erste Vorgänger berücksichtigt. 
Hier muss noch was hin.

### Aktiver Branch

Es gibt höchten einen aktiven Branch (*Current Branch*). 
Neu Commits werden dem aktiven Branch zugeordnet.
Ist kein Branch aktiv, spricht man vom *Detached Head*.

### Autor

Die Metadaten zum Commit erlauben es Autor und Committer zu unterscheiden.
Der Autor ist derjenige, der den Code geschrieben hat.
Meist sind Autor und Committer jedoch identisch.

### Backport

Wenn man eine Änderung, die in einer neuen Version gemacht wurde,
auf eine ältere Version übertragen wird spricht man von einem Backport.
Die kann man beispielsweise mit dem Cherry-Pick-Befehl machen.

### Bare Repository

Ein Klon ohne Workspace.

### ⭐ Branch

Ist ein bennannter Strang zur parallelen Enwicklung, z.B. `main` oder `feature-a`.
Anmerkung: In Git ist Branching keine Voraussetzung für parallele Entwicklung. 
Wird in mehreren Klonen eines Repos gearbeitet,
entstehet parallele Entwicklung auch wenn überall auf `main` gearbeitet wird.
 

### ⭐ Commit
### Committer

Die Metadaten zum Commit erlauben es Autor und Committer zu unterscheiden.
Der Committer ist derjenige, der den Code ins Repository integriert hat,
z.B. ein Maintainer der einen Verbesserungsvorschlag (Patch) integriert.
Meist sind Autor und Committer jedoch identisch.

### Continuous Delivery

Automatisiertes Bauen, Testen und Ausliefern von Softfware in einer sogenanten *Build Pipeline*. 
*Pipelines* werden meist durch ein neues Commit oder ein neues Tag angestoßen.

### Checkout

Ein Checkout kopiert den in einer (*Revision*) gespeicherten Datei- und Verzeichnisbaum als Arbeitsdateien in den Workspace.
IdR. ist diese *Revision* dann das neue *Head*.

### Cherry-Pick

Wendet die Änderungen eines frei gewählten Commits 
im aktuellen Branch an.

### CLI

*Command Line Interface* gemeint ist das `git`-Kommando mit seinen Unterbefehlen.
Oft als Abgrenzung zu grafischen Oberflächen (*GUI*).


### Detached Head

Wenn kein Branch aktiv ist, spricht man vom *Detached Head*.
Dies tritt beispielsweise auf, wenn Checkout einer
älteren Version macht, die nicht das oberste Commit eine Branches ist.

### dezentral

In Git ist mit dezentral, dass man in einem einem Klone alle Operationen der Versionierung (Committen, Mergen, Log Untersuchen, etc.) ohne Verbindung zu einem Server durchführen kann. Mit *Push* und *Pull* werden Änderungen zwischen 
beliebigen Klonen ausgetauscht.
Anmerkung: Meist erfolgt der Austausch innerhalb von Teams indirekt über einen zentralen Klon, z. B. bei GitHub oder GitLab.

### Feature-Branch

Branch, der für die Entwicklung eines bestimmten Features vom Haupstrang abgezweigt wird.


### Fetch

TO DO

### fast-forward

Wenn bei einem Merge der andere Branch neue Commits mitbringt,
der aktuelle Branch aber nicht,
erzeugt Git kein Merge-Commit,
sondern setzt einfach den aktuellen Branchzeiger
auf den den Stand des anderen Branches.

### Gitflow

Populäres [Branching-Modell für Git](https://nvie.com/posts/a-successful-git-branching-model/)

### History

Die History ist die Menge aller Vorgänger eines gegebenen Commits (z.B. des HEAD).
Vorgänger sind Parents, Parents von Parents, etc. 

### Integrations-Branch

Ein Integrationsbranch, ist ein Branch auf dem Änderungen anderer Branches
per Merge integriert werden. 
Auf einem Integrationsbranch wird nicht direkt entwickelt.

### ⭐ Head

Das Head ist die aktuelle Revision eine Repositorys.
Meist ist es das zuletzt erstellte oder das zuletzt ausgecheckte Commit.

### Index

Im Index (auch Stage genannt) werden Änderungen für das nächste Commit gesammelt.

### ⭐ Klon

Kopie eines Repositorys.

### ⭐ Merge

Ein Commit in dem die Versionen von zwei (oder mehr)
anderen Commits zusammengeführt werden.

### Merge-Request

Synonym für Pull-Request

### Merge-Ketten

TO DO

### ⭐ Log

Das Log stellt die Historie der Vorgängerversionen eines 
(oder mehrerer) Commits dar.
Das Log kann auf vielfältige Weise aufbereitet und gefiltert werden.

### Origin

Mit Origin bezeichnet man jenes Repository, von dem geklont wurde.

### Parent

Mit *Parent* bezeichnet man eine direkte Vorgängerversion eines Commits.
Das erste Commit einer Historie hat keinen Parent.
Merge-Commits können mehrere Parents haben.

### ⭐ Push

Push übertragt die aktuelle Version auf einen Branch in einem anderen Klon.

### ⭐ Pull

Pull füht zunächst einen Fetch durch
und integriert dann Änderungen aus einem Branch des anderen Klons
in den aktuellen Branch.
Die Integration kann per Merge oder per Rebase erfolgen.

### ⭐ Pull-Request

Mit einem Pull-Request bietet man dem Maintainer eines Repositorys Änderungen, die man ein einem Klon erstellt hat, zur Intgration an. 
Hoster, wie z.B. GitHub, bieten komfortable User-Interfaces hierzu an.

### ⭐ Rebase

Rebase integriert Änderungen, indem die hinzukommenden Commits
ermittelt und dann auf dem aktuellen Branch neu erstellt werden.

### ⭐ Repository

Ein Git-Repository archiviert die Entstehungsgeschichte einer 
Software.
Es enthält die Commitbeschreibungen, den Commit-Graphen, sowie alle Datei- und Verzeichnisstände, als auch Informationen über Branches und Tags.

### ⭐ Revision-Hash

Eindeutiger Identifier für Commits.
Wird als SHA1-Hash über Inhalte und Metadaten des Commits gebildet.

### Remote

Mit Remote bezeichnet man andere Klone des Repositorys mit denen man Daten per Push und Pull austauschen kann. Meist werden diese per SSH oder HTTPS adressiert,
aber auch lokale Pfade sind zulässig.


### Remote Ref

Remote Refs sind Stellvertreter für den Stand von Branches und Tags in anderer Klone, z. B, dem Origin.

### Ref

Ref ist ein Oberbegriff für Zeiger auf Commits. Branches und Tags sind Refs.

### Upstream-Branch

Ein lokaler Branch, z. B. `main`, kann per Konfiguration mit eine Branch in einem Remote verbunden werden, z.B. `origin/main`.
Beim Checkout (oder Switch) wird diese Verknüpfung idR. automatisch erstellt.

### Staging

Im sogenannten Index, werden Änderungen für das nächste Commit gesammelt.
Dies nennt man Staging.

### Staging-Branch

Wenn man Branches nutzt, um unterschiedliche Phasen der Entwicklung abzubilden, wie etwa `development`, `test` oder `main`, dann spricht man von Staging-Branches.

### Stash
### Submodule

Ein Submodule ist ein Repo, dass als Unterverzeichnis in ein anderes eingebunden ist.

### Subtree

Ein Subtree nennt man es, wenn der Inhalte eines Commits (Tree) in ein Unterverzeichnis eines anderen Repos kopiert wurde. 

### Switch

Switch nennt man das Wechseln auf einen anderen Branch.

### Tag

Festgelegter Name für ein bestimmtes Commit, z.B. "v1.0.2". 

### Tree

Mit Tree bezeichnet man eine hierarchische Struktur aus Dateien und Verzeichnissen.

### Trunk Based Development

Entwicklungsmethode bei der alle Entwickler auf demselben Branch arbeiten und häufig integrieren.

### ⭐ Workspace

Arbeitsbereich mit den Dateien und Verzeichnissen des Projekts.

