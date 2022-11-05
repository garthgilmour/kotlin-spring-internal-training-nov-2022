package demos.spring.notes.v0

fun main() {
    val shop = SampleShop()
    if (shop.makePurchase("AB123", 20, "DEF456GHI78")) {
        println("Purchase Succeeded")
    } else {
        println("Purchase Failed")
    }
}
