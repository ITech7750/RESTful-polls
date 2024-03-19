package ru.tech.kotlin_spring_jdbc.service

import org.springframework.stereotype.Service
import ru.devmark.cargo.exception.UserNotFoundException
import ru.tech.kotlin_spring_jdbc.dto.UserDto
import ru.tech.kotlin_spring_jdbc.model.User
import ru.tech.kotlin_spring_jdbc.repository.UserRepository


@Service
class CargoServiceImpl(
        private val cargoRepository: UserRepository,
) : CargoService {

    override fun getAll(pageIndex: Int): List<UserDto> =
        cargoRepository.getAll(pageIndex)
            .map { it.toDto() }

    override fun getById(id: Int): UserDto =
        cargoRepository.findById(id)
            ?.toDto()
            ?: throw UserNotFoundException(id)

    override fun create(dto: UserDto): Int =
        cargoRepository.create(dto.title, dto.quantity)

    override fun update(id: Int, dto: UserDto) {
        cargoRepository.update(id, dto.title, dto.quantity)
    }

    override fun deleteById(id: Int) {
        cargoRepository.deleteById(id)
    }

    override fun getCarStatistics(): Map<String, Int> =
        cargoRepository.getCarStatistics()

    private fun User.toDto() = UserDto(
        id = id,
        title = title,
        quantity = quantity,
    )
}