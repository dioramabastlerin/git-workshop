---
layout: page
title: <code>repository-untersuchen</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - Inhalte vergangener Versionen untersuchen

Lasse Dir anzeigen welche Dateien es in vorigen Commit gab.

Gebe den Inhalt der Datei `bar`,  wie er im vorigen Commit war. aus.

Wechsle zum vorigen Commit, und untersuche, wie der Workspace dannn aussieht.
Wechsle dann wieder auf `master` zurück.


Diese Dateien gab es in `HEAD~1`:


<pre><code>repo $ <b>git ls-tree -r HEAD~1</b><br><br>100644 blob 8b4d0e47f0e3b71630056ed9d3cf135f30e64efd	foo/bar<br>100644 blob c57eff55ebc0c54973903af5f72bac72762cf4f4	hallo-welt<br>100644 blob 32b08868b00465cca451251689dda4c9d83b2d85	nachher<br>100644 blob e69de29bb2d1d6434b8b29ae775ad8c2e48c5391	restaurant<br><br></code></pre>



Und hier der Inhalt von `bar`:


<pre><code>repo $ <b>git show HEAD~1:foo/bar</b><br><br>line 0 created<br>line 1 Edit file bar at line 1 on branch master by bjoern. / Eine wirklich ziemlich lange Zeile in der Datei 'bar'<br>line 2 created<br>line 3 created<br>line 4 created<br>line 5 Edit file bar at line 5 on branch master by bjoern. / line 5 created<br>line 6 created<br>line 7 created<br>line 8 created<br>line 9 created<br>line 10 created<br>line 11 created<br><br></code></pre>



Und jetzt holen wir genau diese Version in den Workspace:


<pre><code>repo $ <b>git switch --detach HEAD~1</b><br><br>HEAD is now at 3f11b7a : Edit file bar at line 5 on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>ll </b><br><br>total 8.0K<br>drwxr-xr-x 2 gitpod gitpod  17  foo<br>-rw-r--r-- 1 gitpod gitpod  12  hallo-welt<br>-rw-r--r-- 1 gitpod gitpod 375  nachher<br>-rw-r--r-- 1 gitpod gitpod   0  restaurant<br><br></code></pre>



<pre><code>repo $ <b>git switch master</b><br><br>Previous HEAD position was 3f11b7a : Edit file bar at line 5 on branch master by bjoern.<br>Switched to branch 'master'<br><br></code></pre>


## Lösung zu Schritt 2 - Branches und Tags

Zeige die Branches und Tags an.
Zeige jetzt den Commit-Graphen über alle Branches an.


<pre><code>repo $ <b>git branch -vv</b><br><br>  feature-a       f7d22b6 : Edit file bar at line 7 on branch feature-a by bjoern.<br>* master          612a0ee Created file und-tschuess on branch master by bjoern.<br>  some-old-branch 51c4f89 : Edit file bar at line 1 on branch master by bjoern.<br><br></code></pre>



<pre><code>repo $ <b>git tag</b><br><br>release1.0<br>release1.1<br><br></code></pre>


Im Commit-Graphen sieht man, wo die Branches und Tag stehen:


<pre><code>repo $ <b>git log --decorate --oneline --graph --all</b><br><br>* f7d22b6 (feature-a) : Edit file bar at line 7 on branch feature-a by bjoern.<br>| * 612a0ee (HEAD -&gt; master) Created file und-tschuess on branch master by bjoern.<br>| * 3f11b7a (tag: release1.1) : Edit file bar at line 5 on branch master by bjoern.<br>| * 51c4f89 (some-old-branch) : Edit file bar at line 1 on branch master by bjoern.<br>|/  <br>* f0f1b47 Edited file hallo-welt on branch master by bjoern.<br>* 8b7f749 (tag: release1.0) Eine Zeile verschieben<br>* 647afca Noch ein paar neue Zeilen<br>* ef159f5 Verschiebe eine  Zeile<br>* 38ad7a7 Created file restaurant on branch master by bjoern.<br>* fb0ebcd Kopiere eine Zeile aus 'bar'<br>* 4f3774e Ergänze eine Zeile<br>* 314b299 Benenne die Datei im<br>* c746e19 Ergänze zwei zeilen<br>* 03f6630 Beginne mit leerer Datei<br>* 92fea2a Created file bar on branch master by bjoern.<br>* fb73daf Created file hallo-welt on branch master by bjoern.<br><br></code></pre>


## Lösung zu Schritt 3 - ⭐ Hole alten Stand einer einzelnen Datei zurück.

Die Datei `hallo-welt` wurde nach dem `release1.0` bearbeitet.
Dem Kunden gefällt das nicht. Stelle den alten Zustand mit
einem neuen Commit wieder her. 

In `release1.0` sah es os aus:


<pre><code>repo $ <b>git show release1.0:hallo-welt</b><br><br>Hallo Welt!<br><br></code></pre>


Jetzt sieh es so aus:


<pre><code>repo $ <b>git show HEAD:hallo-welt</b><br><br>Hello World!<br><br></code></pre>


Gezieltes zurückholen:


<pre><code>repo $ <b>git restore -s release1.0 hallo-welt</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am 'Zurückgeholt'</b><br><br>[master ff4201b] Zurückgeholt<br> 1 file changed, 1 insertion(+), 1 deletion(-)<br><br></code></pre>


Im Commit-Graphen sieht man, wo die Branches und Tag stehen:

Jetzt sieh es so wieder so aus:


<pre><code>repo $ <b>git show HEAD:hallo-welt</b><br><br>Hallo Welt!<br><br></code></pre>


[Zur Aufgabe](aufgabe-repository-untersuchen.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

