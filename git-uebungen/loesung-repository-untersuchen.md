---
layout: page
title: <code>repository-untersuchen</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Verzeichnisstruktur

Untersuche das Projektverzeichnis.


<pre><code>repo $ <b>ls -hal</b><br><br>total 24K<br>drwxrwxr-x 4 bjoern bjoern 4,0K Jul  8 20:41 .<br>drwxrwxr-x 3 bjoern bjoern 4,0K Jul  8 20:41 ..<br>drwxrwxr-x 2 bjoern bjoern 4,0K Jul  8 20:41 foo<br>drwxrwxr-x 8 bjoern bjoern 4,0K Jul  8 20:41 .git<br>-rw-rw-r-- 1 bjoern bjoern  253 Jul  8 20:41 hallo-welt<br>-rw-rw-r-- 1 bjoern bjoern  181 Jul  8 20:41 und-tschuess<br><br></code></pre>


Man sieht: Das Projekt enthält eine Datei, ein normales Unterverzeichnis
und natürlich auch ein `.git`-Verzeichnis, welches das Repository beherbergt.

## Lösung zu Schritt 2 - Commits ansehen

Sieh Dir die Commits an und lasse dabei Informationen 
zu Branches und Tags mit anzeigen.


<pre><code>repo $ <b>git log --oneline --decorate</b><br><br>e2c81ab (HEAD -&gt; master) Created file und-tschuess on branch master by bstachmann.<br>2231206 (tag: release1.1) : Edit file bar at line 5 on branch master by bstachmann.<br>671d500 (some-old-branch) : Edit file bar at line 1 on branch master by bstachmann.<br>4d988c6 : Edit file hallo-welt at line 3 on branch master by bstachmann.<br>6fb9302 (tag: release1.0) Created file bar on branch master by bstachmann.<br>79b23d6 Created file hallo-welt on branch master by bstachmann.<br><br></code></pre>


## Lösung zu Schritt 3 - Einzelne Commits untersuchen

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0


Hier die aktuelle Version `HEAD`:


<pre><code>repo $ <b>git show</b><br><br>commit e2c81ab9d52afc7b57d6f581fe79895eeddbfae8<br>Author: bstachmann &lt;egal&gt;<br>Date:   Thu Jul 8 20:41:15 2021 +0200<br><br>    Created file und-tschuess on branch master by bstachmann.<br><br>diff --git a/und-tschuess b/und-tschuess<br>new file mode 100644<br>index 0000000..36fe753<br>--- /dev/null<br>+++ b/und-tschuess<br>@@ -0,0 +1,12 @@<br>+line 0 created<br>+line 1 created<br>+line 2 created<br>+line 3 created<br>+line 4 created<br>+line 5 created<br>+line 6 created<br>+line 7 created<br>+line 8 created<br>+line 9 created<br>+line 10 created<br>+line 11 created<br>\ No newline at end of file<br><br></code></pre>



Und hier kommt die 1.0:


<pre><code>repo $ <b>git show release1.0~1</b><br><br>commit 79b23d6bcdd4f032760e5d3598ec004e95fef4f8<br>Author: bstachmann &lt;egal&gt;<br>Date:   Thu Jul 8 20:41:15 2021 +0200<br><br>    Created file hallo-welt on branch master by bstachmann.<br><br>diff --git a/hallo-welt b/hallo-welt<br>new file mode 100644<br>index 0000000..36fe753<br>--- /dev/null<br>+++ b/hallo-welt<br>@@ -0,0 +1,12 @@<br>+line 0 created<br>+line 1 created<br>+line 2 created<br>+line 3 created<br>+line 4 created<br>+line 5 created<br>+line 6 created<br>+line 7 created<br>+line 8 created<br>+line 9 created<br>+line 10 created<br>+line 11 created<br>\ No newline at end of file<br><br></code></pre>


## Lösung zu Schritt 4 - Inhalte vergangener Versionen untersuchen

Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.

Gebe den Inhalt der Datei `bar`,  wie er im vorigen Commit war. aus.

Hole die (ganze) vorige Version in den Workspace, um sie näher zu untersuchen.


Diese Dateien gab es in `HEAD~1`:


<pre><code>repo $ <b>git ls-tree -r HEAD~1</b><br><br>100644 blob dfc51d5ad35532e30efa54be6e19387dfcc8fcca	foo/bar<br>100644 blob d0895cfdcf547856535fe3ac92a6632311cabb98	hallo-welt<br><br></code></pre>



Und hier der Inhalt von `bar`:


<pre><code>repo $ <b>git show HEAD~1:foo/bar</b><br><br>line 0 created<br>line 1 Edit file bar at line 1 on branch master by bstachmann. / line 1 created<br>line 2 created<br>line 3 created<br>line 4 created<br>line 5 Edit file bar at line 5 on branch master by bstachmann. / line 5 created<br>line 6 created<br>line 7 created<br>line 8 created<br>line 9 created<br>line 10 created<br>line 11 created<br><br></code></pre>



Und jetzt holen wir genau diese Version in den Workspace:


<pre><code>repo $ <b>git checkout HEAD~1</b><br><br>Note: switching to 'HEAD~1'.<br><br>You are in 'detached HEAD' state. You can look around, make experimental<br>changes and commit them, and you can discard any commits you make in this<br>state without impacting any branches by switching back to a branch.<br><br>If you want to create a new branch to retain commits you create, you may<br>do so (now or later) by using -c with the switch command. Example:<br><br>  git switch -c &lt;new-branch-name&gt;<br><br>Or undo this operation with:<br><br>  git switch -<br><br>Turn off this advice by setting config variable advice.detachedHead to false<br><br>HEAD is now at 2231206 : Edit file bar at line 5 on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>ls -Rl</b><br><br>.:<br>total 8<br>drwxrwxr-x 2 bjoern bjoern 4096 Jul  8 20:41 foo<br>-rw-rw-r-- 1 bjoern bjoern  253 Jul  8 20:41 hallo-welt<br><br>./foo:<br>total 4<br>-rw-rw-r-- 1 bjoern bjoern 311 Jul  8 20:41 bar<br><br></code></pre>


## Lösung zu Schritt 5 - Branches und Tags

Zeige die Branches und Tags an.
Zeige jetzt den Commit-Graphen über alle Branches an.


<pre><code>repo $ <b>git branch -vv</b><br><br>* (HEAD detached at 2231206) 2231206 : Edit file bar at line 5 on branch master by bstachmann.<br>  feature-a                  7119ab0 : Edit file bar at line 7 on branch feature-a by bstachmann.<br>  master                     e2c81ab Created file und-tschuess on branch master by bstachmann.<br>  some-old-branch            671d500 : Edit file bar at line 1 on branch master by bstachmann.<br><br></code></pre>



<pre><code>repo $ <b>git tag</b><br><br>release1.0<br>release1.1<br><br></code></pre>


Im Commit-Graphen sieht man, wo die Branches und Tag stehen:


<pre><code>repo $ <b>git log --decorate --oneline --graph --all</b><br><br>* 7119ab0 (feature-a) : Edit file bar at line 7 on branch feature-a by bstachmann.<br>| * e2c81ab (master) Created file und-tschuess on branch master by bstachmann.<br>| * 2231206 (HEAD, tag: release1.1) : Edit file bar at line 5 on branch master by bstachmann.<br>| * 671d500 (some-old-branch) : Edit file bar at line 1 on branch master by bstachmann.<br>|/  <br>* 4d988c6 : Edit file hallo-welt at line 3 on branch master by bstachmann.<br>* 6fb9302 (tag: release1.0) Created file bar on branch master by bstachmann.<br>* 79b23d6 Created file hallo-welt on branch master by bstachmann.<br><br></code></pre>


[Zur Aufgabe](aufgabe-repository-untersuchen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

