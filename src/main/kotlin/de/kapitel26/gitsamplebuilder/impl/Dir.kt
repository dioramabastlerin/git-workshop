package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.AbstracWorkingDir
import de.kapitel26.gitsamplebuilder.LogBuilder
import java.io.File

class Dir(rootDir: File, log: LogBuilder = LogBuilder())
    : AbstracWorkingDir<Dir>(rootDir, log) {

    fun createSampleVariant(suffix: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir.parent, "${baseNameWithoutSuffix()}.$suffix"))
                    .also { duplicate ->
                        exeuteSplittedRaw(false, "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath)
                    }
                    .apply(commands)

    private fun baseNameWithoutSuffix() = """(.*)(\..*?)""".toRegex().matchEntire(baseName)?.groups?.get(1)?.value
            ?: throw IllegalArgumentException("Not valid $baseName")

}