package ru.rsavin.socialnetwork.domain;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PersonalPage {
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<Interest> interests;
    private Integer gender;
    private String city;
    private List<PersonalPage> friends;
}
