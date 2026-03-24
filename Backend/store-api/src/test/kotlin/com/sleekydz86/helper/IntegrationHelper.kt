package com.sleekydz86.helper


import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

open class IntegrationHelper {

    @Autowired
    private lateinit var dataSource: DataSource

    private val jdbcTemplate: JdbcTemplate by lazy { JdbcTemplate(dataSource) }

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    protected open fun init() {
        RestAssured.port = port
        RestAssured.basePath = "/api"
        validateH2Database()
        val truncateAllTablesQuery = jdbcTemplate.queryForList(
            "SELECT CONCAT('TRUNCATE TABLE ', TABLE_NAME, ';') AS q FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
            String::class.java
        ).filterNotNull()
        truncateAllTables(truncateAllTablesQuery)
    }

    private fun validateH2Database() {
        jdbcTemplate.queryForObject("SELECT H2VERSION() FROM DUAL", String::class.java)
    }

    private fun truncateAllTables(truncateAllTablesQuery: List<String>) {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0")

        truncateAllTablesQuery.forEach { truncateQuery ->
            jdbcTemplate.execute(truncateQuery)
        }

        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1")
    }
}
