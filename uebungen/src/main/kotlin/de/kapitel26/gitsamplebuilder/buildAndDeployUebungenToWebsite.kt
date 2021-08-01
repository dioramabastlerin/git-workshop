package de.kapitel26.gitsamplebuilder

import de.kapitel26.gitsamplebuilder.gitworkshop.buildGitUebungen
import java.io.File

fun main() {
    val srcDir = File("build/git-uebungen/loesungen")
    val targetDir = File("git-uebungen")
    targetDir.deleteRecursively()
    copy(srcDir, targetDir)
}

fun copy(src: File, targetDir: File) {
    if(src.isFile && src.name.matches("(aufgabe|loesung)-.*\\.md".toRegex())) {
        src.copyTo(File(targetDir, src.name), overwrite = false)
    } else if(src.isDirectory) {
        src.listFiles().forEach { copy(it,targetDir ) }
    }
}
