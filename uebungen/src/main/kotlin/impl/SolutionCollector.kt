package impl

class SolutionCollector {

    val collectedCommands: MutableList<Pair<String, () -> Unit>> = mutableListOf()
    val aufgabenUndSchritte: MutableList<Pair<String, MutableList<String>>> = mutableListOf()

    fun registerAufgabe(title: String) {
        aufgabenUndSchritte.add(title to mutableListOf<String>())
    }
    
    fun registerSchritt(schritt: String) {
        aufgabenUndSchritte.last().second.add(schritt) 
    }

    fun reset() {
        collectedCommands.clear()
    }
}