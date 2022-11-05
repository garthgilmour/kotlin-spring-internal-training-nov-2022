package classes

import java.text.DecimalFormat

fun main() {
    val s1 = Student("Joe Bloggs", "Philosophy", 3, listOf(65.4, 66.5, 72.2))
    val s2 = Student("Jane Smith", "Architecture", 2, listOf(56.4, 68.7))

    val df = DecimalFormat(".00")
    println(s1)
    println("\t With current average of: ${df.format(s1.average())}")
    println(s2)
    println("\t With current average of: ${df.format(s2.average())}")
}
