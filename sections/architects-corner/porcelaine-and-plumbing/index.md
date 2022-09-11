#### Architects Corner

## Porcelain and Plumbing


---


## Wie macht man Software erweiterbar?


---


Git setzt auf [Porcelaine & Plumbing](https://git-scm.com/book/en/v2/Git-Internals-Plumbing-and-Porcelain), statt auf Plugins.



---


* `plumbing`
  - elementar
  - Datenstrukturen auslesen/bearbeiten
* `porcelaine`
  - komfortabel, leistungsfähige
  - z. B. `log`, `commit`, `merge`, ... 
  - basieren auf `plumbing`
* auch `plumbing` ist 
  - standardisiert
  - ermöglicht Erweiterungen


