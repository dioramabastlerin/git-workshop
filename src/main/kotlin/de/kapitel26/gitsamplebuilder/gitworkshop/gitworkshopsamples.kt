package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples

fun main() {

    createCollectionOfSamples("gitworkshop") {

        // Repository
        repositoryUntersuchen()

        // Commits
        commitsErstellen()

        // Dezentralität, Klone und Remotes
        cloning()
        pushFetchPull()
        pushRejected()

        // Merges
        integrationOfChanges()

        // Branches

        // Workflows

        // Rebasing

        // Modules
    }
}

