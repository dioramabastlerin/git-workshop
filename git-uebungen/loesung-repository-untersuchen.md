---
layout: page
title: <code>repository-untersuchen</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Verzeichnisstruktur

Untersuche das Projektverzeichnis.


<pre><code>repo $ <b>ls -1AF</b><br><br>foo/<br>.git/<br>hallo-welt<br>nachher<br>restaurant<br>und-tschuess<br><br></code></pre>



<pre><code>repo $ <b>ls -1AF foo</b><br><br>bar<br><br></code></pre>



<pre><code>repo $ <b>ls -1AF .git</b><br><br>branches/<br>COMMIT_EDITMSG<br>config<br>description<br>HEAD<br>hooks/<br>index<br>info/<br>logs/<br>objects/<br>refs/<br><br></code></pre>


Man sieht: Das Projekt enthält einige Dateien, ein Unterverzeichnis
und natürlich auch ein `.git`-Verzeichnis, welches das Repository beherbergt.

## Lösung zu Schritt 2 - Commits ansehen

Sieh Dir die Commits an und lasse dabei Informationen 
zu Branches und Tags mit anzeigen.


<pre><code>repo $ <b>git log --oneline --decorate</b><br><br>11f8864 (HEAD -&gt; master) Created file und-tschuess on branch master by bjoern.<br>b40b9b0 (tag: release1.1) : Edit file bar at line 5 on branch master by bjoern.<br>0e645de (some-old-branch) : Edit file bar at line 1 on branch master by bjoern.<br>c83acbb : Edit file hallo-welt at line 3 on branch master by bjoern.<br>e618854 (tag: release1.0) Eine Zeile verschieben<br>85b74ca Noch ein paar neue Zeilen<br>316068a Verschiebe eine  Zeile<br>1f4fbb6 Created file restaurant on branch master by bjoern.<br>de32a96 Kopiere eine Zeile aus 'bar'<br>4b6dac9 Ergänze eine Zeile<br>d5bc10e Benenne die Datei im<br>2704dee Ergänze zwei zeilen<br>1d54b83 Beginne mit leerer Datei<br>66cecb0 Created file bar on branch master by bjoern.<br>2bb8e2a Created file hallo-welt on branch master by bjoern.<br><br></code></pre>


## Lösung zu Schritt 3 - Einzelne Commits untersuchen

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0


Hier die aktuelle Version `HEAD`:


<pre><code>repo $ <b>git show</b><br><br>commit 11f88643703a930b9ced102662b1cd8b93de0271<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Created file und-tschuess on branch master by bjoern.<br><br>diff --git a/und-tschuess b/und-tschuess<br>new file mode 100644<br>index 0000000..36fe753<br>--- /dev/null<br>+++ b/und-tschuess<br>@@ -0,0 +1,12 @@<br>+line 0 created<br>+line 1 created<br>+line 2 created<br>+line 3 created<br>+line 4 created<br>+line 5 created<br>+line 6 created<br>+line 7 created<br>+line 8 created<br>+line 9 created<br>+line 10 created<br>+line 11 created<br>\ No newline at end of file<br><br></code></pre>



Und hier kommt die 1.0:


<pre><code>repo $ <b>git show release1.0~1</b><br><br>commit 85b74ca8c78054ab3ad9397e6708e15e909b7afa<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Noch ein paar neue Zeilen<br><br>diff --git a/nachher b/nachher<br>index dc66148..c35fa99 100644<br>--- a/nachher<br>+++ b/nachher<br>@@ -3,4 +3,7 @@ Und das ist wohl doch sehr lange her. Wie man sieht.<br> Nach der Umbenennung<br> Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br> Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>-Und eine, die nichts damit zu tun hat.<br>\ No newline at end of file<br>+Und eine, die nichts damit zu tun hat.<br>+Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>+dazwischen.<br>+Ende<br>\ No newline at end of file<br><br></code></pre>


## Lösung zu Schritt 4 - Inhalte vergangener Versionen untersuchen

Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.

Gebe den Inhalt der Datei `bar`,  wie er im vorigen Commit war. aus.

Hole die (ganze) vorige Version in den Workspace, um sie näher zu untersuchen.


Diese Dateien gab es in `HEAD~1`:


<pre><code>repo $ <b>git ls-tree -r HEAD~1</b><br><br>100644 blob 8b4d0e47f0e3b71630056ed9d3cf135f30e64efd	foo/bar<br>100644 blob 95de6572bd879ff4f0c1e2e5bd138cd33e3025df	hallo-welt<br>100644 blob 32b08868b00465cca451251689dda4c9d83b2d85	nachher<br>100644 blob e69de29bb2d1d6434b8b29ae775ad8c2e48c5391	restaurant<br><br></code></pre>



Und hier der Inhalt von `bar`:


<pre><code>repo $ <b>git show HEAD~1:foo/bar</b><br><br>line 0 created<br>line 1 Edit file bar at line 1 on branch master by bjoern. / Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>line 2 created<br>line 3 created<br>line 4 created<br>line 5 Edit file bar at line 5 on branch master by bjoern. / line 5 created<br>line 6 created<br>line 7 created<br>line 8 created<br>line 9 created<br>line 10 created<br>line 11 created<br><br></code></pre>



Und jetzt holen wir genau diese Version in den Workspace:


<pre><code>repo $ <b>git checkout HEAD~1</b><br><br>Note: switching to 'HEAD~1'.<br><br>You are in 'detached HEAD' state. You can look around, make experimental<br>changes and commit them, and you can discard any commits you make in this<br>state without impacting any branches by switching back to a branch.<br><br>If you want to create a new branch to retain commits you create, you may<br>do so (now or later) by using -c with the switch command. Example:<br><br>  git switch -c &lt;new-branch-name&gt;<br><br>Or undo this operation with:<br><br>  git switch -<br><br>Turn off this advice by setting config variable advice.detachedHead to false<br><br>HEAD is now at b40b9b0 : Edit file bar at line 5 on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>ls -Rl --time-style=+&quot;&quot;</b><br><br>.:<br>total 8<br>drwxr-xr-x 2 gitpod gitpod  17  foo<br>-rw-r--r-- 1 gitpod gitpod 249  hallo-welt<br>-rw-r--r-- 1 gitpod gitpod 375  nachher<br>-rw-r--r-- 1 gitpod gitpod   0  restaurant<br><br>./foo:<br>total 4<br>-rw-r--r-- 1 gitpod gitpod 342  bar<br><br></code></pre>


## Lösung zu Schritt 5 - Branches und Tags

Zeige die Branches und Tags an.
Zeige jetzt den Commit-Graphen über alle Branches an.


<pre><code>repo $ <b>git branch -vv</b><br><br>* (HEAD detached at b40b9b0) b40b9b0 : Edit file bar at line 5 on branch master by bjoern.<br>  feature-a                  75f504a : Edit file bar at line 7 on branch feature-a by bjoern.<br>  master                     11f8864 Created file und-tschuess on branch master by bjoern.<br>  some-old-branch            0e645de : Edit file bar at line 1 on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git tag</b><br><br>release1.0<br>release1.1<br><br></code></pre>


Im Commit-Graphen sieht man, wo die Branches und Tag stehen:


<pre><code>repo $ <b>git log --decorate --oneline --graph --all</b><br><br>* 75f504a (feature-a) : Edit file bar at line 7 on branch feature-a by bjoern.<br>| * 11f8864 (master) Created file und-tschuess on branch master by bjoern.<br>| * b40b9b0 (HEAD, tag: release1.1) : Edit file bar at line 5 on branch master by bjoern.<br>| * 0e645de (some-old-branch) : Edit file bar at line 1 on branch master by bjoern.<br>|/  <br>* c83acbb : Edit file hallo-welt at line 3 on branch master by bjoern.<br>* e618854 (tag: release1.0) Eine Zeile verschieben<br>* 85b74ca Noch ein paar neue Zeilen<br>* 316068a Verschiebe eine  Zeile<br>* 1f4fbb6 Created file restaurant on branch master by bjoern.<br>* de32a96 Kopiere eine Zeile aus 'bar'<br>* 4b6dac9 Ergänze eine Zeile<br>* d5bc10e Benenne die Datei im<br>* 2704dee Ergänze zwei zeilen<br>* 1d54b83 Beginne mit leerer Datei<br>* 66cecb0 Created file bar on branch master by bjoern.<br>* 2bb8e2a Created file hallo-welt on branch master by bjoern.<br><br></code></pre>


## Lösung zu Schritt 6 - ⭐ Herkunft von Zeilen ermitteln

Es geht darum für die Datei `nachher` Folgendes zu ermitteln:

* Für jede Zeile zeigen, in welchem Commit sie zuletzt bearbeitet wurde.
* Innerhalb der Datei wurden Zeilen verschoben. Welche?
* Es wurden auch Zeilen aus anderen Dateien verschoben und kopiert. Welche?


<pre><code>repo $ <b>git blame nachher -s -w</b><br><br>2704deef foo/vorher 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>2704deef foo/vorher 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4b6dac92 nachher    3) Nach der Umbenennung<br>de32a96c nachher    4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>316068a0 nachher    5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>316068a0 nachher    6) Und eine, die nichts damit zu tun hat.<br>85b74ca8 nachher    7) dazwischen.<br>e6188543 nachher    8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>85b74ca8 nachher    9) Ende<br><br></code></pre>


Man sieht, in welchem Commit die Zeilen zuletzt bearbeitet wurden, auch über Umbenennungen hinweg.


<pre><code>repo $ <b>git blame nachher -s -w -M --show-number</b><br><br>2704deef foo/vorher 1 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>2704deef foo/vorher 2 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4b6dac92 nachher    3 3) Nach der Umbenennung<br>de32a96c nachher    4 4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>316068a0 nachher    5 5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>316068a0 nachher    6 6) Und eine, die nichts damit zu tun hat.<br>85b74ca8 nachher    8 7) dazwischen.<br>85b74ca8 nachher    7 8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>85b74ca8 nachher    9 9) Ende<br><br></code></pre>


Die Zeilennummern zeigen, welche Zeilen verschoben wurden.


<pre><code>repo $ <b>git blame nachher -s -w -M -C</b><br><br>2704deef foo/vorher 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>2704deef foo/vorher 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4b6dac92 nachher    3) Nach der Umbenennung<br>de32a96c nachher    4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>1f4fbb6d restaurant 5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>316068a0 nachher    6) Und eine, die nichts damit zu tun hat.<br>85b74ca8 nachher    7) dazwischen.<br>85b74ca8 nachher    8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>85b74ca8 nachher    9) Ende<br><br></code></pre>


Hier sieht eine Verschiebung aus der Datei `restaurant`.


<pre><code>repo $ <b>git blame nachher -s -w -M -C -C -C</b><br><br>2704deef foo/vorher 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>2704deef foo/vorher 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4b6dac92 nachher    3) Nach der Umbenennung<br>66cecb0c foo/bar    4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>1f4fbb6d restaurant 5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>316068a0 nachher    6) Und eine, die nichts damit zu tun hat.<br>85b74ca8 nachher    7) dazwischen.<br>85b74ca8 nachher    8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>85b74ca8 nachher    9) Ende<br><br></code></pre>


Hier sieht man, dass Inhalte aus einer anderen Datei `foo/bar` kopiert wurden.

[Zur Aufgabe](aufgabe-repository-untersuchen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

