package ru.tech.kotlin_spring_jdbc.model

data class User(val id: Int = 0,
                val title: String,
                val quantity: Int? = null,)
