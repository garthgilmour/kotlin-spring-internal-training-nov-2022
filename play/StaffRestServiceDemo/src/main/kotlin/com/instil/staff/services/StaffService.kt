package com.instil.staff.services

import com.instil.staff.model.Department
import com.instil.staff.model.Department.*
import com.instil.staff.model.Employee
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/staff")
class StaffService(private val staff: List<Employee>) {

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
            ok(staff)
        }
    }
}
