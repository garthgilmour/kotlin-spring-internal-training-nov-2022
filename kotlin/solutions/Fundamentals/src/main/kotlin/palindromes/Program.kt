package palindromes

fun main() {
    print("A man, a plan, a canal, Panama")
    print("Gold is where you find it")
    print("If I had a hi-fi")
    print("Fortune favors the prepared mind")
    print("Rats live on no evil star")
    print("There is no abstract living")
    print("Some men interpret nine memos")
}

fun print(input: String) {
    if (isPalindrome(input)) {
        println("$input is a palindrome")
    } else {
        println("$input is NOT a palindrome")
    }
}

fun isPalindrome(input: String): Boolean {
    val regex = Regex("[^a-z]+")
    val canonical = input.toLowerCase().replace(regex, "")

    var forward = 0
    var backward = canonical.length - 1

    while (forward < backward) {
        if (canonical[forward] != canonical[backward]) {
            return false
        }
        forward++
        backward--
    }
    return true
}
