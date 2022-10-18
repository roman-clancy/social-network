package ru.rsavin.socialnetwork.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalPageController {

    @PostMapping("/account")
    public ResponseEntity<Object> register() {
        return ResponseEntity.ok().build();
    }

}
