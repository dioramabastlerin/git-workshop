package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.LogBuilder
import java.io.File as IOFile

class File(val location: java.io.File, val log: LogBuilder) {

    fun createSampleFileContent(): String =
            (0..11).map { "line $it created" }.joinToString("\n")

    fun edit(line: Int, message: String = "edited") = edit(line..line, message)

    fun edit(linesToEdit: IntRange, message: String = "edited") {
        location
                .apply { log.editFile(location.name, linesToEdit, message) }
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

    fun lines() = location.readLines()
}