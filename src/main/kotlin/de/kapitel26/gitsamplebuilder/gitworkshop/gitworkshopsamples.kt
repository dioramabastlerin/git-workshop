package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import impl.LogBuilderOptions
import impl.LogOutputFormat.MARKDOWN

fun main() {

    createCollectionOfSamples("git-uebungen", LogBuilderOptions(outputFormat = MARKDOWN)) {

        thema("Repository") {
            repositoryUntersuchen()
        }

        thema("Commits") {
            commitsErstellen()
        }

        thema("Klone") {
            cloning()
            pushFetchPull()
            pushRejected()
        }

        thema("Merges") {
            integrationOfChanges()
        }

        // Branches

        // Workflows

        // Rebasing

        // Modules
    }
}


