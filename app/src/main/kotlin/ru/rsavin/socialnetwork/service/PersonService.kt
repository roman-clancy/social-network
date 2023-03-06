package ru.rsavin.socialnetwork.service

import ru.rsavin.socialnetwork.domain.Person

interface PersonService {
    fun register(
        firstName: String,
        secondName: String,
        age: Int,
        city: String,
        biography: String? = null,
        password: String
    ): String

    fun search(
        firstName: String,
        secondName: String
    ): List<Person>

    fun getById(id: String): Person
    fun addFriend(personId: String, friendId: String)
    fun deleteFriend(personId: String, friendId: String)
}