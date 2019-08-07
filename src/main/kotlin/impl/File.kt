package impl

class File(val location: java.io.File, val log: LogBuilder) {

    var content: String
        get() = location.readText()
        set(value) {
            location.writeText(value)
        }

    fun createSampleFileContent(): String =
            (0..11).joinToString("\n") { "line $it created" }

    fun edit(line: Int, message: String = "edited") = edit(line..line, message)

    fun edit(linesToEdit: IntRange, message: String = "edited") {
        location
                .apply { log.editFile(dirName(), message) }
                .readLines()
                .mapIndexed { index, s ->
                    if (index in linesToEdit)
                        "line $index $message / $s"
                    else
                        s
                }
                .joinToString("\n")
                .also { location.writeText(it) }
    }

    fun replace(searchFor: String, replaceWith: String) {
        location
                .apply { log.editFile(dirName(), "replace $searchFor with $replaceWith") }
                .readText()
                .replace(searchFor, replaceWith)
                .also { location.writeText(it) }
    }

    fun replaceRegex(searchFor: Regex, replaceWith: String) {
        location
                .apply { log.editFile(dirName(), "replace pattern $searchFor with $replaceWith") }
                .readText()
                .replace(searchFor, replaceWith)
                .also { location.writeText(it) }
    }


    private fun dirName() = location.parentFile.name

    fun lines() = location.readLines()
}