package ru.rsavin.socialnetwork.web.dto;

import lombok.Data;

@Data
public class RegisterAccountDto {
    private String firstName;
    private String secondName;
    private Integer age;
    private String biography;
    private String city;
    private String password;
}