package impl

import java.io.File

class Dir(
        rootDir: File,
        log: LogBuilder,
        solutionCollector: SolutionCollector)
    : AbstractWorkingDir<Dir>(rootDir, log, solutionCollector) {

    fun createSampleVariant(suffix: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir.parent, "${baseNameWithoutSuffix()}.$suffix"), log, solutionCollector)
                    .also { duplicate ->
                        executeProcess(
                                "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath
                        )
                    }
                    .apply(commands)

    private fun baseNameWithoutSuffix() = """([^.]*)(\..*)?""".toRegex().matchEntire(baseName)?.groups?.get(1)?.value
            ?: throw IllegalArgumentException("Not valid $baseName")

}