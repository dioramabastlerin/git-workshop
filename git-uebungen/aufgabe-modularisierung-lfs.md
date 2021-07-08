---
layout: page
title: <code>modularisierung-lfs</code>
parent: Aufgaben

---
# Übung - Git LFS

Hier geht es um das [Git LFS (Large File System)](https://git-lfs.github.com/),
das es ermöglicht große Artefakte, z. B. Bilder, Filme, Audio, 
mit Git zu versionieren aber außerhalb des Repositorys zu speichern,
so dass nicht bei jedem Clone alle Bilder geladen werden,
sonden nur die für ein `checkout` jeweils benötigten.

## Installaction

Installation unter debian/ubuntu:

    $ sudo apt-get update
    $ sudo apt-get install git-lfs

## Tipps

* `git lfs install` richtet LFS in einem Repository ein.
* `git lfs-track <pattern>` legt fest welche Dateien im LFS gespeichert werden sollen.\
  Diese Einstellung wird in der Datei `.gitattributes` hinterlegt, 
  die man mitversionieren sollte.
* `git lfs ls-files` zeigt, welche Dateien im `HEAD` durch LFS verwaltet werden.\
   Mit `--all` werden auch Dateien aus der Historie angezeigt.
* `git lfs logs` zeigt Details, falls mal etwas schief geht.   




<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


## Schritt 1 - LFS einrichten

Starte im Verzeichnis `aufgaben/repo`.

Richte LFS in für `png`-Dateien ein und pushe das Ergebnis.
Erzeuge dann eine `png`-Datei (muss kein echtes Bild sein)
und pushe erneut.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


## Schritt 2 - Ein LFS-Repo klonen

Starte im Verzeichnis `aufgaben/modularisierung-lfs`.

Klone das Repository.
Schaue die `png`-Datei an. 

## Schritt 3 - Noch ein Bild

Starte im Verzeichnis `aufgaben/modularisierung-lfs`.

Ändere das `png` in `repo` und pushe das Ergebnis. 
Untersuche in `myclone` welche Dateien von LFS verwaltet werden.

## Schritt 4 - Auf alte Versionen wechseln

Starte im Verzeichnis `aufgaben/modularisierung-lfs`.

Erstelle einen neuen Klon `myclone2` und checke dort `master~2` aus.
Schau Dir die `png`-Datei an.  

## Schritt 5 - Trouble

Starte im Verzeichnis `aufgaben/modularisierung-lfs`.

Erstelle einen neuen Klon `myclone3`
Entferne jetzt Hauptrepository durch `rm -rf repo.git` und versuche auf 
dann in `myclone3` auf `master~2` zu wechseln. Was passiert?                

[Zur Lösung](loesung-modularisierung-lfs.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

