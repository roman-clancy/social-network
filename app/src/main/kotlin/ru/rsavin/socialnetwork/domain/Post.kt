package ru.rsavin.socialnetwork.domain

data class Post(
    var id: String? = null,
    var text: String?,
    var authorId: String?
)