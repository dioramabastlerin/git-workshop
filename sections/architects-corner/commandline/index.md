#### Architects Corner


## Die Kommandozeile


---


### Was Git nicht ist?

Die (vermutlich) meisten Versionsverwaltungssysteme (z. B. auch Subversion) sind **Client-Server**-Systeme:

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

* * Don't Panic: Im Entwickleralltag brauch man weniger als 10 davon.*



