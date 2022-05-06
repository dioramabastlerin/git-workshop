fun main(args: Array<String>) {
    var s = 0
    args.forEach {
        s += it.toInt()
    }
    println("Eingabe    : ${args.joinToString()}")
    println("Summe      : $s")
    println("Mittelwert : ${s / args.size}")
}