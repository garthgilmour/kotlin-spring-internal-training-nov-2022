package rochambeau.enums

import java.util.Random

fun main() {
    println("Lets play Rock / Paper / Scissors")
    println("Enter your choice")

    val random = Random()
    val userChoice = Rochambeau.valueOf(readLine()?.toUpperCase() ?: "ROCK")
    val programChoice = Rochambeau.values()[random.nextInt(3)]

    println("You chose $userChoice and program chose $programChoice")
    println(pickWinner(userChoice, programChoice))
}

fun pickWinner(userChoice: Rochambeau, programChoice: Rochambeau): String {
    if (userChoice == programChoice) {
        return "You Draw"
    }
    return if (userChoice.beats(programChoice)) "You Win" else "You Lose"
}
