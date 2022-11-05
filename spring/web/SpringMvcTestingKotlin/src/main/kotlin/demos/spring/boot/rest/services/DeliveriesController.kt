package demos.spring.boot.rest.services

import demos.spring.boot.rest.model.Delivery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/deliveries")
class DeliveriesController {
    @GetMapping(produces = ["application/json"])
    fun viewAllAsJson() = emptyList<Delivery>()
}
