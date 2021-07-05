---
layout: page
title: <code>modularisierung-submodules-subtrees</code>
parent: Aufgaben

---
# Übung - Modularisierung mit Submodules und Subrepos


Es geht darum, wie man in Git ein übergreifendes
Repository erstellt, dass Inhalte aus mehreren
anderen Repository einbettet.

Git bietet dazu zwei unterschiedliche Ansätze:
`git submodule` und `git subtree`.
Wir werden hier beide für folgende Anwendungsfälle erprobe:

* Module als Subtree einbinden
* Änderung aus einem Modul übernehmen
* Änderung in ein Modul übertragen
* Übergeordnetes Repo klonen

### Submodules

Bei diesem Ansatz werden Git-Repositorys geschachtelt,
d. h. in dem Zielverzeichnis wo das Modul eingebettet werden
wird das das andere Repository geklont.
Das übergeordnete Repository merkt sich dabei nur
die Adresse (URL) des anderen Repositorys,
das Zielverzeichnis und den Commit-Hash,
der ausgecheckt werden soll.

 * `submodule add  <Quellrepository> <Zielverzeichnis>`:
    Zum initialen Einbetten.
    **Achtung!** Bei Submodules müssen Änderungen durch ein `git commit` bestätigt werden!
 * `submodule update --init <Zielverzeicnis>`:
    Zum Holen der der Submodule.

### Subtrees

Bei diesem Ansatz werden Commits aus dem aus dem untergeordeten
Repository übertragen und per `merge` integriert, 
ganz ähnlich wie beim normalen `pull`.
Die Besonderheit ist, dass Zur Integraion 
dabei eine sogenanntes `subtree`-Merge erfolgt, 
bei dem die Dateien in eine vorgegebenes Zielverzeichnis (`prefix`) verschoben werden.

 * `subtree add --prefix=<Zielverzeichnis> <Quellrepository>`: Initales einbetten.
 * `subtree pull --prefix=<Zielverzeichnis> <Quellrepository>`: Aktualisieren aus dem Quellrepository.
 * `subtree push--prefix=<Zielverzeichnis> <Quellrepository>`: Übertragen ins Quellrepository.

Tipp: Wer nicht mag,
dass Subtree alle Commit aus dem Quellrepository holt,
kann die Option `--squash` nutzen.

## Setup

Zwei Repositorys `mod-a` und `mod-b` sind vorhanden.
Diese sollen 

### Verzeichnisse

 * `./` Haupverzeichnis für diese Übung 
   - `myfirstrepo/` Bereits vorhandenes Repository.
  
  


<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>


## Schritt 2 - Module als Subtree einbinden

Starte im Verzeichnis `aufgaben/subtrees`.

Binde die Module `mod-a.git` und `mod-b.git`
per `subtree add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>


## Schritt 3 - Änderung aus einem Modul übernehmen

Starte im Verzeichnis `aufgaben/modularisierung-submodules-subtrees`.

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
Sieh Dir das übertragene Commit an.

## Schritt 4 - Änderung in ein Modul übertragen

Starte im Verzeichnis `aufgaben/modularisierung-submodules-subtrees`.

Gehe in `subtrees` ändere `mod-a/anton` und committe.
Übertrage die Änderung per `subtree push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.

## Schritt 5 - Übergeordnetes Repo klonen

Starte im Verzeichnis `aufgaben/modularisierung-submodules-subtrees`.

Klone `subtrees` zu `mysubtrees`.
Untersuche die `HEAD` Verzeichnisstruktur,
und den Commit-graphen.


<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>


## Schritt 7 - Module als Submodule einbinden

Starte im Verzeichnis `aufgaben/submodules`.

Binde die Module `mod-a.git` und `mod-b.git`
per `submodule add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>


## Schritt 8 - Subtree: Änderung aus einem Modul übernehmen

Starte im Verzeichnis `aufgaben/modularisierung-submodules-subtrees`.

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `submodules/mod-b` und hole die Änderungen per `pull` ab.
Sieh Dir das übertragene Commit an.

## Schritt 9 - Änderung in ein Modul übertragen

Starte im Verzeichnis `aufgaben/modularisierung-submodules-subtrees`.

Gehe in `subtrees/mod-a` ändere `anton` und committe.
Übertrage die Änderung per `push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.

## Schritt 10 - Übergeordnetes Repo klonen

Starte im Verzeichnis `aufgaben/modularisierung-submodules-subtrees`.

Klone `submodules` zu `mysubmodules`.
Untersuche die Verzeichnisstruktur.
Vergiß nicht, ein `submodule update` auszuführen.

[Zur Lösung](loesung-modularisierung-submodules-subtrees.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

