package impl

import impl.LogOutputFormat.HTML
import impl.LogOutputFormat.MARKDOWN
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class LogBuilder(val options: LogBuilderOptions = LogBuilderOptions()) {

    private val fullLogFileName = ".full-log.md"

    private var activeCollectors = resetCollectors()

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

    fun writeDocs(rootDir: File): Any =
            when (options.outputFormat) {
                HTML -> writeHtmlFiles(rootDir)
                MARKDOWN -> writeMarkdownFiles(rootDir)
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

    fun writeHtmlFiles(rootDir: File) {
        val header = """
            <head>
            <meta charset="utf-8">  
            </head>
            
        """.trimIndent()

        collectedLogs
                .flatMap { (line, names) -> names.map { name -> name to line } }
                .groupBy { it.first }
                .entries
                .map { (name, lines) -> name to lines.map { it.second }.joinToString("\n") }
                .map { (name, markdown) ->
                    val htmlBody = markdown2html(markdown)
                    val nameWithoutExtension = File(name).nameWithoutExtension
                    File(rootDir, "$nameWithoutExtension.html").writeText(header + htmlBody)
                }
    }

    private fun markdown2html(markdown: String): String {
        val flavour = CommonMarkFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)
        val html = HtmlGenerator(markdown, parsedTree, flavour).generateHtml()
        return html
    }

    fun of(name: String) =
            collectedLogs
                    .filter { (_, ns) -> ns.contains(name) }
                    .map { (s, _) -> s }

    fun reset() {
        activeCollectors = resetCollectors()
        collectedLogs = mutableListOf()
    }

    private fun resetCollectors(): MutableSet<String> {
        return if (options.createFullLog)
            mutableSetOf(fullLogFileName)
        else
            mutableSetOf()
    }


}