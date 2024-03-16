package ru.tech.kotlin_spring_jdbc.repository

import ru.tech.kotlin_spring_jdbc.model.Cargo

interface CargoRepository {

    fun getAll(pageIndex: Int): List<Cargo>

    fun findById(id: Int): Cargo?

    fun create(title: String, quantity: Int?): Int

    fun update(id: Int, title: String, quantity: Int?)

    fun deleteById(id: Int)

    fun getCarStatistics(): Map<String, Int>
}