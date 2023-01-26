# Git Workshop Material

falsche änderung!

## Vorgebaute Übungen entpacken

    $ unzip build/distributions/git-uebungen.zip

## Übungen frisch bauen lassen

    $ gradle clean assembleDist
    $ cd git-uebungen/aufgaben

    
## Per GitHub-Action gebautes Zip-File

Der Workflow [ist hier](https://github.com/bstachmann/git-workshop/actions/workflows/zip-git-uebungen.yml)

### Presentation und Website

Hosted on GitHub Pages as https://bstachmann.github.io/git-workshop/. Just push to update.

Preview on http://localhost:4000/git-workshop/ by using [GitPod local Companion](https://www.gitpod.io/blog/local-app) as proxy.

andere änderung

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

Dritte Änderung!

Ich finde Pull-Requests toll.

## Remotes

```bash
 $ git remote add github https://github.com/bstachmann/git-workshop.git
 $ git remote add gitlab https://gitlab.com/bjoern.stachmann/git-workshop.git
```
