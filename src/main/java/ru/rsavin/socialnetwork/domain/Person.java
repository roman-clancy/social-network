package ru.rsavin.socialnetwork.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Person {
    private String id;
    private String firstName;
    private String secondName;
    private Integer age;
    private String biography;
    private String city;
    private String password;
}
