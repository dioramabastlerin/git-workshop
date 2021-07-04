package impl

import java.io.File

class CollectionOfSamples(rootDir: File, options: LogBuilderOptions) : AbstractDir<CollectionOfSamples>(
    rootDir,
    log = LogBuilder(options, rootDir),
    solutionCollector = SolutionCollector()
) {

    var aufgabenNamen: MutableList<String> = mutableListOf()
    var thema: String? = null

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createAufgabenFolge(name: String, commands: Dir.() -> Unit) {
        val prefix = "%02d".format(aufgabenNamen.size + 1)
        val fullName = thema?.let { "${it.toLowerCase()}-$name" } ?: "$prefix-$name"
        aufgabenNamen.add(fullName)

        createSample("loesungen/$fullName") {
            markdown(navigationLinks(fullName))

            inDir(".") {
                logTo("aufgabe-$fullName.md") {
                    commands()
                }
            }
            writeDocs()

            executeProcess("cp", "-a", rootDir.absolutePath, "../../aufgaben/")

            applyLoesungen(navigationLinks(fullName), fullName)
            writeDocs()
        }

        reset()

        logTo("ueberblick.md") {
            aufgabenNamen.forEach { name ->
                markdown(" * [$name](loesungen/$fullName/aufgabe-$fullName.html) [Lösung](loesungen/$fullName/loesung-$fullName.html)")
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
    return "[Aufgabe](../../aufgaben/$fullName/aufgabe-$fullName.html)" +
            " [Lösung](../../loesungen/$fullName/loesung-$fullName.html)" +
            " [Überblick](../../index.html)"
}
