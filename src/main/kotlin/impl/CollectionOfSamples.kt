package impl

import java.io.File

class CollectionOfSamples(rootDir: File, options: LogBuilderOptions)
    : AbstractDir<CollectionOfSamples>(rootDir, log = LogBuilder(options), solutionCollector = SolutionCollector()) {

    var aufgabenFolgenNummer: Int = 0
    var thema: String? = null

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createAufgabenFolge(name: String, commands: Dir.() -> Unit) {
        aufgabenFolgenNummer++
        val prefix = "%02d".format(aufgabenFolgenNummer)
        val fullName = thema?.let { "$prefix-${it.toLowerCase()}-$name" } ?: "$prefix-$name"

        createSample("$fullName.loesung") {
                commands()

                writeDocs()

            val aufgabenDir = File(rootDir.parent, "$fullName.aufgaben")
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

    fun createSample(sampleName: String, commands: (Dir.() -> Unit)? = null) {
        log.collectedLogs.clear()
        createDir(sampleName, commands)
    }

    fun thema(thema: String, commands: CollectionOfSamples.() -> Unit) {
        val previousThema = this.thema
        this.thema = thema
        commands()
        this.thema = previousThema
    }
}