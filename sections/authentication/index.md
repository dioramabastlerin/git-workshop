### Zugriff auf andere Repositories

Für `clone`, `fetch`, `pull` und `push` wird Zugriff auf andere Repos benötigt.
Drei Arten des Zugangs sind üblich:

  * `file:` Dateisystem, Berechtigung durch das Betriebssystem
  * `ssh:` Secure Shell, Authentifizierung mit ssh-Zertifikaten, Netzwerkkommunikation verschlüsselt
  * `https:` Secure Web, Authentifizierung per Passwort, Netzwerkkommunikation verschlüsselt
    

---


### `ssh:` Caching von Zugangsdaten

Auf Linux- und MacOs-Systemen werden private-Keys in der Regel durch den `ssh-agent` von  *OpenSSH* im Speicher gecached. Falls noch nicht aktiv kann ein Agent mit `eval $(ssh-agent)` gestartet werden.

Auf Windows-Systemen wird oft [Putty](https://www.chiark.greenend.org.uk/~sgtatham/putty/) für den SSH-Zugang und zum Cachen private Keys genutzt.


---


### `https:` Caching von Zugangsdaten

Per Konfiguration kann Git [das Handling von Passwörten delegieren](https://git-scm.com/docs/gitcredentials), z B.

```bash
$ git config credential.helper 'cache'
```

---

 * [`git-credential-store`](https://git-scm.com/docs/git-credential-store) speichert Passwörter unverschlüsselt auf Festplatte. Unsicher falls Volume nicht encrypteds.
 * [`git-credential-cache`](https://git-scm.com/docs/git-credential-cache) speichert Passwörter in Memory.
 * [`git-credential-manager`](https://github.com/GitCredentialManager/git-credential-manager)
   - Für Windows: Kann im Standard-Installer von Git ausgewählt werden. 
