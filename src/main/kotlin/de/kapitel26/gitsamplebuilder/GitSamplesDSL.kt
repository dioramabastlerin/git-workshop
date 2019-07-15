package de.kapitel26.gitsamplebuilder

import impl.CollectionOfSamples
import impl.Dir
import impl.LogBuilder
import impl.SolutionCollector
import java.io.File as IOFile

class CommandLineException(val failedProcess: Process, message: String) : RuntimeException(message)

fun buildGitSamples(sampleName: String, sampleDir: String = "build/git-samples", suffix: String = "aufgabe", commands: Dir.() -> Unit) =
        buildGitSamples(IOFile(sampleDir, "$sampleName.$suffix")) {
            commands()
        }

fun buildGitSamples(rootDir: IOFile, commands: Dir.() -> Unit) {
    Dir(rootDir, LogBuilder(), SolutionCollector())
            .apply { clear() }
            .run(commands)
}

fun createCollectionOfSamples(dirName: String, commands: CollectionOfSamples.() -> Unit) {
    CollectionOfSamples(IOFile("build", dirName))
            .apply { clear() }
            .run(commands)
}