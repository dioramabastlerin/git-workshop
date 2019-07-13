package de.kapitel26.gitsamplebuilder.impl

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class LogBuilder {

    private var activeCollectors = mutableSetOf(".full-log.md")
    var collectedLogs = mutableListOf<Pair<String, Set<String>>>()

    fun createDir(dirName: String, where: String) = shell("mkdir $dirName", where)

    fun cd(dirName: String, where: String) = shell("cd $dirName", where)

    fun createFile(name: String, where: String) = shell("# created file '$name'", where)

    fun editFile(name: String?, linesToEdit: IntRange, message: String, where: String) =
            shell("# Change file '$name' at lines $linesToEdit: $message", where)

    fun shell(cmd: String, where: String?) = addRawLine("    $where$ $cmd")

    fun doc(message: String) {
        message.trimIndent().lines().forEach { addRawLine(it) }
        addRawLine("")
    }

    fun enableDoc(name: String) = activeCollectors.add(name)

    fun disableDoc(name: String) = activeCollectors.remove(name)

    fun addRawLine(s: String) =
        collectedLogs.add(s to activeCollectors.toSet())

    fun writeMarkdownFiles(rootDir: File) {
        val name2writer = mutableMapOf<String, BufferedWriter>()
        collectedLogs.forEach { (line, names) ->
            names.forEach { name ->
                name2writer.computeIfAbsent(name) { logName ->
                    BufferedWriter(FileWriter(File(rootDir, logName)))
                }.apply {
                    write(line)
                    write("\n")
                }
            }
        }
        name2writer.values.forEach { it.close() }
    }

    fun of(name: String) =
            collectedLogs
                    .filter { (_, ns) -> ns.contains(name) }
                    .map { (s, _) -> s }

}