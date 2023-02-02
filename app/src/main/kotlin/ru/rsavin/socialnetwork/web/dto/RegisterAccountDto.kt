package ru.rsavin.socialnetwork.web.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RegisterAccountDto(
    @field:NotNull
    @field:NotBlank
    var firstName: String?,
    @field:NotNull
    @field:NotBlank
    var secondName: String?,
    @field:NotNull
    var age: Int?,
    var biography: String? = null,
    @field:NotNull
    @field:NotBlank
    var city: String?,
    @field:NotNull
    @field:NotBlank
    var password: String?
)