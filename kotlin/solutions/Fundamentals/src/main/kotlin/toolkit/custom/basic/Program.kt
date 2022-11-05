package toolkit.custom.basic

import toolkit.custom.printResults

fun main() {
    val originalData = arrayListOf("12", "73", "52", "36", "87")

    val mappedData = mymap(originalData, String::toInt)
    val filteredData = myfilter(mappedData) { it > 50 }
    val (even, odd) = mypartition(mappedData) { it % 2 == 0 }
    val anyGreaterThan50 = myany(mappedData) { it > 50 }
    val allGreaterThan50 = myall(mappedData) { it > 50 }
    val sum = myreduce(mappedData) { previous, current -> previous + current }
    val join = myreduce(mappedData, "Values: ") { previous, current -> "$previous $current" }

    printResults("Results of mapping to int", mappedData)
    printResults("Results of filtering > 50", filteredData)
    printResults("Results of partitioning even/odd", even, odd)
    printResults("Results of any > 50", anyGreaterThan50)
    printResults("Results of all > 50", allGreaterThan50)
    printResults("Results of reduce - sum", sum)
    printResults("Results of reduce - join", join)
}

fun <T, U> mymap(input: Iterable<T>, transform: (T) -> U): List<U> {
    val results = ArrayList<U>()
    for (item in input) {
        results.add(transform(item))
    }
    return results
}

fun <T> myfilter(input: Iterable<T>, predicate: (T) -> Boolean): List<T> {
    val results = ArrayList<T>()
    for (item in input) {
        if (predicate(item)) {
            results.add(item)
        }
    }
    return results
}

fun <T> mypartition(input: Iterable<T>, predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
    val results = Pair(ArrayList<T>(), ArrayList<T>())
    for (item in input) {
        if (predicate(item)) {
            results.first.add(item)
        } else {
            results.second.add(item)
        }
    }
    return results
}

fun <T> myany(input: Iterable<T>, predicate: (T) -> Boolean): Boolean {
    for (item in input) {
        if (predicate(item)) return true
    }
    return false
}

fun <T> myall(input: Iterable<T>, predicate: (T) -> Boolean): Boolean {
    for (item in input) {
        if (!predicate(item)) return false
    }
    return true
}

fun <T> myreduce(input: Iterable<T>, reducer: (T, T) -> T): T {
    var total = input.first()
    for (item in input.drop(1)) {
        total = reducer(total, item)
    }
    return total
}

fun <T, U> myreduce(input: Iterable<T>, initial: U,  reducer: (U, T) -> U): U {
    var total = initial
    for (item in input) {
        total = reducer(total, item)
    }
    return total
}
