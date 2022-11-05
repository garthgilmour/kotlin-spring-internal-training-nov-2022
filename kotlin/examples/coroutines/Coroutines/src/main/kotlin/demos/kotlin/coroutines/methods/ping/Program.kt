package demos.kotlin.coroutines.methods.ping

import demos.kotlin.coroutines.shared.pingSlowServer
import demos.kotlin.coroutines.shared.printInDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    printInDetail("Program starts")
    val result = demo()
    printInDetail("Demo returns $result")
    printInDetail("Program ends")
}

private suspend fun demo(): List<String> = withContext(Dispatchers.IO) {
    val allResults = mutableListOf<String>()

    val result1 = pingSlowServer(8)
    printInDetail(result1)
    allResults.add(result1)

    val result2 = pingSlowServer(6)
    printInDetail(result2)
    allResults.add(result2)

    val result3 = pingSlowServer(4)
    printInDetail(result3)
    allResults.add(result3)

    val result4 = pingSlowServer(2)
    printInDetail(result4)
    allResults.add(result4)

    allResults.toList()
}



