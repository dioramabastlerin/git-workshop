package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples

fun main() {

    createCollectionOfSamples("git-uebungen") {

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


