package types.option.monadic

import arrow.core.*
import arrow.core.computations.option

class Postcode(val input: String?) {
    fun value() = Option.fromNullable(input)
}

class Address(val street: String, val postcode: Postcode?) {
    fun location() = Option.fromNullable(postcode)
}

class Person(val name: String, val address: Address?) {
    fun residence() = Option.fromNullable(address)
}

suspend fun printPostcode(person: Person) {
    val result = option {
        val address = person.residence().bind()
        val location = address.location().bind()
        location.value()
    }

    println(result.fold({ "No postcode available" }, { "Postcode of $it" }))
}

suspend fun main() {
    printPostcode(
        Person(
            "Dave",
            Address("10 Arcatia Road", Postcode("ABC 123"))
        )
    )
    printPostcode(
        Person(
            "Dave",
            Address("10 Arcatia Road", null)
        )
    )
    printPostcode(Person("Dave", null))
}
