package anagrams.basic

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

fun print(input1: String, input2: String) {
    if (isAnagram(input1, input2)) {
        println("$input1 and $input2 are anagrams")
    } else {
        println("$input1 and $input2 are NOT anagrams")
    }
}

fun isAnagram(input1: String, input2: String): Boolean {
    if (input1.length != input2.length) {
        return false
    }
    for (char in input1) {
        val firstCount = input1.count { c -> char == c }
        val secondCount = input2.count { c -> char == c }
        if (firstCount != secondCount) {
            return false
        }
    }
    return true
}
