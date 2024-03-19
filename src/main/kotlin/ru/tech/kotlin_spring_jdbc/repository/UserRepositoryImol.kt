package ru.tech.kotlin_spring_jdbc.repository
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import ru.tech.kotlin_spring_jdbc.model.User
import ru.tech.kotlin_spring_jdbc.util.getIntOrNull


@Repository
class UserRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : UserRepository {

    override fun getAll(pageIndex: Int): List<User> =
        jdbcTemplate.query(
            "select * from cargo_table order by id limit :limit offset :offset",
            mapOf(
                "limit" to PAGE_SIZE,
                "offset" to PAGE_SIZE * pageIndex,
            ),
            ROW_MAPPER
        )

    override fun findById(id: Int): User? =
        jdbcTemplate.query(
            "select * from cargo_table where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).firstOrNull()

    override fun create(title: String, quantity: Int?): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into cargo_table (title,quantity) values (:title, :quantity)",
            MapSqlParameterSource(
                mapOf(
                    "title" to title,
                    "quantity" to quantity,
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return keyHolder.keys?.getValue("id") as Int
    }

    override fun update(id: Int, title: String, quantity: Int?) {
        jdbcTemplate.update(
            "update cargo_table set title = :title, quantity = :quantity where id = :id",
            mapOf(
                "id" to id,
                "title" to title,
                "quantity" to quantity,
            )
        )
    }

    override fun deleteById(id: Int) {
        jdbcTemplate.update(
            "delete from cargo_table where id = :id",
            mapOf(
                "id" to id,
            )
        )
    }

    override fun getCarStatistics(): Map<String, Int> =
        jdbcTemplate.query(
            """select cb.title, count(c.id) from cargo c
                   join car_brand cb on c.brand_id = cb.id
                   group by cb.title""",
            EXTRACTOR,
        )!!

    private companion object {
        const val PAGE_SIZE = 3
        val ROW_MAPPER = RowMapper<User> { rs, _ ->
            User(
                id = rs.getInt("id"),
                title = rs.getString("title"),
                quantity = rs.getIntOrNull("quantity"),
            )
        }

        val EXTRACTOR = ResultSetExtractor<Map<String, Int>> { rs ->
            val result = mutableMapOf<String, Int>()
            while(rs.next()) {
                val title = rs.getString("title")
                result.getOrPut(title) { 0 }
                result[title] = result.getValue(title) + rs.getInt("count")
            }
            result
        }
    }
}