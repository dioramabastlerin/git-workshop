package impl

class SolutionCollector {

    val collectedCommands: MutableList<Pair<String, () -> Unit>> = mutableListOf()

    fun reset() {
        collectedCommands.clear()
    }
}