# Rebasing


---


## Learning Goals


```
    rebase / rebase --interactive
```

 * Rebasing **copies commits**
 * **Risks** of rebasing
 * **Interactive rebasing**


---


### Rebase and Merge are related

![Rebasing and Merging](rebasing-and-merging.png)


---


### Rebase Example 1

![Rebasing Example vorher](abb-branches-beispiel-rebase-vorher.png)


---


### Rebase Example 2


![Rebasing Beispiel nachher](abb-branches-beispiel-rebase-nachher.png)


---


### Rebase

    git rebase newbase

 * Duplicated Commits will built onto `newbase`
 * `rebase` duplicates all commits contained in `HEAD`
   but not in `newbase`
   - `HEAD..newbase`
 
Without parameter: Upstream Branch.

    git rebase


---


### Rebase - Conflicts

 * theirs vs. ours


---


## Risks and Problems

 * Problems with duplicated Commits

 * Integration point are not visible


---


### Related Commands

 * `commit --amend`
 * `reset HEAD~1`
 * `cherry-pick`
 * `filter-branch`


---


### Application in **Workflows**

---


![Feature Branching with Rebasing](feature-branching-with-rebasing.png)
