#### Architects Corner


## Die Kommandozeile


---


### Git ist kein Client-Server-System

Klassische Versionsverwaltunge bestehen aus:

 1. Server
    * Verwaltet Archiv Versionhistorie auf dem Server
    * laufender Prozess/Dämon
 1. Client (evlt. mehrere)
    * Verwalten den Workspace (die Arbeitsdateien) auf dem Client

Beim Arbeiten (Versionen erstellen, Historie abfragen etc) findet kontinuierlich eine Kommunikation zwischen Client und Server statt.


---


### Git ist ein Kommanzeilentool

Ein **Repository** in Git besteht aus Dateien,
die in einem Verzeichnis (meist: `.git`) gespeichert sind. 

Git besteht aus mehr 100 Kommandozeilenbefehlen* mit denen dieses Repository manipuliert und abgefragt werden kann.

Git ist passiv: Es gibt keinen dauerhaft laufenden Prozess (Dämon).

*Don't Panic: Im Alltag braucht man <10*



