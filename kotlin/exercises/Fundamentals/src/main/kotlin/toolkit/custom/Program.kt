package toolkit.custom

fun main() {
    val originalData = arrayListOf("12", "73", "52", "36", "87")

    // TODO  Uncomment these one by one and implement the relevant function
    //       You can implement either as a free function or as an extension

    // Option 1 - Simple Function version
    //    val mappedData = mymap(originalData, String::toInt)
    //    val filteredData = myfilter(mappedData) { it > 50 }
    //    val (even, odd) = mypartition(mappedData) { it % 2 == 0 }
    //    val anyGreaterThan50 = myany(mappedData) { it > 50 }
    //    val allGreaterThan50 = myall(mappedData) { it > 50 }
    //    val sum = myreduce(mappedData) { previous, current -> previous + current }
    //    val join = myreduce(mappedData, "Values: ") { previous, current -> previous + " " + current }

    // Option 2 - Extension Function version
    //    val mappedData = originalData.mymap(String::toInt)
    //    val filteredData = mappedData.myfilter { it > 50 }
    //    val (even, odd) = mappedData.mypartition { it % 2 == 0 }
    //    val anyGreaterThan50 = mappedData.myany { it > 50 }
    //    val allGreaterThan50 = mappedData.myall { it > 50 }
    //    val sum = mappedData.myreduce { previous, current -> previous + current }
    //    val join = mappedData.myreduce("Values: ") { previous, current -> previous + " " + current }

    //    printResults("Results of mapping to int", mappedData)
    //    printResults("Results of filtering > 50", filteredData)
    //    printResults("Results of partitioning even/odd", even, odd)
    //    printResults("Results of any > 50", anyGreaterThan50)
    //    printResults("Results of all > 50", allGreaterThan50)
    //    printResults("Results of reduce - sum", sum)
    //    printResults("Results of reduce - join", join)
}
