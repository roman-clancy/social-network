package ru.rsavin.socialnetwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rsavin.socialnetwork.domain.Person;
import ru.rsavin.socialnetwork.persistence.repository.PersonRepository;
import ru.rsavin.socialnetwork.service.PersonService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String register(String firstName, String secondName, Integer age, String biography, String city, String password) {
        Person build = Person.builder()
                .firstName(firstName)
                .secondName(secondName)
                .age(age)
                .city(city)
                .biography(biography)
                .password(passwordEncoder.encode(password))
                .build();
        return personRepository.save(build);
    }

    @Override
    public Person getById(String id) {
        return personRepository.getById(id);
    }
}
