package ru.rsavin.socialnetwork

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SocialNetworkApplication

fun main(args: Array<String>) {
    val context = runApplication<SocialNetworkApplication>(*args)
/*    val dataSource = context.getBean(DataSource::class.java)
    val jdbcTemplate = JdbcTemplate(dataSource)
    val lines = FileUtils.readLines(
        File("/Users/rsavin/Projects/social-network/app/src/main/resources/db/people.txt"),
        Charsets.UTF_8
    )
    jdbcTemplate.batchUpdate(
        "INSERT INTO person (id, first_name, second_name, age, city, password) VALUES (?, ?, ?, ?, ?, ?)",
        lines,
        10_000,
    ) { ps, line ->
        val strings = line.split(",")
        ps.setString(1, UUID.randomUUID().toString())
        ps.setString(2, strings[0].split(" ")[0])
        ps.setString(3, strings[0].split(" ")[1])
        ps.setInt(4, strings[1].toInt())
        ps.setString(5, strings[2])
        ps.setString(6, "default")
    }*/
}
