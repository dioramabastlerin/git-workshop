package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.AbstractDir
import de.kapitel26.gitsamplebuilder.LogBuilder
import java.io.File

class Dir(rootDir: File, baseName: String = rootDir.name, log: LogBuilder = LogBuilder()) : AbstractDir<Dir>(rootDir, baseName, log) {

    fun createSample(name: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir.parent))
                    .run(commands)

    fun createSampleVariant(suffix: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir.parent, "${baseNameWithoutSuffix()}.$suffix"), baseNameWithoutSuffix())
                    .also { duplicate ->
                        exeuteSplittedRaw(false, "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath)
                    }
                    .apply(commands)

    private fun baseNameWithoutSuffix() = """(.*)(\..*?)""".toRegex().matchEntire(baseName)?.groups?.get(1)?.value
            ?: throw IllegalArgumentException("Not valid $baseName")

}