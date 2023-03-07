package ru.rsavin.socialnetwork.web.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreatePostDto(
    @field:NotNull
    @field:NotBlank
    var text: String?
)
