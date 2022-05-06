fun main(args: Array<String>) {
    var summe = 0
    args.forEach {
        summe += it.toInt()
    }
    println("Eingabe    : ${args.joinToString()}")
    println("Summe      : $summe")
    println("Mittelwert : ${summe / args.size}")
}