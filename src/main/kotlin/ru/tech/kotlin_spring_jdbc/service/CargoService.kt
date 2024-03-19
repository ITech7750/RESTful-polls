package ru.tech.kotlin_spring_jdbc.service

import ru.tech.kotlin_spring_jdbc.dto.UserDto

interface CargoService {

    fun getAll(pageIndex: Int): List<UserDto>

    fun getById(id: Int): UserDto

    fun create(dto: UserDto): Int

    fun update(id: Int, dto: UserDto)

    fun deleteById(id: Int)

    fun getCarStatistics(): Map<String, Int>
}