
# Scripting

 * Environment Variables
   - https://git-scm.com/book/en/v2/Git-Internals-Environment-Variables
 * `git`-Options
   - `-C`
   - `--git-dir`
 * Git-Configuration
   - `git -c`
 * Git output
   - `--no-color`
 * `xargs`
 * for-each-ref
 * Reference-Clones


---
 

## Example

```bash
git for-each-ref --shell --format="%(refname)" refs/heads/tmp/* | xargs git branch -d 
```
 