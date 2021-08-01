---
layout: page
title: Installation
nav_order: 80
permalink: /installation
---

**Liebe Workshop-Teilnehmer!**

Es ist eine gute Idee, die Arbeitsumgebung schon vor dem Workshop vorzubereiten, damit nicht so viel Zeit für Setup-Themen im Workshop verbraucht wird. Meldet Euch bei mir, falls ihr beim Einrichten auf Probleme stoßt. Aber: Keine Panik! Notfalls kriegen wir das auch noch am ersten Vormittag hin.

## Auf dem eigenen Rechner arbeiten

### Git installieren

Zunächst einmal brauchen wir natürlich Git. Installiert es (https://git-scm.com/downloads), oder lasst es installieren (falls euer Rechner fremdverwaltet ist). Falls ihr auf Windows installiert, wird der Installer Euch mehrfach Optionen zur Auswahl anbieten. Nehmt einfach die vorausgewählten Optionen, falls ihr keine besonderen Wünsche an die Installation habt.

### Bash-Shell öffnen

**MacOs**, **Linux**: Eine Terminal-Session öffnen.

**Windows**: Git-Bash öffnen. Drückt dazu kurz die Windows-Taste und gebt dann Git-Bash ein (oder klickt auf das Icon welches der Installer dafür hinterlassen hat). Dann sollte sich ein Terminal der Git-Bash öffnen. Info: Wir führen die (Kommandozeilen-) Beispiele für diesen Workshop mit Bash aus, weil dies sowohl auf Windows also auch Linux und MacOs gut funktioniert. Erfahrene User können natürlich gerne ihr Lieblings-Shell nutzen, z. B. zsh oder powershell.

Gebt Folgendes ein:

    $ git version
    git version 2.27.0

Falls Git bei Euch eine andere Versionsnummer ausgibt, kein Problem: Hauptsache Git ist da.


### Git konfigurieren

Zunächst werden Name und Email konfiguriert:

    $ git config --global user.name <mein-name>
    $ git config --global user.email <meine-email>

(Optional) Editor für Commit-Message etc. konfigurieren, hier z. B. `notepad`:

    $ git config --global core.editor notepad


### Workshop-Repository holen


    $ git clone https://github.com/bstachmann/git-workshop.git
    $ cd git-workshop

### Übungen entpacken

Die Übungen liegen jetzt als Zip-Datei vor und können entpackt werden.

    $ unzip build.zip -d build
    $ cd build/git-uebungen/aufgaben/intro-hallo-welt/
