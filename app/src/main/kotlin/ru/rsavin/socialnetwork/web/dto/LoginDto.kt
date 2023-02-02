package ru.rsavin.socialnetwork.web.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LoginDto(
    @field:NotNull
    @field:NotBlank
    var id: String?,
    @field:NotNull
    @field:NotBlank
    var password: String?
)
