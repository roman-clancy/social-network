package ru.rsavin.socialnetwork.domain

data class Person(
    var id: String? = null,
    var firstName: String,
    var secondName: String,
    var age: Int,
    var biography: String? = null,
    var city: String,
    var password: String
)
