package demos.spring.notes.common

interface Shop {
    fun makePurchase(itemNo: String, quantity: Int, cardNo: String): Boolean
}
