package de.kapitel26.gitsamplebuilder.impl

import java.io.File

class PlainDirectory(rootDir: File, baseName: String = rootDir.name) : Directory<PlainDirectory>(rootDir, baseName) {

    override fun duplicatedSample(suffix: String, function: PlainDirectory.() -> Unit) =
            PlainDirectory(File(rootDir.parent, "$baseName.$suffix"), baseName)
                    .also { duplicate ->
                        exeuteSplittedRaw(false, "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath)
                    }
                    .apply(function)

}