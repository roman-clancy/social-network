package ru.rsavin.socialnetwork.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.rsavin.socialnetwork.domain.Person
import ru.rsavin.socialnetwork.service.PersonRepository
import ru.rsavin.socialnetwork.service.PersonService

@Service
class PersonServiceImpl(
    private val personRepository: PersonRepository,
    private val passwordEncoder: PasswordEncoder
) : PersonService {
    override fun register(
        firstName: String,
        secondName: String,
        age: Int,
        city: String,
        biography: String?,
        password: String
    ): String {
        val person = Person(
            firstName = firstName,
            secondName = secondName,
            age = age,
            city = city,
            biography = biography,
            password = passwordEncoder.encode(password)
        )
        return personRepository.save(person)
    }

    override fun search(firstName: String, secondName: String): List<Person> =
        personRepository.findBySecondNameAndFirstName(secondName, firstName)

    override fun getById(id: String): Person = personRepository.getById(id)
}