package videostore

import videostore.PriceCode.REGULAR
import videostore.PriceCode.CHILDRENS
import videostore.PriceCode.NEW_RELEASE

fun main() {
    val peterPan = Movie("Peter Pan", CHILDRENS)
    val theHulk  = Movie("The Hulk", REGULAR)
    val starWars = Movie("Star Wars", REGULAR)
    val toyStory = Movie("Toy Story", CHILDRENS)
    val killBill = Movie("Kill Bill", NEW_RELEASE)

    val r1 = Rental(peterPan, 2)
    val r2 = Rental(theHulk, 1)
    val r3 = Rental(starWars, 3)
    val r4 = Rental(toyStory, 2)
    val r5 = Rental(killBill, 4)

    val customer = Customer("Joe Bloggs")
    customer.addRental(r1)
    customer.addRental(r2)
    customer.addRental(r3)
    customer.addRental(r4)
    customer.addRental(r5)

    val statement = customer.statement()
    println(statement)
}
