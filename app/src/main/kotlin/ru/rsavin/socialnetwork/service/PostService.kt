package ru.rsavin.socialnetwork.service

import ru.rsavin.socialnetwork.domain.Post

interface PostService {
    fun addPost(authorId: String, text: String): String
    fun updatePost(postId: String, authorId: String, text: String)
    fun deletePost(postId: String, authorId: String)
    fun getPost(postId: String): Post
    fun findAllByAuthorId(authorId: String): List<Post>
    fun getFeed(personId: String, offset: Long, limit: Long): List<Post>
}