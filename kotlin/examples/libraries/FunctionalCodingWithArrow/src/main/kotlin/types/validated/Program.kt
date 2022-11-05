package types.validated

import arrow.core.Validated
import arrow.core.Validated.*
import arrow.core.traverseValidated
import arrow.typeclasses.Semigroup

class Employee(val id: String, val age: Int, val dept: String) {
    override fun toString() = "$id of age $age working in $dept"
}

fun askQuestion(question: String): String {
    println(question)
    return readLine() ?: ""
}

fun checkID(): Validated<String, String> {
    val regex = Regex("[A-Z]{2}[0-9]{2}")
    val response = askQuestion("Whats your ID?")
    return if (regex.matches(response)) Valid(response) else Invalid("Bad ID")
}

fun checkAge(): Validated<String, String> {
    val response = askQuestion("How old are you?")
    return if (response.toInt() > 16) Valid(response) else Invalid("Bad Age")
}

fun checkDept(): Validated<String, String> {
    val depts = listOf("HR", "Sales", "IT")
    val response = askQuestion("Where do you work?")
    return if (depts.contains(response)) Valid(response) else Invalid("Bad Dept")
}

fun main() {
    val sg = object : Semigroup<String> {
        override fun String.combine(b: String) = "$this $b"
    }

    val answers = listOf(checkID(), checkAge(), checkDept())

    val result = answers
        .traverseValidated(sg) { it }
        .map { (id, age, dept) -> Employee(id, age.toInt(), dept) }
        .fold({ "Error: $it" }, { "Result: $it" })

    println(result)
}
