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

        createSample("loesungen/$fullName") {
            logTo(markdownFilename()) {
                markdown(navigationLinks(fullName))
            }

            inDir("") {
                commands()
            }
            writeDocs()

            executeProcess("cp", "-a", rootDir.absolutePath, "../../aufgaben/")

            applyLoesungen(navigationLinks(fullName))
            writeDocs()
        }


        reset()

        logTo("index.md") {
            aufgabenNamen.forEach { name ->
                markdown(" * [$name](aufgaben/$name/index.html) [Lösung](loesungen/$name/loesung.html)")
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

fun navigationLinks(fullName: String): String {
    return "[Aufgabe](../../aufgaben/$fullName/index.html)" +
            " [Lösung](../../loesungen/$fullName/loesung.html)" +
            " [Überblick](../../index.html)"
}
