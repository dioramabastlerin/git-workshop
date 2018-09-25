# Workflow: Lokal alleine entwickeln (easy)
_________________________________________

 * Datei bearbeiten und ändern
   - `status` 13
   - `diff` 19
   - `add`, Staging 14,18
   - `commit <file>` 12,15
   - `commit -a` 12,15
_________________________________________

 * Neue Datei
   - `add <file>`
   - `add .` `<dir>`
_________________________________________

 * Neues Verzeichnis
 * Exkurs: Trees and the Object Storage
    ![Trees and the Object Storage](abb/trees-and-object-storage.jpg)
   - Zeigen, woraus ein Commit-Hash gebildet wird
     `git log --pretty=raw`
_________________________________________

 * `init` 12
_________________________________________

 * Oops
   - Unterbrechung
     - `stash` 66
     - `stash list`
     - `stash pop`
     - `stash -u`

   - Datei sollte gar nicht geändert werden.
     - `checkout` 22,26
     - `git checkout HEAD~2  protokoll.md`
   - War falsch, aber schon länger her
     - `revert`
   - Änderungen Rückgängig 22,23
_________________________________________

 * Löschen von Dateien
   - `rm`
_________________________________________

 * Umbenennen und verschieben
   - `mv`
   - `log --follow`, `-M`

