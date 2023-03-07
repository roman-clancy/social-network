package ru.rsavin.socialnetwork.web.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PostDto(
    var id: String?,
    var text: String?,
    var authorId: String?
)
