package ru.rsavin.socialnetwork.web.controller

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleDataNotFoundException(ex: Exception?, request: WebRequest?): ResponseEntity<Any?> {
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}