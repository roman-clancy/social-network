package ru.rsavin.socialnetwork.service

import ru.rsavin.socialnetwork.domain.Post

interface PostRepository {
    fun save(post: Post): String
    fun findAllByAuthor(authorId: String): List<Post>
}