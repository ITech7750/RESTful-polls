package ru.tech.kotlin_spring_jdbc.controller


import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.tech.kotlin_spring_jdbc.dto.UserDto
import ru.tech.kotlin_spring_jdbc.service.CargoService


@RestController
@RequestMapping("api/v1")
@Tag(
    name = "Пользователи",
    description = "Все методы для работы с пользователями системы",
)

class RestController(
        private val serverService: CargoService,
) {

    @GetMapping
    @Operation(method = "Получение всех пользователей со страницы")
    fun getAll(
        @RequestParam("page") pageIndex: Int,
    ): List<UserDto> = serverService.getAll(pageIndex)

    @GetMapping("/{id}")
    @Operation(method = "Получение пользователя по id")
    fun getById(@PathVariable id: Int): UserDto = serverService.getById(id)

    @GetMapping("/statistics")
    @Operation(method = "Статистика пользователей")
    fun getCarStatistics(): Map<String, Int> =
        serverService.getCarStatistics()

    @PostMapping
    @Operation(method = "Создать новую учетную запись")
    fun create(@RequestBody dto: UserDto): Int =
        serverService.create(dto)

    @PutMapping("/{id}")
    @Operation(method = "Изменить учетную запись")
    fun update(@PathVariable id: Int, @RequestBody dto: UserDto) {
        serverService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    @Operation(method = "Удалить учетную запись")
    fun deleteById(@PathVariable id: Int) {
        serverService.deleteById(id)
    }


}