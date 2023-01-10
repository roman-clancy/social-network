package ru.rsavin.socialnetwork.service;

import ru.rsavin.socialnetwork.domain.Person;

public interface PersonService {
    String register(String firstName, String secondName, Integer age, String biography, String city, String password);
    Person getById(String id);
}
