package demos.kotlin.coroutines.methods.waldo

import demos.kotlin.coroutines.shared.fetchNextName
import demos.kotlin.coroutines.shared.printInDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    printInDetail("Program starts")
    val result = findWaldo("Jane")
    printInDetail("Return value is $result")
    printInDetail("Program ends")
}

suspend fun findWaldo(starterName: String): String = withContext(Dispatchers.IO) {
    val firstName = fetchNextName(starterName)
    printInDetail("Found $firstName")

    val secondName = fetchNextName(firstName)
    printInDetail("Found $secondName")

    val thirdName = fetchNextName(secondName)
    printInDetail("Found $thirdName")

    val fourthName = fetchNextName(thirdName)
    printInDetail("Found $fourthName")

    fetchNextName(fourthName)
}
