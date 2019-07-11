package de.kapitel26.gitsamplebuilder.impl

import java.io.File
import java.lang.ProcessBuilder.Redirect.INHERIT
import java.lang.ProcessBuilder.Redirect.PIPE

class Dir(rootDir: File, log: LogBuilder = LogBuilder(), val map: MutableList<Dir.() -> Unit> = mutableListOf())
    : AbstracWorkingDir<Dir>(rootDir, log, map) {

    fun createSampleVariant(suffix: String, commands: Dir.() -> Unit) =
            Dir(File(rootDir.parent, "${baseNameWithoutSuffix()}.$suffix"))
                    .also { duplicate ->
                        executeProcess(
                                "cp", "-a", rootDir.absolutePath + "/.", duplicate.rootDir.absolutePath,
                                stdoutRedirect = if (false) INHERIT else PIPE,
                                errorRedirect = PIPE
                        )
                    }
                    .apply(commands)

    private fun baseNameWithoutSuffix() = """([^.]*)(\..*)?""".toRegex().matchEntire(baseName)?.groups?.get(1)?.value
            ?: throw IllegalArgumentException("Not valid $baseName")

}