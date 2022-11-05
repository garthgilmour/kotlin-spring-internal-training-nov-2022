package operations.zip

import operations.zip.Department.*

import arrow.core.nonEmptyListOf

fun main() {
    val names = nonEmptyListOf("Dave", "Jane", "Fred", "Mary")
    val ages = nonEmptyListOf(20, 30, 40 , 50)
    val salaries = nonEmptyListOf(20000.0, 30000.0, 40000.0, 50000.0)
    val departments = nonEmptyListOf(IT, HR, Sales, IT)

    val results = names.zip(ages, salaries, departments, ::Employee)
    results.forEach(::println)
}
