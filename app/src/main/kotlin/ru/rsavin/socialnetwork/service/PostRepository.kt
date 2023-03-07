package ru.rsavin.socialnetwork.service

import ru.rsavin.socialnetwork.domain.Post

interface PostRepository {
    fun save(post: Post): String
    fun update(post: Post): Boolean
    fun findAllByAuthor(authorId: String): List<Post>
    fun delete(post: Post): Boolean
    fun get(postId: String): Post
}