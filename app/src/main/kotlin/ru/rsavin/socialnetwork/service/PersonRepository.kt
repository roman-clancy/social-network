package ru.rsavin.socialnetwork.service

import ru.rsavin.socialnetwork.domain.Person

interface PersonRepository {
    fun save(person: Person): String
    fun getById(id: String): Person
    fun findBySecondNameAndFirstName(secondName: String, firstName: String): List<Person>
    fun addFriend(personId: String, friendId: String)
    fun deleteFriend(personId: String, friendId: String)
}