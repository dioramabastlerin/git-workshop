package de.kapitel26.gitsamplebuilder.impl

import de.kapitel26.gitsamplebuilder.AbstracDir
import de.kapitel26.gitsamplebuilder.LogBuilder
import java.io.File

class CollectionOfSamples(rootDir: File, log: LogBuilder = LogBuilder())
    : AbstracDir<CollectionOfSamples>(rootDir, log = log) {

    fun clear() {
        rootDir.deleteRecursively()
        rootDir.mkdirs()
    }

    fun createSample(sampleName: String, commands: (Dir.() -> Unit)? = null) {
        log.collectedLogs.clear()
        createDir(sampleName, commands)
    }

    fun copySample(original: String, copy: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir, copy), log)
                    .also { duplicate ->
                        Dir(rootDir, log)
                                .exeuteSplittedRaw(false, "cp", "-a", File(rootDir, original).absolutePath + "/.", duplicate.rootDir.absolutePath)
                    }
                    .apply(commands)

    private fun baseNameWithoutSuffix() =
            """([^.]*)(\..*)?""".toRegex().matchEntire(baseName)?.groups?.get(1)?.value
                    ?: throw IllegalArgumentException("Not valid $baseName")

    fun createAufgabenFolge(name: String, commands: Dir.() -> Unit) = createSample("$name.aufgabe", commands)

    fun createLoesungsFolge(name: String, commands: Dir.() -> Unit) = copySample("$name.aufgabe", "$name.loesung", commands)

}