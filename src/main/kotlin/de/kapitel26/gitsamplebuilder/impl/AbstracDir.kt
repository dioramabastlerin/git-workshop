package de.kapitel26.gitsamplebuilder.impl

abstract class AbstracDir<T>(
        val rootDir: java.io.File,
        val baseName: String = rootDir.name,
        val log: LogBuilder = LogBuilder()
) {
    fun logAsMarkdown() = log.toMarkdown()

    fun clearLog() = log.clear()

    private fun createOrAppendToFile(name: String, content: String? = null, commands: File.() -> Unit = {}) =
            File(java.io.File(rootDir, name), log)
                    .apply {
                        if (!location.exists()) {
                            log.createFile(name, content, currentDirname())
                            location.writeText(content ?: createSampleFileContent())
                        } else {
                            log.appendToFile(name, content, currentDirname())
                            location.appendText(content ?: createSampleFileContent())
                        }
                    }
                    .run(commands)

    fun markdown(message: String) {
        log.doc(message)
    }

    fun createDir(dirName: String, commands: (Dir.() -> Unit)? = null) =
            java.io.File(rootDir, dirName)
                    .apply { if (exists()) throw IllegalStateException("Dir $this not expected to exist!") }
                    .apply { mkdirs() }
                    .apply { log.createDir(dirName, currentDirname()) }
                    .run { Dir(this, log = log) }
                    .run {
                        if (commands != null) {
                            log.cd(dirName, /* TODO */ currentDirname())
                            commands()
                            log.cd("..", currentDirname())
                        }
                    }

    protected fun currentDirname() = rootDir.name

}