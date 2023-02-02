package ru.rsavin.socialnetwork.web.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserDto(
    var firstName: String?,
    var secondName: String?,
    var age: Int?,
    var biography: String? = null,
    var city: String?
)