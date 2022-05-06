if (args.isEmpty())
    throw RuntimeException("No arguments given!")

val s = args
        .map { it.toInt() }
        .sum()
println("The average is ${s / args.size}")