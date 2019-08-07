package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples

fun main() {

    createCollectionOfSamples("gitworkshop") {

        repositoryUntersuchen()
        cloning()
        pushFetchPull()
        pushRejected()
        integrationOfChanges()
    }
}

