package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import impl.LogBuilderOptions
import impl.LogOutputFormat.HTML

fun main() {

    createCollectionOfSamples("git-uebungen", LogBuilderOptions(outputFormat = HTML)) {

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

        thema("Branching") {
            branching()
        }

        thema("Rebasing") {
            rebasing()
        }

        // Branches

        // Workflows

        // Rebasing

        // Modules
    }
}


