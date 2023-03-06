package ru.rsavin.socialnetwork.service

import ru.rsavin.socialnetwork.domain.Post

interface PostService {
    fun addPost(authorId: String, text: String): String
    fun findAllByAuthorId(authorId: String): List<Post>
}