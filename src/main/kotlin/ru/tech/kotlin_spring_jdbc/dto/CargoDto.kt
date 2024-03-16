package ru.tech.kotlin_spring_jdbc.dto

data class CargoDto (
    val id: Int = 0,
    val title: String,
    val quantity: Int? = null,
)