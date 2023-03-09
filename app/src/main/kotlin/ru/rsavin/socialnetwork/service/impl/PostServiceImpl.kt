package ru.rsavin.socialnetwork.service.impl

import org.springframework.stereotype.Service
import ru.rsavin.socialnetwork.domain.Person
import ru.rsavin.socialnetwork.domain.Post
import ru.rsavin.socialnetwork.service.PersonRepository
import ru.rsavin.socialnetwork.service.PostRepository
import ru.rsavin.socialnetwork.service.PostService
import ru.rsavin.socialnetwork.service.cache.PostCache

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val personRepository: PersonRepository,
    private val postCache: PostCache
) : PostService {
    override fun addPost(authorId: String, text: String): String {
        val post = Post(
            authorId = authorId,
            text = text
        )
        val newId = postRepository.save(post)
        post.id = newId
        val friends: List<Person> = personRepository.findFriends(authorId)
        friends.forEach {
            postCache.putPost(it.id!!, post)
        }
        return newId
    }

    override fun updatePost(postId: String, authorId: String, text: String) {
        val post = Post(
            id = postId,
            text = text,
            authorId = authorId
        )
        postRepository.update(post)
        postCache.updatePost(post)
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

    override fun getFeed(personId: String, offset: Long, limit: Long): List<Post> = postCache.getPosts(
        personId, limit, offset
    )
}