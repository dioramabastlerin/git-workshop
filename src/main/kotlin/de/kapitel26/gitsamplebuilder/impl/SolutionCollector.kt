package de.kapitel26.gitsamplebuilder.impl

class SolutionCollector {

    val collectedCommands: MutableList<() -> Unit> = mutableListOf()

    fun collect(command: () -> Unit) = collectedCommands.add(command)

    fun perform() = collectedCommands.forEach { it.invoke() }
}