---
layout: page
title: Installation
nav_order: 80
permalink: /installation
---

**Liebe Workshop-Teilnehmer*innen!**

Es ist eine gute Idee, die Arbeitsumgebung schon vor dem Workshop vorzubereiten, damit nicht so viel Zeit für Setup-Themen im Workshop verbraucht wird. Meldet Euch bei mir, falls ihr beim Einrichten auf Probleme stoßt. Aber: Keine Panik! Notfalls kriegen wir das auch noch am ersten Vormittag hin.

## Auf dem eigenen Rechner arbeiten

### Git installieren

Zunächst einmal brauchen wir natürlich Git. Installiert es (https://git-scm.com/downloads), oder lasst es installieren (falls euer Rechner fremdverwaltet ist). Falls ihr auf Windows installiert, wird der Installer Euch mehrfach Optionen zur Auswahl anbieten. Nehmt einfach die vorausgewählten Optionen, falls ihr keine besonderen Wünsche an die Installation habt.

### Bash-Shell öffnen

**MacOs**, **Linux**: Eine Terminal-Session öffnen.

**Windows**: Git-Bash öffnen. Drückt dazu kurz die Windows-Taste und gebt dann Git-Bash ein (oder klickt auf das Icon welches der Installer dafür hinterlassen hat). Dann sollte sich ein Terminal der Git-Bash öffnen. Info: Wir führen die (Kommandozeilen-) Beispiele für diesen Workshop mit Bash aus, weil dies sowohl auf Windows also auch Linux und MacOs gut funktioniert. Erfahrene User können natürlich gerne ihr Lieblings-Shell nutzen, z. B. zsh oder powershell.

Gebt Folgendes ein:

    $ git version
    git version 2.30.1

Falls Git bei Euch eine andere Versionsnummer ausgibt, kein Problem: Hauptsache Git ist da.


### Git konfigurieren

Zunächst werden Name und Email konfiguriert:

    $ git config --global user.name <mein-name>
    $ git config --global user.email <meine-email>

(Optional) Editor für Commit-Message etc. konfigurieren, hier z. B. `notepad`:

    $ git config --global core.editor notepad


### Workshop-Repository klonen

    $ git clone https://github.com/bstachmann/git-workshop.git
    $ cd git-workshop
    
#### Fallback

Falls das Klonen, z. B. wegen einer unternehmensinternen Firewall-Regel, nicht klappt, kann die Übung aus einem Zip-Archiv entpackt werden. Dieses erhalten Sie als [Download build.zip](https://github.com/bstachmann/git-workshop/raw/main/build.zip) oder es wird über die Seminarorganisation bereit gestellt.

    $ mkdir git-workshop
    $ cd git-workshop
    $ pwd
 
`pwd` zeigt ihnen, in welchem Verzeichnis die Git-Bash aktiv ist. Kopieren Sie die Datei `build.zip` in dieses Verzeichnis.

### Übungen entpacken

Die Übungen liegen jetzt als Zip-Datei vor und können entpackt werden.

    $ unzip build.zip -d build
    $ cd build/git-uebungen/aufgaben/intro-hallo-welt/
    

## In der Cloud arbeiten (Gitpod IDE)

**Voraussetzung**: Wenn sie einen Account bei [*GitHub*](https://github.com), [*GitLab*](https://gitlab.com) oder [*BitBucket*](https://bitbucket.com) haben (oder einrichten), können sie auch in der Cloud (auf [Gitpod](https://gitpod.io)) arbeiten.

[**Git-Workshop auf Gitpod öffnen**](https://gitpod.io#https://github.com/bstachmann/git-workshop)

Nach dem Öffnen, müssen Sie sich ggf. anmelden, und  Zugriffsrechte für die Gitpod-App gewähren.


