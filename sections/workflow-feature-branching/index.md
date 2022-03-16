# Workflow
## Feature-Branching

<!-- .slide: data-background-image="workflow-feature-branching/feature-branch.png" data-background-opacity="0.15" -->


---

![Feature-Branching](workflow-feature-branching.png)


---

# In Features entwickeln


 * `branch` 25,26,38
 * `fetch` 42,47
 * Workflow: Feature-Branch 54,55,56
 * `rebase` 50,51,64

---

![Feature Branches](abb-feature-branches.png)


---

## Integrationsbranch

Erlaubt man nur Merges auf einem Branch,
spricht man von einem *Integrationsbranch*.

![Integrationsbranch](abb-integrationsbranch.png)


---

## 1st Parent History

![1st Parent History](abb-1st-parent-history.png)

```bash
git log --first-parent master
```