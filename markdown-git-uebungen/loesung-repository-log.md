---
layout: page
title: <code>repository-log</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Verzeichnisstruktur

Untersuche das Projektverzeichnis.
Welche Dateien gibt es im Workspace? Welche Verzeichnisse?
Wo liegt das Repository?


<pre><code>repo $ <b>ll </b><br><br>total 12K<br>drwxr-xr-x 2 gitpod gitpod  17  foo<br>-rw-r--r-- 1 gitpod gitpod  12  hallo-welt<br>-rw-r--r-- 1 gitpod gitpod 375  nachher<br>-rw-r--r-- 1 gitpod gitpod   0  restaurant<br>-rw-r--r-- 1 gitpod gitpod 181  und-tschuess<br><br></code></pre>



<pre><code>repo $ <b>ll foo</b><br><br>total 4.0K<br>-rw-r--r-- 1 gitpod gitpod 342  bar<br><br></code></pre>



<pre><code>repo $ <b>ll .git</b><br><br>total 28K<br>drwxr-xr-x  2 gitpod gitpod    6  branches<br>-rw-r--r--  1 gitpod gitpod   54  COMMIT_EDITMSG<br>-rw-r--r--  1 gitpod gitpod   92  config<br>-rw-r--r--  1 gitpod gitpod   73  description<br>-rw-r--r--  1 gitpod gitpod   23  HEAD<br>drwxr-xr-x  2 gitpod gitpod 4.0K  hooks<br>-rw-r--r--  1 gitpod gitpod  477  index<br>drwxr-xr-x  2 gitpod gitpod   21  info<br>drwxr-xr-x  3 gitpod gitpod   30  logs<br>drwxr-xr-x 52 gitpod gitpod 4.0K  objects<br>drwxr-xr-x  4 gitpod gitpod   31  refs<br><br></code></pre>


Man sieht: Das Projekt enthält einige Dateien, ein Unterverzeichnis
und natürlich auch ein `.git`-Verzeichnis, welches das Repository beherbergt.

## Lösung zu Schritt 2 - Commits ansehen

Sieh Dir die Commits. 
Achte dabei auf die angezeigten Branches und Tags.


<pre><code>repo $ <b>git log --oneline</b><br><br>612a0ee Created file und-tschuess on branch master by bjoern.<br>3f11b7a : Edit file bar at line 5 on branch master by bjoern.<br>51c4f89 : Edit file bar at line 1 on branch master by bjoern.<br>f0f1b47 Edited file hallo-welt on branch master by bjoern.<br>8b7f749 Eine Zeile verschieben<br>647afca Noch ein paar neue Zeilen<br>ef159f5 Verschiebe eine  Zeile<br>38ad7a7 Created file restaurant on branch master by bjoern.<br>fb0ebcd Kopiere eine Zeile aus 'bar'<br>4f3774e Ergänze eine Zeile<br>314b299 Benenne die Datei im<br>c746e19 Ergänze zwei zeilen<br>03f6630 Beginne mit leerer Datei<br>92fea2a Created file bar on branch master by bjoern.<br>fb73daf Created file hallo-welt on branch master by bjoern.<br><br></code></pre>


## Lösung zu Schritt 3 - ⭐ Commits ansehen: Datei-Statistik

Sieh Dir die Commits an. 
Lase dir dabei die Statistik anzeigen, 
d.h. wie viele Zeilen in welcher Datei geändert wurden.


<pre><code>repo $ <b>git log --stat</b><br><br>commit 612a0ee90fedcfcfce170e568ba9607f41655f0c<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Created file und-tschuess on branch master by bjoern.<br><br> und-tschuess | 12 ++++++++++++<br> 1 file changed, 12 insertions(+)<br><br>commit 3f11b7a430d69d0ee8cce52f6e14194499c5ae55<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file bar at line 5 on branch master by bjoern.<br><br> foo/bar | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br>commit 51c4f89f7185d542bc9480bb4b805930187289a7<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    : Edit file bar at line 1 on branch master by bjoern.<br><br> foo/bar | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br>commit f0f1b47ab2a76d24c1ca88f9cbc080a922314973<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Edited file hallo-welt on branch master by bjoern.<br><br> hallo-welt | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br>commit 8b7f749049d39c440a8cb5961a2b7828fc268fbd<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Eine Zeile verschieben<br><br> nachher | 2 +-<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br>commit 647afca68d43ea53d40aeb1d1409b7ce52221c30<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Noch ein paar neue Zeilen<br><br> nachher | 5 ++++-<br> 1 file changed, 4 insertions(+), 1 deletion(-)<br><br>commit ef159f5e8ae5d8699f786fec7c212215593b0ead<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Verschiebe eine  Zeile<br><br> nachher    | 4 +++-<br> restaurant | 1 -<br> 2 files changed, 3 insertions(+), 2 deletions(-)<br><br>commit 38ad7a70115ed56b68478d3cfbbd58f4d4886320<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Created file restaurant on branch master by bjoern.<br><br> restaurant | 1 +<br> 1 file changed, 1 insertion(+)<br><br>commit fb0ebcdbba79d688262eddaff634607e74a1b2f3<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Kopiere eine Zeile aus 'bar'<br><br> nachher | 3 ++-<br> 1 file changed, 2 insertions(+), 1 deletion(-)<br><br>commit 4f3774e422e62d77af26c97ece03a60bdb0a3a7b<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Ergänze eine Zeile<br><br> nachher | 3 ++-<br> 1 file changed, 2 insertions(+), 1 deletion(-)<br><br>commit 314b2998fa7aaea4b194a625c985246eb740060a<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Benenne die Datei im<br><br> foo/vorher =&gt; nachher | 0<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br><br>commit c746e19521a16276c1014e0823147e667060bcfd<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Ergänze zwei zeilen<br><br> foo/vorher | 2 ++<br> 1 file changed, 2 insertions(+)<br><br>commit 03f6630f696dac20a83abd1dbc97eaa654be8879<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Beginne mit leerer Datei<br><br> foo/vorher | 0<br> 1 file changed, 0 insertions(+), 0 deletions(-)<br><br>commit 92fea2abf8c7fe86c6d8cc10bfe40fe321e6c93f<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Created file bar on branch master by bjoern.<br><br> foo/bar | 12 ++++++++++++<br> 1 file changed, 12 insertions(+)<br><br>commit fb73dafa7a93f088977e80742665c905ae6a5488<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Created file hallo-welt on branch master by bjoern.<br><br> hallo-welt | 1 +<br> 1 file changed, 1 insertion(+)<br><br></code></pre>


## Lösung zu Schritt 4 - Einzelne Commits untersuchen

Zeige Details zur aktuellen Version,
und zur Vorgängerversion des Releases 1.0


Hier die aktuelle Version `HEAD`:


<pre><code>repo $ <b>git show</b><br><br>commit 612a0ee90fedcfcfce170e568ba9607f41655f0c<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Created file und-tschuess on branch master by bjoern.<br><br>diff --git a/und-tschuess b/und-tschuess<br>new file mode 100644<br>index 0000000..36fe753<br>--- /dev/null<br>+++ b/und-tschuess<br>@@ -0,0 +1,12 @@<br>+line 0 created<br>+line 1 created<br>+line 2 created<br>+line 3 created<br>+line 4 created<br>+line 5 created<br>+line 6 created<br>+line 7 created<br>+line 8 created<br>+line 9 created<br>+line 10 created<br>+line 11 created<br>\ No newline at end of file<br><br></code></pre>



Und hier kommt die 1.0:


<pre><code>repo $ <b>git show release1.0~1</b><br><br>commit 647afca68d43ea53d40aeb1d1409b7ce52221c30<br>Author: bjoern &lt;kapitel26blog@gmail.com&gt;<br>Date:   Thu Jul 29 00:00:00 2021 +0000<br><br>    Noch ein paar neue Zeilen<br><br>diff --git a/nachher b/nachher<br>index dc66148..c35fa99 100644<br>--- a/nachher<br>+++ b/nachher<br>@@ -3,4 +3,7 @@ Und das ist wohl doch sehr lange her. Wie man sieht.<br> Nach der Umbenennung<br> Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br> Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>-Und eine, die nichts damit zu tun hat.<br>\ No newline at end of file<br>+Und eine, die nichts damit zu tun hat.<br>+Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>+dazwischen.<br>+Ende<br>\ No newline at end of file<br><br></code></pre>


## Lösung zu Schritt 5 - Inhalte vergangener Versionen untersuchen

Lasse Dir anzeigen welche Dateien es im vorigen Commit gab.

Gebe den Inhalt der Datei `bar` so aus,  wie er im vorigen Commit war.


Diese Dateien gab es in `HEAD~1`:


<pre><code>repo $ <b>git ls-tree -r HEAD~1</b><br><br>100644 blob 8b4d0e47f0e3b71630056ed9d3cf135f30e64efd	foo/bar<br>100644 blob c57eff55ebc0c54973903af5f72bac72762cf4f4	hallo-welt<br>100644 blob 32b08868b00465cca451251689dda4c9d83b2d85	nachher<br>100644 blob e69de29bb2d1d6434b8b29ae775ad8c2e48c5391	restaurant<br><br></code></pre>



Und hier der Inhalt von `bar`:


<pre><code>repo $ <b>git show HEAD~1:foo/bar</b><br><br>line 0 created<br>line 1 Edit file bar at line 1 on branch master by bjoern. / Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>line 2 created<br>line 3 created<br>line 4 created<br>line 5 Edit file bar at line 5 on branch master by bjoern. / line 5 created<br>line 6 created<br>line 7 created<br>line 8 created<br>line 9 created<br>line 10 created<br>line 11 created<br><br></code></pre>


## Lösung zu Schritt 6 - ⭐ Herkunft von Zeilen ermitteln

Es geht darum für die Datei `nachher` Folgendes zu ermitteln:

* Für jede Zeile zeigen, in welchem Commit sie zuletzt bearbeitet wurde.
* Innerhalb der Datei wurden Zeilen verschoben. Welche?
* Es wurden auch Zeilen aus anderen Dateien verschoben und kopiert. Welche?


<pre><code>repo $ <b>git blame nachher -s -w</b><br><br>c746e195 foo/vorher 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>c746e195 foo/vorher 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4f3774e4 nachher    3) Nach der Umbenennung<br>fb0ebcdb nachher    4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>ef159f5e nachher    5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>ef159f5e nachher    6) Und eine, die nichts damit zu tun hat.<br>647afca6 nachher    7) dazwischen.<br>8b7f7490 nachher    8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>647afca6 nachher    9) Ende<br><br></code></pre>


Man sieht, in welchem Commit die Zeilen zuletzt bearbeitet wurden, auch über Umbenennungen hinweg.


<pre><code>repo $ <b>git blame nachher -s -w -M --show-number</b><br><br>c746e195 foo/vorher 1 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>c746e195 foo/vorher 2 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4f3774e4 nachher    3 3) Nach der Umbenennung<br>fb0ebcdb nachher    4 4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>ef159f5e nachher    5 5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>ef159f5e nachher    6 6) Und eine, die nichts damit zu tun hat.<br>647afca6 nachher    8 7) dazwischen.<br>647afca6 nachher    7 8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>647afca6 nachher    9 9) Ende<br><br></code></pre>


Die Zeilennummern zeigen, welche Zeilen verschoben wurden.


<pre><code>repo $ <b>git blame nachher -s -w -M -C</b><br><br>c746e195 foo/vorher 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>c746e195 foo/vorher 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4f3774e4 nachher    3) Nach der Umbenennung<br>fb0ebcdb nachher    4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>38ad7a70 restaurant 5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>ef159f5e nachher    6) Und eine, die nichts damit zu tun hat.<br>647afca6 nachher    7) dazwischen.<br>647afca6 nachher    8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>647afca6 nachher    9) Ende<br><br></code></pre>


Hier sieht eine Verschiebung aus der Datei `restaurant`.


<pre><code>repo $ <b>git blame nachher -s -w -M -C -C -C</b><br><br>c746e195 foo/vorher 1) Diese Zeilen wurden also ganz am Anfang geschrieben.<br>c746e195 foo/vorher 2) Und das ist wohl doch sehr lange her. Wie man sieht.<br>4f3774e4 nachher    3) Nach der Umbenennung<br>92fea2ab foo/bar    4) Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>38ad7a70 restaurant 5) Eine sehr lange Zeile aus 'restaurant', die verschoben wird.<br>ef159f5e nachher    6) Und eine, die nichts damit zu tun hat.<br>647afca6 nachher    7) dazwischen.<br>647afca6 nachher    8) Eine ebenfalls recht lange Zeile, die demnächst auch verschoben werden soll.<br>647afca6 nachher    9) Ende<br><br></code></pre>


Hier sieht man, dass Inhalte aus einer anderen Datei `foo/bar` kopiert wurden.

[Zur Aufgabe](aufgabe-repository-log.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

