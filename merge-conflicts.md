# Gemeinsam auf dem `master` arbeiten

_________________________________________

## Lernziel

| Konzept              | Begriff              | Befehl               |
|----------------------|----------------------|----------------------|
| Remote Branch        | `origin/master`      | `fetch`              |
| Merge                | Verzweigung          | `log --grap --all`   |
| Merge-Konflikt       | 3-Wege-Merge         | `HEAD, MERGE_HEAD`   |
| Inhaltlicher Konflikt| Fast-Forward         | `log A..B`           |

_________________________________________

  * `push` 40,41,45,47
_________________________________________

  * `fetch`
    - `log --oneline --all --graph -10`
_________________________________________

### Merge

![Push, Pull and Merge Conflicts](abb/push-pull-merge.jpg)


_________________________________________

### `merge` 29,30,31,43,44
       - `config --global merge.conflictStyle diff3`
       - `fast-forward` 34
       - Konflikte 41
       - `--ours`, `--theirs` 32
       - `merge --abort`
       - `mergetool` 32
       - `checkout` 38
       - `diff HEAD^1`
       - `diff HEAD^2`
_________________________________________

     * Wenn sich auf einer Seite des Merges nichts getan hat,
       macht Git idR. ein *fast-forward*:
       ![Fast-Forward](abb/fast-forward.jpg)
     * Techniken, die helfen, Probleme mit Merges zu mildern:
       ![Merges mildern](abb/merges-mildern.jpg)
     * Wenn man einem Merge per `revert` rückgängig macht,
       muss man dieses später mit einem weiteren `revert` rückgängig machen,
       um die Änderungen zu reaktivieren:
       ![Reverting Merges](abb/reverting-merges.jpg)
     * [Merges und  widersprüchliche Umbenennungen](renames-und-merges.md)

_________________________________________

# Workflow: Gemeinsamem auf `master` arbeiten 48,52
    - Was wurde reingemerged
      - `log HEAD^2..HEAD`
      - `diff HEAD^2...HEAD`


