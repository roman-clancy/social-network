package ru.rsavin.socialnetwork.web.dto

import ru.rsavin.socialnetwork.domain.Person

object EntityMapper {
    fun fromPerson(from: Person): UserDto {
        val dto = UserDto(
            firstName = from.firstName,
            secondName = from.secondName,
            age = from.age,
            biography = from.biography,
            city = from.city
        )
        dto.firstName = from.firstName
        dto.secondName = from.secondName
        dto.age = from.age
        dto.biography = from.biography
        dto.city = from.city
        return dto
    }
}