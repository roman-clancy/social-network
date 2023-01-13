package ru.rsavin.socialnetwork.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class RegisterAccountDto {
    @NotNull private String firstName;
    @NotNull private String secondName;
    private Integer age;
    private String biography;
    private String city;
    @NotNull private String password;
}
