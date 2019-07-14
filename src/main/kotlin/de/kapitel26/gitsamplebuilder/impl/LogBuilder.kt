package de.kapitel26.gitsamplebuilder.impl

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class LogBuilder {

    private val fullLogFileName = ".full-log.md"
    private var activeCollectors = mutableSetOf(fullLogFileName)
    var collectedLogs = mutableListOf<Pair<String, Set<String>>>()

    fun createDir(dirName: String, where: String) = shell("mkdir $dirName", where)

    fun cd(dirName: String, where: String) = shell("cd $dirName", where)

    fun createFile(name: String, where: String) = shell("# created file '$name'", where)

    fun editFile(where: String, message: String) =
            shell("# $message", where)

    fun shell(
            cmd: String, where: String?,
            outputLines: List<String> = emptyList(),
            errorLines: List<String> = emptyList()
    ) {
        addRawLine("    $where$ $cmd")
        outputLines.forEach { addRawLine("    $it") }
        errorLines.forEach { addRawLine("    $it") }
        addRawLine("    ")
    }

    fun doc(message: String) {
        message.trimIndent().lines().forEach { addRawLine(it) }
        addRawLine("")
    }

    fun startLoggingTo(name: String) = activeCollectors.add(name)

    fun stopLoggingTo(name: String) = activeCollectors.remove(name)

    fun addRawLine(s: String) =
        collectedLogs.add(s to activeCollectors.toSet())

    fun writeIndexFile(rootDir: File) {
        collectedLogs
                .flatMap { (line, names) -> names.map { it to line } }
                .filter { (name, _) -> name != fullLogFileName }
                .sortedBy { (name, _) -> name }
                .joinToString("\n") { (_, line) -> line }
                .also { File(rootDir, "index.md").writeText(it) }
    }

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

    fun reset() {
        activeCollectors = mutableSetOf(fullLogFileName)
        collectedLogs = mutableListOf()
    }

}