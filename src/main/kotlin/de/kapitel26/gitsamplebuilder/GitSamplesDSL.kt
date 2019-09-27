package de.kapitel26.gitsamplebuilder

import impl.*
import java.io.File as IOFile

class CommandLineException(val failedProcess: Process, message: String) : RuntimeException(message)

fun buildGitSamples(sampleName: String, sampleDir: String = "build/git-samples", suffix: String = "aufgabe", commands: Dir.() -> Unit) =
        buildGitSamples(
                IOFile(sampleDir, "$sampleName.$suffix"),
                LogBuilderOptions(outputFormat = LogOutputFormat.MARKDOWN, createFullLog = true)
        ) {
            commands()
        }

fun buildGitSamples(gitSamplesRootDir: java.io.File, options: LogBuilderOptions = LogBuilderOptions(), commands: Dir.() -> Unit) {
    Dir(gitSamplesRootDir, LogBuilder(options, gitSamplesRootDir), SolutionCollector())
            .apply { clear() }
            .run(commands)
}

fun createCollectionOfSamples(dirName: String, options: LogBuilderOptions = LogBuilderOptions(), commands: CollectionOfSamples.() -> Unit) {
    CollectionOfSamples(IOFile("build", dirName), options)
            .apply { clear() }
            .run(commands)
}