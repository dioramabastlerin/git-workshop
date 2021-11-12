---
layout: page
title: <code>modularisierung-subtrees</code>
parent: Aufgaben

---
# Übung - Modularisierung mit Subtrees


Es geht darum, wie man in Git ein übergreifendes
Repository erstellt, dass Inhalte aus mehreren
anderen Repository einbettet.

Git bietet dazu zwei unterschiedliche Ansätze:
Einer ist `git subtree`.
Wir werden hier beide für folgende Anwendungsfälle erprobe:

* Module als Subtree einbinden
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
  
  


<pre><code>$ <b>cd subtrees</b><br><br><br></code></pre>


<!--UEB-Modularisierung mit Subtrees--><h2>Schritt 1 - Module als Subtree einbinden</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/modularisierung-subtrees/subtrees`.

Binde die Module `mod-a.git` und `mod-b.git`
per `subtree add` ein.
Untersuche dann die entstandene Verzeichnisstruktur.


<pre><code>subtrees $ <b>cd ..</b><br><br><br></code></pre>


<!--UEB-Modularisierung mit Subtrees--><h2>Schritt 2 - Änderung aus einem Modul übernehmen</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/modularisierung-subtrees`.

Gehe in das Repo `mod-b` ändere die Datei `berta`, committe und pushe.
Sie Dir das entstandene Commit an (`show --stat`)
Gehe in das Repo `subtrees` und hole die Änderungen per `subtree pull` ab.
Sieh Dir das übertragene Commit an.

<!--UEB-Modularisierung mit Subtrees--><h2>Schritt 3 - Änderung in ein Modul übertragen</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/modularisierung-subtrees`.

Gehe in `subtrees` ändere `mod-a/anton` und committe.
Übertrage die Änderung per `subtree push` nach `mod-a.git`.
Sieh Dir das übertragene Commit in `mod-a.git` an.

<!--UEB-Modularisierung mit Subtrees--><h2>Schritt 4 - Übergeordnetes Repo klonen</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/modularisierung-subtrees`.

Klone `subtrees` zu `mysubtrees`.
Untersuche die `HEAD` Verzeichnisstruktur,
und den Commit-graphen.

[Zur Lösung](loesung-modularisierung-subtrees.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

