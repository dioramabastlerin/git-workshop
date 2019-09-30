package impl

import java.io.File

class CollectionOfSamples(rootDir: File, options: LogBuilderOptions)
    : AbstractDir<CollectionOfSamples>(rootDir, log = LogBuilder(options, rootDir), solutionCollector = SolutionCollector()) {

    var aufgabenNamen: MutableList<String> = mutableListOf()
    var thema: String? = null

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createAufgabenFolge(name: String, commands: Dir.() -> Unit) {
        val prefix = "%02d".format(aufgabenNamen.size + 1)
        val fullName = thema?.let { "$prefix-${it.toLowerCase()}-$name" } ?: "$prefix-$name"
        aufgabenNamen.add(fullName)

        val nameAufgabe = "$fullName.aufgabe"
        val nameLoesungen = "$fullName.loesungen"

        createSample(nameAufgabe) {
            commands()
            logTo(markdownFilename()) {
                markdown("[Zur Lösung](../$nameLoesungen#loesungen)")
            }
            writeDocs()

            val loesungDir = File(rootDir.parent, nameLoesungen)
            executeProcess("cp", "-a", rootDir.absolutePath + "/", loesungDir.absolutePath)

            Dir(loesungDir, log, solutionCollector)
                    .apply {
                        logTo("index.md") {
                            markdown("""<div id="loesungen"/>""")
                        }
                        applyLoesungen()
                        writeDocs()
                    }

            reset()
        }

        logTo("index.md") {
            aufgabenNamen.forEach { name ->
                markdown(" * [$name]($name.aufgabe/index.html) [Lösung]($name.loesungen/index.html#loesungen)")
            }
        }
        writeDocs()
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