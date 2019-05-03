package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.AbstractDir
import de.kapitel26.gitsamplebuilder.LogBuilder
import java.io.File

class Dir(rootDir: File, baseName: String = rootDir.name, log: LogBuilder = LogBuilder()) : AbstractDir<Dir>(rootDir, baseName, log) {

    override fun duplicatedSample(suffix: String, function: Dir.() -> Unit) =
            Dir(File(rootDir.parent, "$baseName.$suffix"), baseName)
                    .also { duplicate ->
                        exeuteSplittedRaw(false, "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath)
                    }
                    .apply(function)

}