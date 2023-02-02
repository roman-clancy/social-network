package ru.rsavin.socialnetwork.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.rsavin.socialnetwork.service.AuthService
import ru.rsavin.socialnetwork.service.PersonService
import ru.rsavin.socialnetwork.web.dto.*

@RestController
class PersonController(
    private val personService: PersonService,
    private val authService: AuthService
) {

    @PostMapping("/user/register")
    fun register(@RequestBody registerAccountDto: RegisterAccountDto): ResponseEntity<String>? {
        val personId = personService.register(
            registerAccountDto.firstName!!,
            registerAccountDto.secondName!!,
            registerAccountDto.age!!,
            registerAccountDto.city!!,
            registerAccountDto.biography,
            registerAccountDto.password!!
        )
        return ResponseEntity.ok(personId)
    }

    @GetMapping("/user/{id}")
    operator fun get(@PathVariable id: String): ResponseEntity<UserDto?>? {
        val p = personService.getById(id)
        return ResponseEntity.ok(EntityMapper.fromPerson(p))
    }

    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDto): ResponseEntity<TokenResponseDto>? {
        val token = authService.authenticate(dto.id!!, dto.password!!)
        return ResponseEntity.ok(TokenResponseDto(token))
    }

    @GetMapping("/user/search")
    fun search(
        @RequestParam("first_name") firstName: String,
        @RequestParam("second_name") secondName: String
    ): ResponseEntity<List<UserDto>> {
        val persons = personService.search(firstName, secondName)
        return ResponseEntity.ok(persons.map { EntityMapper.fromPerson(it) })
    }

    @GetMapping("/health")
    fun health(): ResponseEntity<String>? {
        return ResponseEntity.ok("Its working")
    }
}