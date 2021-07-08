---
layout: page
title: <code>repository-sparse-checkout</code>
parent: Aufgaben

---
# Übung - Sparse Checkout


## Tipps

 * `git clone --sparse`: Klont ein Repository, ohne den Workspace zu füllen.
 * `git sparse-checkout init --cone`: Konfiguriert den cone-Modus für bessere Performance.
 * `git sparse-checkout add fileDirOrPattern`: Dateien bzw. Verzeichnisse hinzufügen,
    die beim nächsten Checkout geladen werden sollen.
    Beim nächsten Checkout wird alles geholt.
 * `git sparse-checkout list`: Aktuelle Sparse-Checkout-Konfiguration ansehen.
 * `git sparse-checkout disable`: Sparse-Checkout wieder abschalten.

## Setup

Ein Git-Repository namens `repo` wurde bereits erstellt.
Es enthält Dateien auf dem Top-Level und in zwei Unterverzeichnissen.

### Verzeichnisse

 * `./` Haupverzeichnis für diese Übung 
   - `repo/` Bereits vorhandenes Repository.
  

## Schritt 1 - Sparse-Klon durchführen

Starte im Verzeichnis `aufgaben/repository-sparse-checkout`.

Erstelle einen Sparse-Klon von `repo` mit dem Namen `myrepo`,
überprüfe, dass nur Top-Level-Dateien in den Workspace geholt wurden.

## Schritt 2 - Verzeichnis hinzufügen

Starte im Verzeichnis `aufgaben/repository-sparse-checkout`.

Füge das Verzeichnis `component-a` hinzu .
Überprüfe die neue Konfiguration.
Validiere, dass `component-a` jetzt da ist.

## Schritt 3 - Sparse Checkout deaktivieren

Starte im Verzeichnis `aufgaben/repository-sparse-checkout`.

| Deaktiviere Sparse-Checkout und führe erneut ein Checkout durch.
| Validiere, dass jetzt alle Dateien da sind.

## Schritt 4 - Klonen und auschecken

Starte im Verzeichnis `aufgaben/repository-sparse-checkout`.

Klone `myrepo`, schränke auf `component-a` ein
und mache ein Checkout. Nutze die `--cone`-Option.

[Zur Lösung](loesung-repository-sparse-checkout.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

