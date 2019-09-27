package impl

import java.io.File

class CollectionOfSamples(rootDir: File, options: LogBuilderOptions)
    : AbstractDir<CollectionOfSamples>(rootDir, log = LogBuilder(options, rootDir), solutionCollector = SolutionCollector()) {

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

        val nameAufgabe = "$fullName.aufgabe"
        val nameLoesungen = "$fullName.loesungen"

        createSample(nameAufgabe) {
            commands()
            logTo(markdownFilename()) {
                markdown("[Zur Lösung](../$nameLoesungen)")
            }
            writeDocs()

            val loesungDir = File(rootDir.parent, nameLoesungen)
            executeProcess("cp", "-a", rootDir.absolutePath + "/", loesungDir.absolutePath)

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