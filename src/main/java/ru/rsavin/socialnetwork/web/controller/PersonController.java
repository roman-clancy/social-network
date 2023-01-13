package ru.rsavin.socialnetwork.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rsavin.socialnetwork.domain.Person;
import ru.rsavin.socialnetwork.service.AuthService;
import ru.rsavin.socialnetwork.service.PersonService;
import ru.rsavin.socialnetwork.web.dto.*;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final AuthService authService;

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody RegisterAccountDto registerAccountDto) {
        String personId = personService.register(
                registerAccountDto.getFirstName(),
                registerAccountDto.getSecondName(),
                registerAccountDto.getAge(),
                registerAccountDto.getBiography(),
                registerAccountDto.getCity(),
                registerAccountDto.getPassword()
        );
        return ResponseEntity.ok(personId);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> get(@PathVariable String id) {
        Person p = personService.getById(id);
        return ResponseEntity.ok(EntityMapper.fromPerson(p));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto dto) {
        String token = authService.authenticate(dto.id(), dto.password());
        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Its working");
    }
}
