package com.instil.reification

import kotlin.reflect.full.createInstance

//fun <T: Any> filterByTypeV1(items: List<Any>): List<Pair<T,T>> {
//    val tmp = items.filter { it is T }
//    return tmp.map { Pair(it as T, T::class.createInstance()) }
//}

inline fun <reified T : Any> filterByTypeV2(items: List<Any>): List<Pair<T, T>> {
    val tmp = items.filter { it is T }
    return tmp.map { Pair(it as T, T::class.createInstance()) }
}

class Customer(private val name: String = "Dave") {
    override fun toString() = "Customer called $name"
}

class Item(private val id: Int = 100) {
    override fun toString() = "Item with id $id"
}

fun main() {
    val inputs = listOf(
        Customer("Jane"),
        Item(123),
        Customer("Lucy"),
        Item(456),
        Customer("Mary"),
        Item(789)
    )
    val results1 = filterByTypeV2<Customer>(inputs)
    val results2 = filterByTypeV2<Item>(inputs)

    println("Results for T == Customer")
    results1.forEach(::println)
    println("Results for T == Item")
    results2.forEach(::println)
}
