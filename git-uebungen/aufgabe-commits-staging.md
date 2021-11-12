---
layout: page
title: <code>commits-staging</code>
parent: Aufgaben

---
# Übung - Staging

Es geht um den *Stage-Bereich* (auch Index genannt).
Änderungen (bearbeitete, neue oder gelöschte Dateien) werden nur dann
in ein Commit übernommen, wenn sie vorher im *Stage-Bereich*
registriert werden, z.B. mit `add`

## Tipps

* `git add <datei/verzeichnis>` 
   überträgt den aktuellen Stand einer Datei in den Stage-Bereich.
*  Ändert man eine Datei nach dem `add`, hat sie
   im Workspace einen anderen Stand als in der Stage.
* `git status`, `git diff` und `git diff --staged` zeigen dies.
* `git restore --staged <file>` nimmt ein Staging zurück.
* `git restore <file>` stellt eine Datei im Workspace wieder her.
   **Achtung**: Die lokale Änderungen werden dabei überschreiben!
   - mit `-s <revision>` kann man bestimmen, welche Version wiederhergestellt wird.
* `git clean -df`: Räumt nicht versionierte Daten und Verzeichnisse ab.

# Setup

Im Verzeichnis `repo` wartet ein Git-Projekt darauf,
bearbeitet zu werden. 



<pre><code>$ <b>cd repo</b><br><br><br></code></pre>


<!--UEB-Staging--><h2>Schritt 1 - Staging</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/commits-staging/repo`.

Ersetze in der Datei `demo`,
`Fit` durch `Git`.
Füge sie dann zum Stage-Bereich hinzu.
Ersetze dann `doof` durch `toll`.
Lasse dir den Status und die Diffs
für Workspace und Stage zeigen.

<!--UEB-Staging--><h2>Schritt 2 - Restore - Staging zurücknehmen</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/commits-staging/repo`.

Die letzte Änderung soll doch noch nicht in das nächste Commit übernommen werden,
nehme sie zurück. 

<!--UEB-Staging--><h2>Schritt 3 - Restore - Datei wiederherstellen</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/commits-staging/repo`.

Die letzte Änderung soll ganz verworfen werden. 

<!--UEB-Staging--><h2>Schritt 4 - ⭐ Restore - Zurückholen älterer Datei- und Verzeichnisversionen</h2>

Starte im Verzeichnis `build/git-uebungen/loesungen/commits-staging/repo`.

Im Folder `ufer` wurde ein Spiel gespielt.
Stelle die Spielstände nach, 
indem Du `restore` auf das `ufer`-Verzeichnis anwendest.

Tipp: `ls ufer/*` zeigt die Verzeichnisse des Spiels.

Tipp: Beim `restore` werden unversionierte Dateien nicht abgeräumt.
Man kann sie mit dem `clean`-Befehl abräumen.


<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>


[Zur Lösung](loesung-commits-staging.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

