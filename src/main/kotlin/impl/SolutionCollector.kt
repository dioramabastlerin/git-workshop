package impl

class SolutionCollector {
    val collectedCommands: MutableList<() -> Unit> = mutableListOf()

    fun reset() {
        collectedCommands.clear()
    }
}