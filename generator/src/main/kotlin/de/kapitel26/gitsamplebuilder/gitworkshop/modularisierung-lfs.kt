package de.kapitel26.gitsamplebuilder.gitworkshop

import impl.CollectionOfSamples

fun CollectionOfSamples.lfs() {

    createAufgabenFolge("lfs") {

        createIntro(
                """Git LFS""",
                """
                Hier geht es um das [Git LFS (Large File System)](https://git-lfs.github.com/),
                das es ermöglicht große Artefakte, z. B. Bilder, Filme, Audio, 
                mit Git zu versionieren aber außerhalb des Repositorys zu speichern,
                so dass nicht bei jedem Clone alle Bilder geladen werden,
                sonden nur die für ein `checkout` jeweils benötigten.

                ## Installaction
                
                Installation unter debian/ubuntu:

                    $ sudo apt-get update
                    $ sudo apt-get install git-lfs

                ## Tipps
                
                * `git lfs install` richtet LFS in einem Repository ein.
                * `git lfs-track <pattern>` legt fest welche Dateien im LFS gespeichert werden sollen.\
                  Diese Einstellung wird in der Datei `.gitattributes` hinterlegt, 
                  die man mitversionieren sollte.
                * `git lfs ls-files` zeigt, welche Dateien im `HEAD` durch LFS verwaltet werden.\
                   Mit `--all` werden auch Dateien aus der Historie angezeigt.
                * `git lfs logs` zeigt Details, falls mal etwas schief geht.   


            """
        ) {
            createRepo("repo.git", "--bare") {
                createClone("../repo") {
                    createFileAndCommit("README.md")
                    git( "push" )
                }
            }

        }

        inRepo() {
            createAufgabe(
                "LFS einrichten", """
                    Richte LFS in für `png`-Dateien ein und pushe das Ergebnis.
                    Erzeuge dann eine `png`-Datei (muss kein echtes Bild sein)
                    und pushe erneut.
            """) {
                git("lfs install")
                git("lfs track \"*.png\"")
                git("add .gitattributes")
                git("commit -m 'configure lfs'")
                git("push")

                createFileAndCommit("bild.png", ) { content = "erstes-fake-bild" }
                markdown("""Schaut man sich das Diff des letzten Commits an,
                    | erkennt man, dass im Repository statt der eigentlichen Daten nur ein 
                    | Verweis gespeichert ist.""".trimMargin())
                git("show --oneline")

                git("push")
            }
        }

        createAufgabe(
            "Ein LFS-Repo klonen", """
                Klone das Repository.
                Schaue die `png`-Datei an. 
        """) {
            git("clone repo.git myclone")
            inRepo("myclone") {
                bash("cat bild.png")
            }
        }
        createAufgabe(
            "Noch ein Bild", """
                Ändere das `png` in `repo` und pushe das Ergebnis. 
                Untersuche in `myclone` welche Dateien von LFS verwaltet werden.
        """) {
            inRepo {
                inFile ("bild.png") {
                    content = "zweites-fake-bild"
                    commit("überarbeitetes bild")
                }
                git("push")
            }
            inRepo("myclone") {
                git("pull")
                git("lfs ls-files")
                git("lfs ls-files --all")
            }
        }

        createAufgabe(
            "Auf alte Versionen wechseln", """
                Erstelle einen neuen Klon `myclone2` und checke dort `master~2` aus.
                Schau Dir die `png`-Datei an.  
        """) {
            git("clone repo.git myclone2")
            inRepo("myclone2") {
                git("-c advice.detachedHead=false checkout master~1")
                markdown("Beim Checkout wurde das bild nacheholt.")
                bash("cat bild.png")
            }
        }

        createAufgabe(
            "Trouble", """
                Erstelle einen neuen Klon `myclone3`
                Entferne jetzt Hauptrepository durch `rm -rf repo.git` und versuche auf 
                dann in `myclone3` auf `master~2` zu wechseln. Was passiert?                
        """) {
            git("clone repo.git myclone3")

            bash("rm -rf repo.git repo.moved")

            inRepo("myclone3") {
                git("checkout HEAD~1", acceptableExitCodes = setOf(128))
                git("lfs logs last")
            }
        }

    }
}