package ru.rsavin.socialnetwork.persistence

import org.springframework.jdbc.core.RowMapper
import ru.rsavin.socialnetwork.domain.Person
import java.sql.ResultSet

class PersonRowMapper : RowMapper<Person> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Person = Person(
        id = rs.getString(Constants.PERSON_FIRST_NAME_COL),
        firstName = rs.getString(Constants.PERSON_FIRST_NAME_COL),
        secondName = rs.getString(Constants.PERSON_SECOND_NAME_COL),
        age = rs.getInt(Constants.PERSON_AGE_COL),
        biography = rs.getString(Constants.PERSON_BIOGRAPHY_COL),
        city = rs.getString(Constants.PERSON_CITY_COL),
        password = rs.getString(Constants.PERSON_PASSWORD_COL)
    )
}