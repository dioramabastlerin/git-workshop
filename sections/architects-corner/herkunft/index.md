#### Architects Corner

## Herkunft

---


Der **Linux-Kernel wurde** lange Zeit händisch versioniert (*Tarballs & Patches*),
durch **Linus Torvalds**.

Später wurde eine kommerzielle Versionsverwaltung Namens Bitkeeper genutzt.

*Aus Gründen* musste Torvads ab 2005 eine Alternative dazu finden.


---


Es sollte open-Source sein. Aber **CSV** und
**Subversion** sagten ihm nicht so zu.

> Subversion used to say it is CVS done right:
> with that slogan there is nowhere you can go.
>
> There is no way to do CVS right.

(Linus Torvalds, Mai 2007)


---


![Torvalds Git Talk](torvalds-tech-talk-on-git.png)

[Watch it on YouTube](https://www.youtube.com/watch?v=4XpnKHJAok8)

[Git Website](https://git-scm.com/)


---


... und so entstand Git:

### Die Entwickler

 * Junio C. Hamano
 * Shawn O. Pearce
 * Linus Torvalds
 * uvm.

*Quelle: [Git auf de.wikipedia.org](https://de.wikipedia.org/wiki/Git)*


---


Die Anforderungen ergaben sich  
aus der Kernel-Community:

 * **Parallele Entwicklung** (1.000+ Contributors)
 * **Sicherheit**
 * **Herkunft von Code nachweisen können**
 * **Linux Style** für Kerne-Entwickler


---


### Parallele Entwicklung (1)

 * 1.000+ unabhängige Entwickler
 * unterschiedliche Arbeitsweisen
 * wenig Koordination
 * Mischung aus kleinsten Patches und gewaltigen Features.

---


### Parallele Entwicklung (2)

**Repository**: Eine Datenbank mit allen Versionen aller Quelltexte des Projekts.

Jeder Entwickler hat eine *vollständige unabhängige Kopie* des Projektrepositorys auf seinem loalen Rechner (genant: *Klon*) und kann dort uneingeschränkt Änderungen vornehmen. Git kann **Unterscheide zwischen Klonen** ermitteln, übertragen, kontrolliert zusammenführen kann (**Push** und **Pull**)


---


### Sicherheit und Herkunftsnachweis

Linux-Kernel hochgradig sicherheitsrelevant. Kontrollierte Übernahme von Änderungen wichtig.

 * **Überprüfbarkeit**  
   SHA1-Hashes als Prüfsumme auf allen Inhalten
 * **Digitale Signiermöglichkeit**
 * **Network of Trust**
   1. *Blessed Repository* nur für Maintainer
   2. Entwickler arbeiten auf separaten *Klonen*
   3. bieten ihre Änderungen an (*Pull-Request*) 
   4. Maintainer prüfen beim *Pull* 


---


### *Linux Style* für Kernel-Entwickler

 * Wenig Abängigkeiten, "überall" installierbar

 * Hoch performant  
   filesystem-optimiert (Write-once)

 * Automatisierbar
   stabile Kommandozeilenbefehle

\* *für Projekte aus vielen Quelltextdateien*


---


  * Robustes und einfaches Branching und Merging
  * Performant, auch bei sehr vielen Quelltexten
  * Sehr flexibler Workflow
  * Austausch zwischen Repos (Push/Pull)
  * Open-Source
  * Quasi-Standard  
  * und ...


---


Offensichtlich war Git nicht für für die Kernelentwicklung eine gute Sache. Sonder auch für andere interessant:

![Microsoft buys GitHub](microsoft-buys-github.png)


