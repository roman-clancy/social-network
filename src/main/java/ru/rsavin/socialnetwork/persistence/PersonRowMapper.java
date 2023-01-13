package ru.rsavin.socialnetwork.persistence;

import org.springframework.jdbc.core.RowMapper;
import ru.rsavin.socialnetwork.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.builder()
                .firstName(rs.getString(Constants.PERSON_FIRST_NAME_COL))
                .secondName(rs.getString(Constants.PERSON_SECOND_NAME_COL))
                .age(rs.getInt(Constants.PERSON_AGE_COL))
                .biography(rs.getString(Constants.PERSON_BIOGRAPHY_COL))
                .city(rs.getString(Constants.PERSON_CITY_COL))
                .password(rs.getString(Constants.PERSON_PASSWORD_COL))
                .build();
    }
}
