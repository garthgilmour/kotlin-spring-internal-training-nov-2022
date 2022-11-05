package anagrams.advanced

infix fun String.isAnagramOf(other: String): Boolean {
    if(this.length != other.length) {
        return false
    }

    val lhs = this.toLowerCase()
    val rhs = other.toLowerCase()

    return lhs.toSet().all { char ->
        fun String.countChars(item: Char) = this.count { item == it }

        lhs.countChars(char) == rhs.countChars(char)
    }
}

fun print(input1: String, input2: String) {
    val result = if (input1 isAnagramOf input2) "are" else "are NOT"
    println("$input1 and $input2 $result anagrams")
}

fun main() {
    //test real anagrams
    print("note", "tone")
    print("listen", "silent")
    print("players", "parsley")
    print("orchestra", "carthorse")
    //test incorrect values
    print("note", "toned")
    print("toned", "note")
    print("patio", "rabid")
    print("fooo", "ffoo")
}
