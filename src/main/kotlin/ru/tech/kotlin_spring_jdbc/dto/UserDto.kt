package ru.tech.kotlin_spring_jdbc.dto

data class UserDto (
    val id: Int = 0,
    val title: String,
    val quantity: Int? = null,
)