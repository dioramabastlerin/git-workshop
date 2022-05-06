package de.kapitel26.gitsamplebuilder

import de.kapitel26.gitsamplebuilder.gitworkshop.buildGitUebungen
import java.io.File
import java.lang.ProcessBuilder.Redirect.INHERIT
import java.lang.ProcessBuilder.Redirect.PIPE

fun main() {
    println("Start building")

    val zipFile = File("build.zip")

    if(zipFile.exists()) {
        println("Deleting old zip")
        zipFile.delete()
    }

    println("Zipping")
    val processBuilder = ProcessBuilder("zip", "-r", zipFile.absolutePath.toString(), "build/git-uebungen")
    processBuilder.directory(File("."))
    processBuilder.redirectOutput(INHERIT)
    processBuilder.redirectError(INHERIT)
    val process = processBuilder.start()
    val exit = process.waitFor()
    if(exit != 0)
        throw Exception("Exit: $exit")
    println("OK")
}

