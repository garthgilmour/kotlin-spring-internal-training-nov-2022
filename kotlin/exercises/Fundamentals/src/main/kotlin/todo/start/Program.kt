package todo.start

import todo.shared.ToDo

fun main() {
    val toDoList = mutableListOf<ToDo>()
    while (true) {
        printMenu()
        val choice = readChoice()
        doWork(toDoList, choice)
    }
}

fun printMenu() {
    println("""
Choose from the following options:
    1) View all current items
    2) Add an item to the list
    3) Change the priority of an item
""")
}

fun readChoice(): Int {
    return 0
}

fun doWork(list: MutableList<ToDo>, choice: Int) {
}
