---
layout: page
title: Git Starter Kit
description: Git-Befehle, die in den Übungen verwendet werden.
nav_order: 90
---
# Git Starter Kit

## Repository untersuchen

### Notation: Revision

    git help revisions

Git Versionsnummer sind SHA1-Hashes,

    05ef20d8e641f09ec24c736e97c7e5604df96c48

und dürfen abgekürzt werden (sofern im aktuellen Repo eindeutig).

    05ef20d8

Branches und Tags sind zeiger auf Commits (ähnliche Konvention, wie Dateinamen)

    master

Slashes als Trenner sind üblich.

    feature/cheat-sheet

Mit ~ erreicht man Vorgänger-Commit.

    05ef20d8~2

Mit ^2 erreicht man den zweiten Vorgänger eines Merges.

    05ef20d8^2

## Befehle

    git clone git http://0.0.0.0:8080/git/bjoern/git-workshop.git my-git-workshop


    git log --oneline --graph -10

Historie über Umbenennung:

    git log --follow my-file myDir/

    git show HEAD~2

    git show HEAD~2:myfilegit

    git diff 3faa78 893edc --stat

Achtung "Detache HEAD" (Wenn man darauf weiter arbeiten möchte -> Branch erstellen)

    git checkout 3faa78

# Commits erstellen

    git status

    git commit -am "Ein Commit-Kommentar"

    git add my-file

    git tag my-tag 3faa78 -m "Eine Nachricht"

    git diff

## Remote Repositorys

Schreibabkürzung für andere Repositorys.

    git remote add my-remote https://...

    git remote -v

    git branch -r

    git push

    git log origin/master..
    git diff origin/master...

    git fetch

    git pull


## Oops

    git stash -u
    git stash pop

    git revert HEAD~2

    git checkout HEAD~4 -- my-file

     git config --global merge.conflictstyle=diff3

     git log HEAD^1..
     git log HEAD^2..


