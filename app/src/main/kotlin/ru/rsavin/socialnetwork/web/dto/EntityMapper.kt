package ru.rsavin.socialnetwork.web.dto

import ru.rsavin.socialnetwork.domain.Person
import ru.rsavin.socialnetwork.domain.Post

object EntityMapper {
    fun fromPerson(from: Person): UserDto = UserDto(
        id = from.id,
        firstName = from.firstName,
        secondName = from.secondName,
        age = from.age,
        biography = from.biography,
        city = from.city
    )

    fun from(from: Post): PostDto = PostDto(
        id = from.id,
        text = from.text,
        authorId = from.authorId
    )
}