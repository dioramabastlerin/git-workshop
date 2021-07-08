---
layout: page
title: <code>modularisierung-lfs</code>
parent: Lösungen

---
## Lösung zu Schritt 1 - LFS einrichten

Richte LFS in für `png`-Dateien ein und pushe das Ergebnis.
Erzeuge dann eine `png`-Datei (muss kein echtes Bild sein)
und pushe erneut.


<pre><code>repo $ <b>git lfs install</b><br><br>Updated git hooks.<br>Git LFS initialized.<br><br></code></pre>



<pre><code>repo $ <b>git lfs track &quot;*.png&quot;</b><br><br>Tracking &quot;*.png&quot;<br><br></code></pre>



<pre><code>repo $ <b>git add .gitattributes</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -m 'configure lfs'</b><br><br>[master bcd35fc] configure lfs<br> 1 file changed, 1 insertion(+)<br> create mode 100644 .gitattributes<br><br></code></pre>



<pre><code>repo $ <b>git push</b><br><br>To ../repo.git<br>   4658a1a..bcd35fc  master -&gt; master<br><br></code></pre>



<pre><code>repo $ <b># created file 'bild.png'</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file bild.png</b><br><br><br></code></pre>



<pre><code>repo $ <b>git add bild.png</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;Created file bild.png on branch master by bstachmann. &quot;</b><br><br>[master 2111d83] Created file bild.png on branch master by bstachmann.<br> 1 file changed, 3 insertions(+)<br> create mode 100644 bild.png<br><br></code></pre>


Schaut man sich das Diff des letzten Commits an,
 erkennt man, dass im Repository statt der eigentlichen Daten nur ein 
 Verweis gespeichert ist.


<pre><code>repo $ <b>git show --oneline</b><br><br>2111d83 Created file bild.png on branch master by bstachmann.<br>diff --git a/bild.png b/bild.png<br>new file mode 100644<br>index 0000000..b4df1e8<br>--- /dev/null<br>+++ b/bild.png<br>@@ -0,0 +1,3 @@<br>+version https://git-lfs.github.com/spec/v1<br>+oid sha256:8e7107fd666b264559c63e197d38f93f1068e100b0e9f49f758940c327a908cf<br>+size 16<br><br></code></pre>



<pre><code>repo $ <b>git push</b><br><br>Uploading LFS objects: 100% (1/1), 0 B | 0 B/s, done.<br>To ../repo.git<br>   bcd35fc..2111d83  master -&gt; master<br><br></code></pre>


## Lösung zu Schritt 2 - Ein LFS-Repo klonen

Klone das Repository.
Schaue die `png`-Datei an. 


<pre><code>$ <b>git clone repo.git myclone</b><br><br>Cloning into 'myclone'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd myclone</b><br><br><br></code></pre>



<pre><code>myclone $ <b>cat bild.png</b><br><br>erstes-fake-bild<br><br></code></pre>



<pre><code>myclone $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 3 - Noch ein Bild

Ändere das `png` in `repo` und pushe das Ergebnis. 
Untersuche in `myclone` welche Dateien von LFS verwaltet werden.


<pre><code>$ <b>cd repo</b><br><br><br></code></pre>



<pre><code>repo $ <b># Edit file bild.png</b><br><br><br></code></pre>



<pre><code>repo $ <b>git commit -am &quot;überarbeitetes bild &quot;</b><br><br>[master 7f0e3b1] überarbeitetes bild<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br><br></code></pre>



<pre><code>repo $ <b>git push</b><br><br>To ../repo.git<br>   2111d83..7f0e3b1  master -&gt; master<br><br></code></pre>



<pre><code>repo $ <b>cd ..</b><br><br><br></code></pre>



<pre><code>$ <b>cd myclone</b><br><br><br></code></pre>



<pre><code>myclone $ <b>git pull</b><br><br>Updating 2111d83..7f0e3b1<br>Fast-forward<br> bild.png | 4 ++--<br> 1 file changed, 2 insertions(+), 2 deletions(-)<br>From /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/repo<br>   2111d83..7f0e3b1  master     -&gt; origin/master<br><br></code></pre>



<pre><code>myclone $ <b>git lfs ls-files</b><br><br>d540b840e5 * bild.png<br><br></code></pre>



<pre><code>myclone $ <b>git lfs ls-files --all</b><br><br>d540b840e5 * bild.png<br>8e7107fd66 - bild.png<br><br></code></pre>



<pre><code>myclone $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 4 - Auf alte Versionen wechseln

Erstelle einen neuen Klon `myclone2` und checke dort `master~2` aus.
Schau Dir die `png`-Datei an.  


<pre><code>$ <b>git clone repo.git myclone2</b><br><br>Cloning into 'myclone2'...<br>done.<br><br></code></pre>



<pre><code>$ <b>cd myclone2</b><br><br><br></code></pre>



<pre><code>myclone2 $ <b>git -c advice.detachedHead=false checkout master~1</b><br><br>HEAD is now at 2111d83 Created file bild.png on branch master by bstachmann.<br><br></code></pre>


Beim Checkout wurde das bild nacheholt.


<pre><code>myclone2 $ <b>cat bild.png</b><br><br>erstes-fake-bild<br><br></code></pre>



<pre><code>myclone2 $ <b>cd ..</b><br><br><br></code></pre>


## Lösung zu Schritt 5 - Trouble

Erstelle einen neuen Klon `myclone3`
Entferne jetzt Hauptrepository durch `rm -rf repo.git` und versuche auf 
dann in `myclone3` auf `master~2` zu wechseln. Was passiert?                


<pre><code>$ <b>git clone repo.git myclone3</b><br><br>Cloning into 'myclone3'...<br>done.<br><br></code></pre>



<pre><code>$ <b>rm -rf repo.git repo.moved</b><br><br><br></code></pre>



<pre><code>$ <b>cd myclone3</b><br><br><br></code></pre>



<pre><code>myclone3 $ <b>git checkout HEAD~1</b><br><br>Downloading bild.png (16 B)<br>Error downloading object: bild.png (8e7107f): Smudge error: Error downloading bild.png (8e7107fd666b264559c63e197d38f93f1068e100b0e9f49f758940c327a908cf): batch request: missing protocol: &quot;/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/repo.git/info/lfs&quot;<br><br>Errors logged to /home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3/.git/lfs/logs/20210708T204123.596366239.log<br>Use `git lfs logs last` to view the log.<br>error: external filter 'git-lfs filter-process' failed<br>fatal: bild.png: smudge filter lfs failed<br><br></code></pre>



<pre><code>myclone3 $ <b>git lfs logs last</b><br><br>git-lfs/2.11.0 (GitHub; linux amd64; go 1.14.2)<br>git version 2.27.0<br><br>$ git-lfs filter-process<br>Error downloading object: bild.png (8e7107f): Smudge error: Error downloading bild.png (8e7107fd666b264559c63e197d38f93f1068e100b0e9f49f758940c327a908cf): batch request: missing protocol: &quot;/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/repo.git/info/lfs&quot;<br><br>missing protocol: &quot;/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/repo.git/info/lfs&quot;<br>batch request<br>github.com/git-lfs/git-lfs/errors.newWrappedError<br>	github.com/git-lfs/git-lfs/errors/types.go:198<br>github.com/git-lfs/git-lfs/errors.Wrap<br>	github.com/git-lfs/git-lfs/errors/errors.go:74<br>github.com/git-lfs/git-lfs/tq.(*tqClient).Batch<br>	github.com/git-lfs/git-lfs/tq/api.go:68<br>github.com/git-lfs/git-lfs/tq.Batch<br>	github.com/git-lfs/git-lfs/tq/api.go:40<br>github.com/git-lfs/git-lfs/tq.(*TransferQueue).enqueueAndCollectRetriesFor<br>	github.com/git-lfs/git-lfs/tq/transfer_queue.go:559<br>github.com/git-lfs/git-lfs/tq.(*TransferQueue).collectBatches.func1<br>	github.com/git-lfs/git-lfs/tq/transfer_queue.go:453<br>runtime.goexit<br>	runtime/asm_amd64.s:1373<br>Error downloading bild.png (8e7107fd666b264559c63e197d38f93f1068e100b0e9f49f758940c327a908cf)<br>github.com/git-lfs/git-lfs/errors.newWrappedError<br>	github.com/git-lfs/git-lfs/errors/types.go:198<br>github.com/git-lfs/git-lfs/errors.Wrapf<br>	github.com/git-lfs/git-lfs/errors/errors.go:85<br>github.com/git-lfs/git-lfs/lfs.(*GitFilter).downloadFile<br>	github.com/git-lfs/git-lfs/lfs/gitfilter_smudge.go:115<br>github.com/git-lfs/git-lfs/lfs.(*GitFilter).Smudge<br>	github.com/git-lfs/git-lfs/lfs/gitfilter_smudge.go:76<br>github.com/git-lfs/git-lfs/commands.smudge<br>	github.com/git-lfs/git-lfs/commands/command_smudge.go:127<br>github.com/git-lfs/git-lfs/commands.filterCommand<br>	github.com/git-lfs/git-lfs/commands/command_filter_process.go:120<br>github.com/spf13/cobra.(*Command).execute<br>	github.com/spf13/cobra/command.go:830<br>github.com/spf13/cobra.(*Command).ExecuteC<br>	github.com/spf13/cobra/command.go:914<br>github.com/spf13/cobra.(*Command).Execute<br>	github.com/spf13/cobra/command.go:864<br>github.com/git-lfs/git-lfs/commands.Run<br>	github.com/git-lfs/git-lfs/commands/run.go:103<br>main.main<br>	github.com/git-lfs/git-lfs/git-lfs.go:33<br>runtime.main<br>	runtime/proc.go:203<br>runtime.goexit<br>	runtime/asm_amd64.s:1373<br>Smudge error<br>github.com/git-lfs/git-lfs/errors.newWrappedError<br>	github.com/git-lfs/git-lfs/errors/types.go:198<br>github.com/git-lfs/git-lfs/errors.NewSmudgeError<br>	github.com/git-lfs/git-lfs/errors/types.go:284<br>github.com/git-lfs/git-lfs/lfs.(*GitFilter).Smudge<br>	github.com/git-lfs/git-lfs/lfs/gitfilter_smudge.go:85<br>github.com/git-lfs/git-lfs/commands.smudge<br>	github.com/git-lfs/git-lfs/commands/command_smudge.go:127<br>github.com/git-lfs/git-lfs/commands.filterCommand<br>	github.com/git-lfs/git-lfs/commands/command_filter_process.go:120<br>github.com/spf13/cobra.(*Command).execute<br>	github.com/spf13/cobra/command.go:830<br>github.com/spf13/cobra.(*Command).ExecuteC<br>	github.com/spf13/cobra/command.go:914<br>github.com/spf13/cobra.(*Command).Execute<br>	github.com/spf13/cobra/command.go:864<br>github.com/git-lfs/git-lfs/commands.Run<br>	github.com/git-lfs/git-lfs/commands/run.go:103<br>main.main<br>	github.com/git-lfs/git-lfs/git-lfs.go:33<br>runtime.main<br>	runtime/proc.go:203<br>runtime.goexit<br>	runtime/asm_amd64.s:1373<br><br>Current time in UTC: <br>2021-07-08 18:41:23<br><br>ENV:<br>LocalWorkingDir=/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3<br>LocalGitDir=/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3/.git<br>LocalGitStorageDir=/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3/.git<br>LocalMediaDir=/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3/.git/lfs/objects<br>LocalReferenceDirs=<br>TempDir=/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3/.git/lfs/tmp<br>ConcurrentTransfers=8<br>TusTransfers=false<br>BasicTransfersOnly=false<br>SkipDownloadErrors=false<br>FetchRecentAlways=false<br>FetchRecentRefsDays=7<br>FetchRecentCommitsDays=0<br>FetchRecentRefsIncludeRemotes=true<br>PruneOffsetDays=3<br>PruneVerifyRemoteAlways=false<br>PruneRemoteName=origin<br>LfsStorageDir=/home/bjoern/work/projekte/git-workshop/build/git-uebungen/loesungen/modularisierung-lfs/myclone3/.git/lfs<br>AccessDownload=none<br>AccessUpload=none<br>DownloadTransfers=basic,lfs-standalone-file<br>UploadTransfers=basic,lfs-standalone-file<br>GIT_EXEC_PATH=/usr/lib/git-core<br>GIT_PREFIX=<br><br>Client IP addresses:<br>192.168.178.20 fe80::7585:1d16:51dc:e339<br><br></code></pre>



<pre><code>myclone3 $ <b>cd ..</b><br><br><br></code></pre>


[Zur Aufgabe](aufgabe-modularisierung-lfs.html){:style="position: fixed; right: 10px; top:60px" .btn .btn-purple}

[Zum Überblick](../../ueberblick.html){:style="visibility: hidden"}

