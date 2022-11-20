#### Architects Corner


## Die Kommandozeile


---


### Git ist kein Client-Server-System

Klassische Versionsverwaltungen:

 1. Server
    * Verwaltet Archiv Versionhistorie auf dem Server
    * laufender Prozess/Dämon
 1. Client (evtl. mehrere)
    * Verwalten den Workspace (die Arbeitsdateien) auf dem Client
 1. kontinuierliche Kommunikation zwischen Client und Server


---


### Git ist ein Kommandozeilentool

Ein **Repository** in Git besteht aus Dateien,
die in einem Verzeichnis (meist: `.git`) gespeichert sind. 

Git besteht aus mehr 100 Kommandozeilenbefehlen* mit denen dieses Repository manipuliert und abgefragt werden kann.

Git ist passiv: Es gibt keinen dauerhaft laufenden Prozess (Dämon).

*Don't Panic: Im Alltag braucht man <10*


---

**Bedeutet dass denn, ich muss ab jetzt mit per Kommandozeile arbeiten?**

Nein, es gibt zahlreiche grafische Benuzeroberflächen für den Desktop und das Web. Trotzdem sollte man die Kommandozeile von Git kennen:

 * **Alle** Git-Funktionalitäten sind so erreichbar
 * Manches geht sehr **effizient**
 * **Tipps und Anleitungen** zeigen meist Kommandos
 * Mit Kommandos lassen sich Vorgänge leicht **automatisieren**.

Erfahrene Git-User nutzen meist beides.