package ru.rsavin.socialnetwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.rsavin.socialnetwork.domain.Person;
import ru.rsavin.socialnetwork.service.AuthService;
import ru.rsavin.socialnetwork.service.PersonService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(String id, String password) {
        Person person = personService.getById(id);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (passwordEncoder.matches(password, person.getPassword())) {
            return UUID.randomUUID().toString();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
    }
}
