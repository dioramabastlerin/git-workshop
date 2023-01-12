---
layout: page
title: <code>modularisierung-submodules</code>
parent: Aufgaben

---
# Übung - Modularisierung mit Submodules


Es geht darum, wie man in Git ein übergreifendes
Repository erstellt, dass Inhalte aus mehreren
anderen Repository einbettet.

Git bietet dazu zwei unterschiedliche Ansätze:
Eine davon ist `git submodule`.
Wir werden hier beide für folgende Anwendungsfälle erprobe:

* Module als Submodule einbinden
* Änderung aus einem Modul übernehmen
* Änderung in ein Modul übertragen
* Übergeordnetes Repo klonen

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
  
  


<pre><code>$ <b>cd submodules</b><br><br><br></code></pre>


<!--UEB-Modularisierung mit Submodules--><h2>Schritt 1 - Module als Submodule einbinden</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/modularisierung-submodules/submodules`.

Binde die Module `mod-a.git` und `mod-b.git`
per `submodule add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>submodules $ <b>cd ..</b><br><br><br></code></pre>


<!--UEB-Modularisierung mit Submodules--><h2>Schritt 2 - Subtree: Änderung aus einem Modul übernehmen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/modularisierung-submodules`.

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `submodules/mod-b` und hole die Änderungen per `pull` ab.
Sieh Dir das übertragene Commit an.

<!--UEB-Modularisierung mit Submodules--><h2>Schritt 3 - Änderung in ein Modul übertragen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/modularisierung-submodules`.

Gehe in `subtrees/mod-a` ändere `anton` und committe.
Übertrage die Änderung per `push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.

<!--UEB-Modularisierung mit Submodules--><h2>Schritt 4 - Übergeordnetes Repo klonen</h2>

Starte im Verzeichnis `git-uebungen/aufgaben/modularisierung-submodules`.

Klone `submodules` zu `mysubmodules`.
Untersuche die Verzeichnisstruktur.
Vergiß nicht, ein `submodule update` auszuführen.

[Zur Lösung](loesung-modularisierung-submodules.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

