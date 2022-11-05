package toolkit.basic

import java.lang.Math.random
import java.text.DecimalFormat

fun main() {
    val formatter = DecimalFormat(".00")
    val input = (1..10).map { (1..10).map { random() } }
    //Print out all the numbers which are greater that 0.5
    // formatted to two decimal places via 'formatter'
}
