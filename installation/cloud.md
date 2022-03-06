---
layout: page
title: In der Cloud
nav_order: 20
parent: Installation
---

**Liebe Workshop-Teilnehmer*innen!**

Es ist eine gute Idee, die Arbeitsumgebung schon vor dem Workshop vorzubereiten, damit nicht so viel Zeit für Setup-Themen im Workshop verbraucht wird. Meldet Euch bei mir, falls ihr beim Einrichten auf Probleme stoßt. Aber: Keine Panik! Notfalls kriegen wir das auch noch am ersten Vormittag hin.

## Auf dem eigenen Rechner arbeiten

### Git installieren

Zunächst einmal brauchen wir natürlich Git. Installiert es (https://git-scm.com/downloads), oder lasst es installieren (falls euer Rechner fremdverwaltet ist). Falls ihr auf Windows installiert, wird der Installer Euch mehrfach Optionen zur Auswahl anbieten. Nehmt einfach die vorausgewählten Optionen, falls ihr keine besonderen Wünsche an die Installation habt.

### Bash-Shell öffnen

Wir führen die (Kommandozeilen-) Beispiele für diesen Workshop mit Bash aus, weil dies sowohl auf Windows also auch Linux und MacOs gut funktioniert. Erfahrene User können natürlich gerne ihr Lieblings-Shell nutzen, z. B. zsh oder powershell.

**MacOs**, **Linux**: Eine Terminal-Session öffnen.

**Windows**: Git-Bash öffnen. Drückt dazu kurz die Windows-Taste und gebt dann Git-Bash ein (oder klickt auf das Icon welches der Installer dafür hinterlassen hat). Dann sollte sich ein Terminal der Git-Bash öffnen. Info: 

Gebt Folgendes ein:

    $ git version
    git version 2.35.1

Falls Git bei Euch eine andere Versionsnummer ausgibt, kein Problem: Hauptsache Git ist da.


### Workshop-Repository klonen

    $ git clone https://github.com/bstachmann/git-workshop.git
    $ cd git-workshop

Tipp: Lassen sie sich Anzew

    $

    
#### Fallback

Falls das Klonen, z. B. wegen einer unternehmensinternen Firewall-Regel, nicht klappt, kann die Übung aus einem Zip-Archiv entpackt werden. Dieses erhalten Sie als [Download build.zip](https://github.com/bstachmann/git-workshop/raw/main/build.zip) oder es wird über die Seminarorganisation bereit gestellt.

    $ mkdir git-workshop
    $ cd git-workshop
    $ pwd                       # Linux, MacOs: zeigt, in welchem Verzeichnis die Git-Bash aktiv ist
    $ pwd -W                    # Windows: Zeigt den Pfad in Windows Syntax
 
Kopieren Sie die Datei `build.zip` in dieses Verzeichnis.

### Übungen entpacken

Die Übungen liegen jetzt als Zip-Datei vor und können entpackt werden.

    $ unzip build.zip
    $ cd build/git-uebungen/aufgaben/intro-hallo-welt/
    

## In der Cloud arbeiten (Gitpod IDE)

**Voraussetzung**: Wenn sie einen Account bei [*GitHub*](https://github.com), [*GitLab*](https://gitlab.com) oder [*BitBucket*](https://bitbucket.com) haben (oder einrichten), können sie auch in der Cloud (auf [Gitpod](https://gitpod.io)) arbeiten.

[**Git-Workshop auf Gitpod öffnen**](https://gitpod.io#https://github.com/bstachmann/git-workshop)

Nach dem Öffnen, müssen Sie sich ggf. anmelden, und  Zugriffsrechte für die Gitpod-App gewähren.


