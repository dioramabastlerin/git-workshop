package de.kapitel26.gitsamplebuilder

import de.kapitel26.gitsamplebuilder.gitworkshop.buildGitUebungen
import java.io.File
import java.lang.ProcessBuilder.Redirect.INHERIT
import java.lang.ProcessBuilder.Redirect.PIPE

fun main() {
    println("Start building")

    val distPath = File("build/distributions")
    distPath.mkdirs()
    val zipFile = File("${distPath}/git-uebungen.zip")

    if(zipFile.exists()) {
        println("Deleting old zip")
        zipFile.delete()
    }

    println("Zipping")
    val processBuilder = ProcessBuilder("zip", "-r", zipFile.absolutePath.toString(), "git-uebungen")
    processBuilder.directory(File("build"))
    processBuilder.redirectOutput(INHERIT)
    processBuilder.redirectError(INHERIT)
    val process = processBuilder.start()
    val exit = process.waitFor()
    if(exit != 0)
        throw Exception("Exit: $exit")
    println("OK")
}

