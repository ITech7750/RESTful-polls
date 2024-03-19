package ru.tech.kotlin_spring_jdbc.repository

import ru.tech.kotlin_spring_jdbc.model.User

interface UserRepository {

    fun getAll(pageIndex: Int): List<User>

    fun findById(id: Int): User?

    fun create(title: String, quantity: Int?): Int

    fun update(id: Int, title: String, quantity: Int?)

    fun deleteById(id: Int)

    fun getCarStatistics(): Map<String, Int>
}