---
layout: page
title: <code>modularisierung-repositorys-zusammenfuehren</code>
parent: Aufgaben

---
# Übung - Kleine Projekte zusammenfuehren

Zwei bisher unabhängige Repositorys sollen,
unter Erhaltung der Historie zu einem 
großen Repository zusammengeführt werden.

## Tipps

 * Mit `fetch` (und `pull`) kann man auch Historie aus fremden Repositorys ohne gemeinsame Histories
 mit dem aktuellen Repository holen (Stichwort *unrelated histories*).
 * `merge` (und `pull`) verweigern normalerweise das Integrieren von *unrelated histories*
   - Mit `--allow-unrelated-histories` kann man dies jedoch erzwingen.
 * `git mv` verschiebt Dateien und Verzeichnisse, z. B. `git mv datei1 date1 zielverzeichnis`.
 *  `subtree add --prefix=<Zielverzeichnis> <Quellrepository> <Quellbranch oder Version>`: Einbetten eines anderen Repositorys in ein Unterverzeichnis.
    - Fall man nicht die ganze Historie übernehmen möchte, kann man `--squash` angeben.
    - **Achtung:** `subtree add ` führt einen Merge auf `HEAD` durch und  
      kann daher nicht in einem leeren Repository ohne Commits ausgeführt werden.

## Setup

Zwei Repositorys
 
 * `backend.git`
 * `ui.git` 

sind bereits vorhanden.


<!--UEB-Kleine Projekte zusammenfuehren--><h2>Schritt 1 - Zusammenführen `git subtree`</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/modularisierung-repositorys-zusammenfuehren`.


1. Erstelle ein Repo `application` mit einem Commit.
2. Füge `backend.git` in einem Unterverzeichnis `backend` hinzu.
3. Füge `ui.git` in einem Unterverzeichnis `ui` hinzu.
4. Untersuche Verzeichnissstruktur und Commit-Graphen

<!--UEB-Kleine Projekte zusammenfuehren--><h2>Schritt 2 - Zusammenführen mit `fetch`, `mv` und `merge`</h2>

Starte im Verzeichnis `build/git-uebungen/aufgaben/modularisierung-repositorys-zusammenfuehren`.

UI und Backend sollen in einem neuen Klon `gesamt` zusammengeführt werden.
Folge den Anweisungen im Kapitel *"Kleine Projekte zusammenführen"*.
Untersuche dann Verzeichnissstruktur und Commit-Graphen

[Zur Lösung](loesung-modularisierung-repositorys-zusammenfuehren.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

