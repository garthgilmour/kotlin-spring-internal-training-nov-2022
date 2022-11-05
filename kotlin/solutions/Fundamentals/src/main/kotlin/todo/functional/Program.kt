package todo.functional

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

fun readChoice() = readLine()?.toInt() ?: 0

fun doWork(list: MutableList<ToDo>, choice: Int) {
    try {
        when (choice) {
            1 -> printContents(list)
            2 -> list.add(readItemDetails())
            3 -> {
                val (id, priority) = readAdjustedDetails()
                if (id != null) {
                    val selected: ToDo? = findItem(list, id)

                    if (selected != null) {
                        selected.priority = priority
                    } else {
                        println("No such item")
                    }
                }
            }
            else -> println("Sorry - $choice is not valid")
        }
    } catch (ex: NumberFormatException) {
        println("Invalid information entered...")
    }
}

private fun printContents(list: MutableList<ToDo>) {
    fun printItem(item: ToDo) {
        println("\t${item.id}\t${item.priority}\t${item.title}")
    }
    if (list.isEmpty()) {
        println("The list is empty")
    } else {
        println("All current items:")
        list.forEach(::printItem)
    }
}

private fun readAdjustedDetails(): Pair<Int?, Int> {
    println("Enter the ID of the item")
    val id = readLine()?.toInt()
    println("Enter the new priority")
    val priority = readLine()?.toInt() ?: 0
    return Pair(id, priority)
}

private fun findItem(list: MutableList<ToDo>, id: Int?) = list.lastOrNull { it.id == id }

private fun readItemDetails(): ToDo {
    println("Enter the ID (0)")
    val id = readLine()?.toInt() ?: 0
    println("Enter the Priority (0)")
    val priority = readLine()?.toInt() ?: 0
    println("Enter the Title (No Title)")
    val title = readLine() ?: "No Title"
    return ToDo(id, title, priority)
}
