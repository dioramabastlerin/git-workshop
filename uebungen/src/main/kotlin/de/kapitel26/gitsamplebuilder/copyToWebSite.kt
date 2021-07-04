package de.kapitel26.gitsamplebuilder

import java.io.File

fun main() {
    val srcDir = File("build/git-uebungen")
    copy(srcDir, File("."))
}

fun copy(src: File, toDir: File) {
    val target = File(toDir, src.name)
    if(src.isFile && src.name.endsWith(".md")) {
        src.copyTo(target, overwrite = true)
    } else if(src.isDirectory && src.name != "aufgaben") {
        target.mkdir()
        src.listFiles().forEach { copy(it,target ) }
    }
}
