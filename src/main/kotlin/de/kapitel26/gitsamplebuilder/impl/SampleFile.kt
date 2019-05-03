package de.kapitel26.gitsamplebuilder.impl

import java.io.File

class SampleFile(val location: File) {

    fun createSampleFileContent(): String =
            (0..11).map { "line $it created" }.joinToString("\n")

    fun edit(line: Int, message: String = "edited") = edit(line..line, message)

    fun edit(linesToEdit: IntRange, message: String = "edited") {
        location
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