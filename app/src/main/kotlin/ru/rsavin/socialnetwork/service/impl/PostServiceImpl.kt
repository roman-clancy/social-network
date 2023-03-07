package ru.rsavin.socialnetwork.service.impl

import org.springframework.stereotype.Service
import ru.rsavin.socialnetwork.domain.Post
import ru.rsavin.socialnetwork.service.PostRepository
import ru.rsavin.socialnetwork.service.PostService

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    override fun addPost(authorId: String, text: String): String {
        val post = Post(
            authorId = authorId,
            text = text
        )
        return postRepository.save(post)
    }

    override fun updatePost(postId: String, authorId: String, text: String) {
        val post = Post(
            id = postId,
            text = text,
            authorId = authorId
        )
        postRepository.update(post)
    }
    override fun deletePost(postId: String, authorId: String) {
        val post = Post(
            id = postId,
            authorId = authorId,
            text = null
        )
        postRepository.delete(post)
    }

    override fun getPost(postId: String): Post = postRepository.get(postId)

    override fun findAllByAuthorId(authorId: String): List<Post> =
        postRepository.findAllByAuthor(authorId)
}