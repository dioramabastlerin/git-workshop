package de.kapitel26.gitsamplebuilder.gitworkshop

import de.kapitel26.gitsamplebuilder.createCollectionOfSamples
import impl.LogBuilderOptions

fun main() {

    createCollectionOfSamples("gitworkshop-sandbox", LogBuilderOptions()) {

        // repositoryUntersuchen()
        commitsErstellen()
        // cloning()
        // pushFetchPull()
        // pushRejected()
        // integrationOfChanges()
    }
}

