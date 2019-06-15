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

    fun createSample(sampleName: String, commands: (Dir.() -> Unit)? = null) = createDir(sampleName, commands)

}