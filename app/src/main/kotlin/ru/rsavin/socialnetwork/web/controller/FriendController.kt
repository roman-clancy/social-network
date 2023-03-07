package ru.rsavin.socialnetwork.web.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import ru.rsavin.socialnetwork.security.PersonId
import ru.rsavin.socialnetwork.service.PersonService

@RestController
class FriendController(private val personService: PersonService) {
    @PutMapping("/friend/set/{friendId}")
    fun addFriend(
        @PersonId personId: String,
        @PathVariable friendId: String
    ) {
        personService.addFriend(personId, friendId)
    }

    @PutMapping("/friend/delete/{friendId}")
    fun deleteFriend(
        @PersonId personId: String,
        @PathVariable friendId: String
    ) {
        personService.deleteFriend(personId, friendId)
    }
}