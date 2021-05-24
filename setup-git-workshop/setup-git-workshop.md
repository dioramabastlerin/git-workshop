**Hallo liebe Git-Workshop-Teilnehmer,**

es ist eine gute Idee, Eure Arbeitsumgebung schon vor dem Workshop vorzubereiten, damit wir zu Beginn nicht so viel Zeit für Setup-Themen benötigen und schneller in die wirklich spannenden Inhalte eintauchen können. Ich habe hier ein paar Schritte zusammengefasst, die ihr vorab ausführen könnt.

> Meldet Euch gerne per Email oder Teams bei mir, falls ihr beim Einrichten auf Probleme stoßt.
> Aber: Keine Panik! Notfalls kriegen wir das auch noch am ersten Vormittag hin.

### 1. Git installieren

Zunächst einmal brauchen wir natürlich Git. Installiert es (https://git-scm.com/downloads), oder lasst es installieren (falls euer Rechner fremdverwaltet ist). Falls ihr auf Windows installiert, wird der Installer Euch mehrfach Optionen zur Auswahl anbieten. Nehmt einfach die Vorausgewählten Optionen, falls ihr keine besonderen Wünsche an die Installation habt.

### 1b. (nur Windows) Git-Bash öffnen

Drückt kurz die Windows-Taste und gebt dann `Git-Bash` ein (oder klickt auf das Icon welches der Installer dafür hinterlassen hat). Dann sollte sich ein Terminal der Git-Bash öffnen. *Info*: Wir führen die (Kommandozeilen-) Beispiele für diesen Workshop mit `bash` aus, weil dies sowohl auf Windows also auch  Linux und Mac Os gut funktioniert. Erfahrene User können natürlich gerne ihr Lieblings-Shell nutzen, z. B. `zsh` oder `powershell`.

### 2. Git Version checken

Öffnet ein Terminal und gebt `git version` ein. Falls Git bei Euch eine andere Versionsnummer ausgibt, kein Problem: Hauptsache Git ist da.


```bash 
    $ git version
    git version 2.27.0
```


### 3. Arbeitsverzeichnis einrichten

Richtet jetzt ein Verzeichnis ein, in dem ihr während des Workshops arbeiten könnt:

```bash
    $ mkdir gitworkshop
    $ cd gitworkshop
```

Tipp für Windows-User: Mit  `start .` könnt ihr zusätzlich ein File-Exporer-Fenster auf diesem Verzeichnis öffen.

### 4. Übungen herunterladen und entpacken

Die Übungen könnt ihr als ZIP-File (https://bstachmann.github.io/git-workshop/git-uebungen.zip) herunterladen, in das oben gennante Verzeichnis legen und dort entpacken. Es sollte dann ein Ordner `git-uebungen` mit Unterordner für `aufgaben` und `loesungen` vorliegen:

    $ ls -1 git-uebungen
    aufgaben
    index.html
    loesungen

### 5. Ein Git-Projekt klonen

Probiert, ob ihr das Workshop-Projekt klonen könnt:

    $ git clone https://github.com/bstachmann/git-workshop.git

Hinweis: Hier kann es manchmal zu Firewall- oder anderen Netzwerk-Problemen kommen, falls ihr in einem Unternehmensnetwerk seid.

Viele Grüße

Bjørn Stachmann







    