package ru.rsavin.socialnetwork.web.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdatePostDto(
    @field:NotNull
    @field:NotBlank
    var id: String?,
    @field:NotNull
    @field:NotBlank
    var text: String?
)
