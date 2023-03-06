package ru.rsavin.socialnetwork.persistence.repository

import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.rsavin.socialnetwork.domain.Person
import ru.rsavin.socialnetwork.persistence.Constants
import ru.rsavin.socialnetwork.persistence.PersonRowMapper
import ru.rsavin.socialnetwork.service.PersonRepository
import java.util.*
import javax.sql.DataSource

@Service
class PersonRepositoryImpl(
    dataSource: DataSource
) : PersonRepository {
    private var jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)
    private var jdbcInsert: SimpleJdbcInsert = SimpleJdbcInsert(dataSource).withTableName(Constants.PERSON_TABLE)

    override fun save(person: Person): String {
        val id = UUID.randomUUID().toString()
        val params: MutableMap<String, Any> = with(person) {
            mutableMapOf(
                "id" to id,
                Constants.PERSON_FIRST_NAME_COL to firstName,
                Constants.PERSON_SECOND_NAME_COL to secondName,
                Constants.PERSON_PASSWORD_COL to password,
                Constants.PERSON_CITY_COL to city,
                Constants.PERSON_AGE_COL to age,
            )
        }
        person.biography?.let { params[Constants.PERSON_BIOGRAPHY_COL] = it }
        jdbcInsert.executeBatch(params)
        return id
    }

    override fun getById(id: String): Person {
        val query = "SELECT * FROM person WHERE ID = ?"
        return jdbcTemplate.queryForObject(query, PersonRowMapper(), id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }

    override fun findBySecondNameAndFirstName(secondName: String, firstName: String): List<Person> {
        val query = "SELECT * FROM person WHERE second_name LIKE ? AND first_name LIKE ?"
        return jdbcTemplate.query(query, PersonRowMapper(), "$secondName%", "$firstName%")
    }

    override fun addFriend(personId: String, friendId: String) {
        val query = "INSERT INTO friendship(${Constants.TABLE_ID_COL}, person_id, friend_to) VALUES (?, ?, ?), (?, ?, ?);"
        jdbcTemplate.update(
            query,
            UUID.randomUUID().toString(),
            personId,
            friendId,
            UUID.randomUUID().toString(),
            friendId,
            personId
        )
    }

    override fun deleteFriend(personId: String, friendId: String) {
        val query = "DELETE FROM friendship WHERE (person_id = ? AND friend_to = ?) OR (person_id = ? AND friend_to = ?)"
        jdbcTemplate.update(
            query,
            personId,
            friendId,
            friendId,
            personId
        )
    }
}