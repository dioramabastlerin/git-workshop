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


Die Anforderungen an ein Sourcecode-Repository ergaben sich  
aus Bedürfnissen der Kernel-Community:

 * **Parallele Entwicklung** (1.000+ Contributors)
 * **Sicherheit und Herkunftsnachweise**
 * **Linux Style** für Kernel-Entwickler


---

### Parallele Entwicklung

<table>
  <tr>
    <th>Herausforderung</th>
    <th>Lösungsansatz</th>
  </tr>
  <tr>
    <td>
      <ul>
        <li>kleinst Patches und große Features,</li>
        <li>viele Entwickler (1.000+),</li>
        <li>wenig Koordination</li>
        <li>schwer zentral zu managen</li>
      </ul>
    </td>
    <td>
      <ul>
        <li>Klone: Dezentrales Arbeiten<br/>
      auf unabhängigen Repository-Kopien</li>
        <li>Push/Pull-Operationen<br/>
        ermitteln und übertragen Unterschiede
        nachträglich<br/>
        von Klon zu Klon</li>
      </ul>
    </td>
  </tr>
</table>
 

---


### Sicherheit

<table>
  <tr>
    <th>Herausforderung</th>
    <th>Lösungsansatz</th>
  </tr>
  <tr>
    <td>
        Wer darf Änderungen vornehmen?
    </td>
    <td>
      Schreibrecht nur für Maintainer.<br/>
      Enwickler arbeiten auf Klonen,<br/>
      bieten Änderungen zum Abholen an.<br/>
      ➔ Pull-Request
    </td>
  </tr>
  <tr>
    <td>
        Nachvollziehbarkeit
    </td>
    <td>
      SHA1-Prüfsumme auf allen Inhalten<br/>
      Digitale Signiermöglichkeit
    </td>
  </tr>
</table>

---


### *Linux Style* für Kernel-Entwickler

 * Wenig Abängigkeiten, "überall" installierbar
 * Hoch performant* 
   filesystem-optimiert (Write-once)
 * Stabile Kommandozeilenbefehle erleichtern Automatisierung

\* *für Projekte aus vielen Quelltextdateien*


---

# Git

  * Dezentral: Klone, Push und Pull
  * Robustes und einfaches Branching und Merging
  * Performant, auch bei sehr vielen Quelltexten
  * Sehr flexibler Workflow
  * Austausch zwischen Repos (Push/Pull)
  * Open-Source
  * Quasi-Standard  


---


Offensichtlich war Git nicht für für die Kernelentwicklung eine gute Sache. Sonder auch für andere interessant:

![Microsoft buys GitHub](microsoft-buys-github.png)


