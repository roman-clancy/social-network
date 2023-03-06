package ru.rsavin.socialnetwork.service.impl

import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.rsavin.socialnetwork.security.SecurityContextHolder
import ru.rsavin.socialnetwork.service.AuthService
import ru.rsavin.socialnetwork.service.PersonService
import java.util.*

@Service
class AuthServiceImpl(
    private val personService: PersonService,
    private val passwordEncoder: PasswordEncoder
) : AuthService {
    override fun authenticate(id: String, password: String): String {
        val (_, _, _, _, _, _, pwd) = personService.getById(id)
        if (passwordEncoder.matches(password, pwd)) {
            return UUID.randomUUID().toString().also { SecurityContextHolder.add(it, id) }
        }
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password")
    }
}