package impl

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

    fun createAufgabenFolge(name: String, commands: Dir.() -> Unit) =
            createSample("$name.loesung") {
                commands()

                writeDocs()

                val aufgabenDir = File(rootDir.parent, "$name.aufgaben")
                val loesungDir = rootDir
                executeProcess(
                        "cp", "-a",
                        loesungDir.absolutePath + "/", aufgabenDir.absolutePath
                )
                Dir(loesungDir, log, solutionCollector)
                        .apply {
                            applyLoesungen()
                            writeDocs()
                        }

                reset()
            }

}