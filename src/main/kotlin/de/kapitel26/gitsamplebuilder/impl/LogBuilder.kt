package de.kapitel26.gitsamplebuilder.impl

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class LogBuilder {

    var activeCollectors = mutableSetOf<String>(".full-log.md")
    var collectedLogs = mutableListOf<Pair<String, Set<String>>>()

    fun createDir(dirName: String, where: String) = shell("mkdir $dirName", where)

    fun cd(dirName: String, where: String) = shell("cd $dirName", where)

    fun createFile(name: String, where: String) = shell("# created file '$name'", where)

    fun appendToFile(name: String, where: String) = shell("# append to file '$name'", where)

    fun editFile(name: String?, linesToEdit: IntRange, message: String, where: String) =
            shell("# Change file '$name' at lines $linesToEdit", where)

    fun shell(cmd: String, where: String?) = addRawLine("    ${where?.plus(" ") ?: ""}$ $cmd")

    fun doc(message: String) {
        message.trimIndent().lines().forEach { addRawLine(it) }
        addRawLine("")
    }

    fun enableDoc(name: String) = activeCollectors.add(name)

    fun disableDoc(name: String) = activeCollectors.remove(name)

    fun addRawLine(s: String) =
        collectedLogs.add(s to activeCollectors.toSet())

    fun writeFiles(rootDir: File) {
        val name2writer = mutableMapOf<String, BufferedWriter>()
        collectedLogs.forEach { (line, names) ->
            names.forEach { name ->
                name2writer.computeIfAbsent(name) { name ->
                    BufferedWriter(FileWriter(File(rootDir, name)))
                }.apply {
                    write(line)
                    write("\n")
                }
            }
        }
        name2writer.values.forEach { it.close() }
    }

}