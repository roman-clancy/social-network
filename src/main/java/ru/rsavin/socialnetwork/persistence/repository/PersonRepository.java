package ru.rsavin.socialnetwork.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.rsavin.socialnetwork.domain.Person;
import ru.rsavin.socialnetwork.persistence.Constants;
import ru.rsavin.socialnetwork.persistence.PersonRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class PersonRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public PersonRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(Constants.PERSON_TABLE);
    }

    public String save(Person person) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> params = new HashMap<>() {{
            put("id", id);
            put(Constants.PERSON_FIRST_NAME_COL, person.getFirstName());
            put(Constants.PERSON_SECOND_NAME_COL, person.getSecondName());
            put(Constants.PERSON_PASSWORD_COL, person.getPassword());
            if (person.getBiography() != null) put(Constants.PERSON_BIOGRAPHY_COL, person.getBiography());
            if (person.getCity() != null) put(Constants.PERSON_CITY_COL, person.getCity());
            if (person.getAge() != null) put(Constants.PERSON_AGE_COL, person.getAge());
        }};
        jdbcInsert.execute(params);
        return id;
    }

    public Person getById(String id) {
        final String query = "SELECT * FROM person WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new PersonRowMapper(), id);
    }
}
