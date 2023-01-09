# Git Workshop Material

## Vorgebaute Übungen entpacken

    $ unzip build/distribution/uebungen.zip

## Übungen frisch bauen lassen

    $ gradle clean assembleDist
    $ cd build/git-uebungen/aufgaben

    
## Editing

### Presentation und Website

Hosted on GitHub Pages as https://bstachmann.github.io/git-workshop/. Just push to update.

Preview on http://localhost:4000/git-workshop/ by using [GitPod local Companion](https://www.gitpod.io/blog/local-app) as proxy.


### Exercies

Exercises can be found in `build/git-uebungen`.

 * `gradle run` to rebuild the exercises
 * `gradle distUebungenMarkdown` to update descriptions for the website. Commit and push.
 * `gradle distUebungenZip` to update descriptions for the website. Commit and push.

 ### Progress Server

 A little web app to track progress on the exercises.

  * `gradle progress` to run
  * admin UI on port 8040
  * user UI on port 8080


### Anmerkung

Ich finde Pull-Requests toll.

## Remotes

```bash
 $ git remote add github https://github.com/bstachmann/git-workshop.git
 $ git remote add gitlab https://gitlab.com/bjoern.stachmann/git-workshop.git
```
