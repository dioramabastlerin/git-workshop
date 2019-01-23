
**Branches** ermöglichen es,

in nur *einem* Git-Repo,

mehrere *parallele* Entwicklungsstränge zu haben

und frei zwischen diesen hin und her zu wechseln.

Notes:

Genauer: nur einem Klon eines Repos.

Man auch mehrere Entwicklungsstränge öffnen,
indem man in verschieden Verzeichnissen Klone anlegt.
Dann liegt die Verwaltung der Stränge
außerhalb von Git.
Man muss sich merken, welcher Strang wo abgelegt ist.
Bei Branches gibt man den Strängen Namen und kann sie,
auflisten, vergleichen und administrieren.


---


    git branch

    git branch new-branch

    git checkout new-branch

    git checkout -b new-branch

---

    git branch --merged

    git branch -d

---

## Eigenschaften von Branches

 * beweglicher Zeiger auf Commit.
 * (max.) ein Branch ist *aktiv*
   - checkout wechselt den aktiven Bracnh
 * Beim Commit wird der aktive Branch weiter gesetzt.
 * Branches sind lokal
