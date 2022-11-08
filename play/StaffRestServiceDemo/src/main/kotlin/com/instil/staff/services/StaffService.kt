package com.instil.staff.services

import com.instil.staff.model.Department
import com.instil.staff.model.Department.*
import com.instil.staff.model.Employee
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/staff")
class StaffService(private val staff: MutableList<Employee>) {

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun allStaff() = if (staff.isEmpty()) {
        notFound().build()
    } else {
        ok(staff)
    }

    @GetMapping(
        value = ["/byAge/{age}"],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun staffByAge(@PathVariable age: Int) = filterStaff { it.age == age }

    @GetMapping(
        value = ["/byDept/{department}"],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun staffByDept(
        @PathVariable department: Department
    ) = filterStaff { it.department == department }

    @GetMapping(
        value = ["/byName/{name}"],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun staffByName(@PathVariable name: String) = findStaff {
        it.name.equals(name, true)
    }

    @DeleteMapping(
        value = ["/byName/{name}"],
        produces = [TEXT_PLAIN_VALUE]
    )
    fun deleteStaffByName(@PathVariable name: String): ResponseEntity<String> {
        val result = staff.removeIf {
            it.name.equals(name, true)
        }

        return if (result) ok("Deleted $name") else notFound().build()
    }

    @PutMapping(
        consumes = [APPLICATION_JSON_VALUE], //ContentType header
        produces = [TEXT_PLAIN_VALUE]        //Accept header
    )
    fun addStaff(@RequestBody newEmployee: Employee): ResponseEntity<String> {
        fun build404() = badRequest().body("${newEmployee.name} already exists!")
        fun build200() = ok("${newEmployee.name} created")

        return if(alreadyExists(newEmployee)) {
            build404()
        } else {
            staff.add(newEmployee)
            build200()
        }
    }

    private fun alreadyExists(newEmployee: Employee) = staff.any { it.name.equals(newEmployee.name, true) }

    private fun findStaff(predicate: (Employee) -> Boolean): ResponseEntity<Employee> {
        val result = staff.firstOrNull(predicate)

        return if (result == null) {
            notFound().build()
        } else {
            ok(result)
        }
    }

    private fun filterStaff(predicate: (Employee) -> Boolean): ResponseEntity<List<Employee>> {
        val results = staff.filter(predicate)
        return if (results.isEmpty()) {
            notFound().build()
        } else {
            ok(results)
        }
    }
}
