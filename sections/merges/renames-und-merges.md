
## Was passiert in Git bei widersprüchlichen Umbenennungen

Git meldet sich beim Merge, wenn die beiden Zweigen eine widersprüchliche Umbenennung oder Verschiebung enthalten. 

    $ git merge rename-and-edit2
    CONFLICT (rename/rename): 
    Rename "a.md"->"a1e.md" in branch "HEAD" 
    rename "a.md"->"a2e.md" in "rename-and-edit2"
    Automatic merge failed; fix conflicts and then commit the result.
    
Auch der `status`-Befehl zeigt, was Sache ist:
        
    $ git status
      ...
    Unmerged paths:
      (use "git add/rm <file>..." as appropriate to mark resolution)
    
    	both deleted:    a.md
    	added by us:     a1e.md
    	added by them:   a2e.md
      ...

Git lässt beide Varianten stehen.

    $ ll
    -rw-r--r-- 1 bjoern bjoern   96 Aug 24 20:12 a1e.md
    -rw-r--r-- 1 bjoern bjoern   95 Aug 24 20:12 a2e.md


Zur Sicherheit sollte man noch prüfen, 
ob sich die beiden Dateien inhaltlich unterscheiden 
und die Änderungen ggf. mit einem Editor in jener Datei,
die übrig bleiben soll, zusammenführen.


    git diff --no-index a1e.md a2e.md

Man entscheidet dann, welche Variante man und registriert diese mit `add`. 
Die Andere und die Vorige werden mit `rm` abgemeldet.

    $ git add a1e.md

    $ git rm a.md
    a.md: needs merge
    rm 'a.md'
    
    $ git rm a2e.md
    a2e.md: needs merge
    rm 'a2e.md'
    
Anmerkung: Obwohl die `needs merge`-Meldung ein wenig nach Fehlermeldung aussieht. 
Werden auch die Löschungen in die Stage Übernommen. Der Merge kann abgeschlossem werden

    $ git commit
    
