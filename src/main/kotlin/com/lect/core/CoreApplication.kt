package com.lect.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

@RestController
class Controller{
    @GetMapping
    fun index() = "Nothing"

    @GetMapping("/admin")
    fun secret() = "attack"
}
fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}



