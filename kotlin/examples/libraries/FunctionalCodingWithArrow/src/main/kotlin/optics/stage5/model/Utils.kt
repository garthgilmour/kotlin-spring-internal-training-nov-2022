package optics.stage5.model

fun <T> foldOverChildren(initial: String, children: List<T>, indent: String ="\t") = children.fold(initial) { state, child ->
    "$state\n$indent$child"
}
