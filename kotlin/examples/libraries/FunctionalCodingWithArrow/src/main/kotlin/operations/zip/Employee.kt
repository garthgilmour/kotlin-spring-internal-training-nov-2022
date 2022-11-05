package operations.zip

class Employee(val name: String,
               val age: Int,
               val salary: Double,
               val dept: Department) {
    override fun toString() = "$name of age $age earning $salary working in $dept"
}
