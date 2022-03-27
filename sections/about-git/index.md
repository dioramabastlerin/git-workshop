
![Torvalds Git Talk](torvalds-tech-talk-on-git.png)

[Watch it on YouTube](https://www.youtube.com/watch?v=4XpnKHJAok8)


---


Der Linux-Kernel wurde lange Zeit händisch versioniert (Tarballs & Patches).

Später wurde eine kommerzielle Versionsverwaltung Namens Bitkeeper genutzt.
Aus Lizenzgründen wurde ab 2005 eine Alternative dazu gesucht.


---


> Subversion used to say it is CVS done right:
> with that slogan there is nowhere you can go.
>
> There is no way to do CVS right.

(Linus Torvalds, Mai 2007)


---

### Herausforderungen der Kernel-Community

 * Viele sehr unabhängige Entwickler
 * Viele Patches
 * Parallele Entwicklung


---

### Die Entwickler von Git

 * Junio C. Hamano
 * Shawn O. Pearce
 * Linus Torvalds
 * uvm.


---


## Warum Git?

  * Robustes und einfaches Branching und Merging
  * Dezentraler Ansatz
  * Performant, auch bei sehr vielen Quelltexten
  * Sehr flexibler Workflow
  * Open-Source
  * Quasi-Standard: Viele Projekte und Unternehmen nutzen Git
  * und ...


---


... das "Killer-Feature"

Der **Pull-Request**
erleichtert es, Änderungen dezentral zu erstellen und
später anderen zur Integration anzubieten.

*GitHub*, *GitLab*, *BitBucket* haben dies als Webanwendungen angeboten.

**Git erleichtert Open-Source-Contributions!**


---


### Achitektur von Git

 * **dezentral** \
   Kein Server/Service/Dämon. \
   Nur Kommandos, die mit Dateien arbeiten.
 * nicht **opinionated** \
   unterstützt unterschiedlichste Workflows \
   ohne Präferenz für *richtigen Workflow*
 * **"Porcelaine & Plumbing"**
   - Plumbing: "Rohe" Befehle, Dateiformate
   - Porcelaine: Komfortable End-User-Befehle
 * **Abwärtskompatibilität**