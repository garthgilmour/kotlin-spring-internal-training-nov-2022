package com.instil.staff.services

import com.instil.staff.model.Department
import com.instil.staff.model.Department.*
import com.instil.staff.model.Employee
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/staff")
class StaffService {
    private val staff = listOf(
        Employee("Jane", 30, 20000.0, IT),
        Employee("Fred", 31, 30000.0, HR),
        Employee("Mary", 30, 40000.0, Sales),
        Employee("Bary", 32, 50000.0, IT),
        Employee("Lucy", 30, 60000.0, HR),
        Employee("Dave", 33, 70000.0, Sales)
    )

    @GetMapping(produces=[APPLICATION_JSON_VALUE])
    fun allStaff() = staff

    @GetMapping(
        value=["/byAge/{age}"],
        produces=[APPLICATION_JSON_VALUE]
    )
    fun staffByAge(@PathVariable age: Int) = staff.filter {
        it.age == age
    }

    @GetMapping(
        value=["/byDept/{department}"],
        produces=[APPLICATION_JSON_VALUE]
    )
    fun staffByDept(@PathVariable department: Department) = staff.filter {
        it.department == department
    }

    @GetMapping(
        value=["/byName/{name}"],
        produces=[APPLICATION_JSON_VALUE]
    )
    fun staffByName(@PathVariable name: String) = staff.firstOrNull {
        it.name.equals(name, true)
    }
}
