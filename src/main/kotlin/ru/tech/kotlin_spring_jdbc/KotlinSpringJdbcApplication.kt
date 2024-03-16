package ru.tech.kotlin_spring_jdbc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringJdbcApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringJdbcApplication>(*args)
}
