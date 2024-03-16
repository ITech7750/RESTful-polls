package ru.tech.kotlin_spring_jdbc.service

import org.springframework.stereotype.Service
import ru.devmark.cargo.exception.CargoNotFoundException
import ru.tech.kotlin_spring_jdbc.dto.CargoDto
import ru.tech.kotlin_spring_jdbc.model.Cargo
import ru.tech.kotlin_spring_jdbc.repository.CargoRepository


@Service
class CargoServiceImpl(
    private val cargoRepository: CargoRepository,
) : CargoService {

    override fun getAll(pageIndex: Int): List<CargoDto> =
        cargoRepository.getAll(pageIndex)
            .map { it.toDto() }

    override fun getById(id: Int): CargoDto =
        cargoRepository.findById(id)
            ?.toDto()
            ?: throw CargoNotFoundException(id)

    override fun create(dto: CargoDto): Int =
        cargoRepository.create(dto.title, dto.quantity)

    override fun update(id: Int, dto: CargoDto) {
        cargoRepository.update(id, dto.title, dto.quantity)
    }

    override fun deleteById(id: Int) {
        cargoRepository.deleteById(id)
    }

    override fun getCarStatistics(): Map<String, Int> =
        cargoRepository.getCarStatistics()

    private fun Cargo.toDto() = CargoDto(
        id = id,
        title = title,
        quantity = quantity,
    )
}