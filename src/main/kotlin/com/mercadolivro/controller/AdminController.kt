package com.mercadolivro.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin")
class AdminController {

    @GetMapping("/reports")
    fun getCustomers(): String {
        return "This is a Report. Only Admin can see it!"

    }


}