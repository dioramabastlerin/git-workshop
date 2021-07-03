
## What happens when merging contradicting renames?


---


    $ git merge rename-and-edit2
    CONFLICT (rename/rename): 
    Rename "a.md"->"a1e.md" in branch "HEAD" 
    rename "a.md"->"a2e.md" in "rename-and-edit2"
    Automatic merge failed; fix conflicts and then commit the result.

---
    
`status` will show it too:
        
    $ git status
      ...
    Unmerged paths:
      (use "git add/rm <file>..." as appropriate to mark resolution)
    
    	both deleted:    a.md
    	added by us:     a1e.md
    	added by them:   a2e.md
      ...


---


Git keeps both variants in the worktree:

    $ ll
    -rw-r--r-- 1 bjoern bjoern   96 Aug 24 20:12 a1e.md
    -rw-r--r-- 1 bjoern bjoern   95 Aug 24 20:12 a2e.md


---


Double check for changes in content:

    git diff --no-index a1e.md a2e.md


---


Decide what to keep and what to remove:

    $ git add a1e.md

    $ git rm a.md
    a.md: needs merge
    rm 'a.md'
    
    $ git rm a2e.md
    a2e.md: needs merge
    rm 'a2e.md'

    $ git commit


Notes:
    
Anmerkung: Obwohl die `needs merge`-Meldung ein wenig nach Fehlermeldung aussieht. 
Werden auch die Löschungen in die Stage Übernommen. Der Merge kann abgeschlossem werden

    
