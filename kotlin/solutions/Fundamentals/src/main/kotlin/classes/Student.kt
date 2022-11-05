package classes

class Student(val name: String, val subject: String, val year: Int, val marks: List<Double>) {
    override fun toString(): String {
        return "$name studying $subject in year $year"
    }
    fun average(): Double {
        return marks.average()
    }
}
