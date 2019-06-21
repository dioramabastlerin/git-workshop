package de.kapitel26.gitsamplebuilder

import de.kapitel26.gitsamplebuilder.impl.CollectionOfSamples
import de.kapitel26.gitsamplebuilder.impl.Dir
import java.io.File as IOFile

class CommandlineException(val failedProcess: Process, message: String) : RuntimeException(message)

fun buildGitSamples(sampleName: String, sampleDir: String = "build/git-samples", suffix: String = "aufgabe", commands: Dir.() -> Unit) =
        buildGitSamples(IOFile(sampleDir, "$sampleName.$suffix")) {
            commands()
        }

fun buildGitSamples(rootDir: IOFile, commands: Dir.() -> Unit) {
    Dir(rootDir)
            .apply { clear() }
            .run(commands)
}

fun createCollectionOfSamples(dirName: String, commands: CollectionOfSamples.() -> Unit) {
    CollectionOfSamples(IOFile("build", dirName))
            .apply { clear() }
            .run(commands)
}