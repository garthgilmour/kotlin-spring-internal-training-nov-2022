package toolkit.custom.extensions

import toolkit.custom.printResults

fun main() {
    val originalData = arrayListOf("12", "73", "52", "36", "87")

    val mappedData = originalData.mymap(String::toInt)
    val filteredData = mappedData.myfilter { it > 50 }
    val (even, odd) = mappedData.mypartition { it % 2 == 0 }
    val anyGreaterThan50 = mappedData.myany { it > 50 }
    val allGreaterThan50 = mappedData.myall { it > 50 }
    val sum = mappedData.myreduce { previous, current -> previous + current }
    val join = mappedData.myreduce("Values: ") { previous, current -> "$previous $current" }

    printResults("Results of mapping to int", mappedData)
    printResults("Results of filtering > 50", filteredData)
    printResults("Results of partitioning even/odd", even, odd)
    printResults("Results of any > 50", anyGreaterThan50)
    printResults("Results of all > 50", allGreaterThan50)
    printResults("Results of reduce - sum", sum)
    printResults("Results of reduce - join", join)
}

fun <T, U> Iterable<T>.mymap(transform: (T) -> U): List<U> {
    val results = ArrayList<U>()
    for (item in this) {
        results.add(transform(item))
    }
    return results
}

fun <T> Iterable<T>.myfilter(predicate: (T) -> Boolean): List<T> {
    val results = ArrayList<T>()
    for (item in this) {
        if (predicate(item)) {
            results.add(item)
        }
    }
    return results
}

fun <T> Iterable<T>.mypartition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
    val results = Pair(ArrayList<T>(), ArrayList<T>())
    for (item in this) {
        if (predicate(item)) {
            results.first.add(item)
        } else {
            results.second.add(item)
        }
    }
    return results
}

fun <T> Iterable<T>.myany(predicate: (T) -> Boolean): Boolean {
    for (item in this) {
        if (predicate(item)) return true
    }
    return false
}

fun <T> Iterable<T>.myall(predicate: (T) -> Boolean): Boolean {
    for (item in this) {
        if (!predicate(item)) return false
    }
    return true
}

fun <T> Iterable<T>.myreduce(reducer: (T, T) -> T): T {
    var total = this.first()
    for (item in this.drop(1)) {
        total = reducer(total, item)
    }
    return total
}

fun <T, U> Iterable<T>.myreduce(initial: U,  reducer: (U, T) -> U): U {
    var total = initial
    for (item in this) {
        total = reducer(total, item)
    }
    return total
}
