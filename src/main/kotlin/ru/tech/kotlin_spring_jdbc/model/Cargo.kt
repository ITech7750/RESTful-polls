package ru.tech.kotlin_spring_jdbc.model

data class Cargo(    val id: Int = 0,
                     val title: String,
                     val quantity: Int? = null,)
