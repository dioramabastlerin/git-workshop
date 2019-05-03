package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.AbstractDir
import java.io.File

class Dir(rootDir: File, baseName: String = rootDir.name) : AbstractDir<de.kapitel26.gitsamplebuilder.impl.Dir>(rootDir, baseName) {

    override fun duplicatedSample(suffix: String, function: de.kapitel26.gitsamplebuilder.impl.Dir.() -> Unit) =
            de.kapitel26.gitsamplebuilder.impl.Dir(File(rootDir.parent, "$baseName.$suffix"), baseName)
                    .also { duplicate ->
                        exeuteSplittedRaw(false, "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath)
                    }
                    .apply(function)

}