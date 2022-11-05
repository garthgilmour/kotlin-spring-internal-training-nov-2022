package anagrams

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
    return false
}
