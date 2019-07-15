fun main(args: Array<String>) {
    var s = 0.0
    args.forEach {
        s += it.toDouble()
    }
    println("Eingabe    : ${args.joinToString()}")
    println("Summe      : $s")
    println("Mittelwert : ${s / args.size}")
}