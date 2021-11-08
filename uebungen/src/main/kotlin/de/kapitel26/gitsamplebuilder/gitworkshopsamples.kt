package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import impl.LogBuilderOptions
import impl.LogOutputFormat.*

fun main() {
    buildGitUebungen()
}

fun buildGitUebungen() {
    createCollectionOfSamples("git-uebungen", LogBuilderOptions(outputFormat = BOTH)) {

        thema("Intro") {
            halloWelt()
        }

        thema("Repository") {
            repositoryUntersuchen()
            klonen()
            sparseCeckout()
        }

        thema("Commits") {
            erstellen()
            staging()
        }

        thema("Zusammenarbeit") {
            pushFetchPull()
            pushRejected()
            integrationVonAenderungen()
            branching()
            rebasing()
        }

        // Modules
        thema("Modularisierung") {
            submodules()
            subtrees()
            repositorysZusammenfuehren()
            //lfs()
        }
    }
}


