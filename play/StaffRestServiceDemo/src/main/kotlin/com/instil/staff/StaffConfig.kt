package com.instil.staff

import com.instil.staff.model.Department.*
import com.instil.staff.model.Employee
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StaffConfig {
    @Bean
    fun staffData() = mutableListOf(
        Employee("Jane", 30, 20000.0, IT),
        Employee("Fred", 31, 30000.0, HR),
        Employee("Mary", 30, 40000.0, Sales),
        Employee("Bary", 32, 50000.0, IT),
        Employee("Lucy", 30, 60000.0, HR),
        Employee("Dave", 33, 70000.0, Sales)
    )
}
