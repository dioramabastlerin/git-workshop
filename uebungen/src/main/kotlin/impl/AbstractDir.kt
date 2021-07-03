package impl

abstract class AbstractDir<T>(
        val rootDir: java.io.File,
        val baseName: String = rootDir.name,
        val log: LogBuilder,
        val solutionCollector: SolutionCollector
) {

    fun markdown(message: String) {
        log.doc(message)
    }

    fun createDir(dirName: String, commands: (Dir.() -> Unit)? = null) =
            java.io.File(rootDir, dirName)
                    .apply { if (exists()) throw IllegalStateException("Dir $this not expected to exist!") }
                    .apply { mkdirs() }
                    .apply { log.createDir(dirName, currentDirname()) }
                    .run { Dir(this, log = log, solutionCollector = solutionCollector) }
                    .run {
                        if (commands != null) {
                            log.cd(dirName, /* TODO */ currentDirname())
                            commands()
                            log.cd("..", currentDirname())
                        }
                    }

    protected fun currentDirname(): String = rootDir.name

    protected fun reset() {
        log.reset()
        solutionCollector.reset()
    }

    @Suppress("UNCHECKED_CAST")
    fun logTo(name: String, commands: T.() -> Unit) {
        log.startLoggingTo(name)
        (this as T).commands()
        log.stopLoggingTo(name)
    }

    fun writeDocs() = log.writeDocs(rootDir)

}