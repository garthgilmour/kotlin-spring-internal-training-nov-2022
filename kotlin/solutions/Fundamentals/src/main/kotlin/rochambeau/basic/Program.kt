package rochambeau.basic

import java.util.Random

fun main() {
    println("Lets play Rock / Paper / Scissors")
    println("Enter your choice")

    val options = listOf("Rock", "Paper", "Scissors")
    val random = Random()

    val userChoice = readLine() ?: "Rock"
    val programChoice = options[random.nextInt(3)]

    println("You chose $userChoice and program chose $programChoice")
    println(pickWinner(userChoice, programChoice))
}

fun pickWinner(userChoice: String, programChoice: String): String {
    if (userChoice == programChoice) {
        return "You Draw"
    }
    if ((userChoice == "Rock" && programChoice == "Scissors")
            || (userChoice == "Scissors" && programChoice == "Paper")
            || (userChoice == "Paper" && programChoice == "Rock")) {
        return "You Win"
    }
    return "You Lose"
}
