package de.kapitel26.gitsamplebuilder.impl

import java.io.File

class CollectionOfSamples(rootDir: File)
    : AbstractDir<CollectionOfSamples>(rootDir, log = LogBuilder(), solutionCollector = SolutionCollector()) {

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createSample(sampleName: String, commands: (Dir.() -> Unit)? = null) {
        log.collectedLogs.clear()
        createDir(sampleName, commands)
    }

    // TODO obsolete?
    fun copySample(original: String, copy: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir, copy), log, solutionCollector)
                    .also { duplicate ->
                        Dir(rootDir, log, solutionCollector)
                                .executeProcess(
                                        "cp", "-a", File(rootDir, original).absolutePath + "/.", duplicate.rootDir.absolutePath
                                )
                    }
                    .apply(commands)

    fun createAufgabenFolge(name: String, commands: Dir.() -> Unit) =
            createSample("$name.aufgabe") {
                commands()

                writeDocs()

                val aufgabenDir = rootDir
                val loesungDir = File(rootDir.parent, "$name.loesung")
                executeProcess(
                        "cp", "-a",
                        aufgabenDir.absolutePath + "/", loesungDir.absolutePath
                )
                Dir(loesungDir, log, solutionCollector)
                        .apply {
                            applyLoesungen()
                            writeDocs()
                        }

                reset()
            }

}