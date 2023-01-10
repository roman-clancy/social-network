package ru.rsavin.socialnetwork.web.dto;

import ru.rsavin.socialnetwork.domain.Person;

public class EntityMapper {
    public static UserDto fromPerson(Person from) {
        UserDto dto = new UserDto();
        dto.setFirstName(from.getFirstName());
        dto.setSecondName(from.getSecondName());
        dto.setAge(from.getAge());
        dto.setBiography(from.getBiography());
        dto.setCity(from.getCity());
        return dto;
    }
}
