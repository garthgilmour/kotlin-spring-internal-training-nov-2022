package counting.chars

fun process(input: String): List<Pair<Char, Int>> {
    val startState = listOf(Pair(input[0], 1))
    return input
        .drop(1)
        .fold(startState) { state, char ->
            val(oldChar, oldCount) = state.last()
            if(char == oldChar) {
                state.dropLast(1)
                    .plus(Pair(oldChar, oldCount + 1))
            } else {
                state.plus(Pair(char, 1))
            }
    }
}
