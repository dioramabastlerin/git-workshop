package de.kapitel26.gitsamplebuilder.impl

class SolutionCollector {
    val collectedCommands: MutableList<() -> Unit> = mutableListOf()

    fun reset() {
        collectedCommands.clear()
    }
}