package rochambeau

import java.util.Random

fun main() {
    println("Lets play Rock / Paper / Scissors")

    val random = Random()
    val programChoice = random.nextInt(3)
    println("Program chose $programChoice")
}
