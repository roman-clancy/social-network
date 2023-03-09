package ru.rsavin.socialnetwork.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.rsavin.socialnetwork.domain.Post
import ru.rsavin.socialnetwork.security.PersonId
import ru.rsavin.socialnetwork.service.PostService
import ru.rsavin.socialnetwork.web.dto.*

@RestController
class PostController(
    private val postService: PostService
) {

    @PostMapping("/post/create")
    fun create(
        @RequestBody createPostDto: CreatePostDto,
        @PersonId personId: String
    ): ResponseEntity<String>? {
        val postId = postService.addPost(authorId = personId, createPostDto.text!!)
        return ResponseEntity.ok(postId)
    }

    @PutMapping("/post/update")
    fun update(
        @RequestBody updatePostDto: UpdatePostDto,
        @PersonId personId: String
    ): ResponseEntity<Unit>? {
        postService.updatePost(authorId = personId, postId = updatePostDto.id!!, text = updatePostDto.text!!)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/post/delete/{postId}")
    fun delete(
        @PathVariable postId: String,
        @PersonId personId: String
    ): ResponseEntity<Unit>? {
        postService.deletePost(authorId = personId, postId = postId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/post/get/{postId}")
    fun get(
        @PathVariable postId: String
    ): ResponseEntity<Post>? {
        val post = postService.getPost(postId = postId)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/post/feed")
    fun feed(
        @PersonId personId: String,
        @RequestParam("offset") offset: Long = 0,
        @RequestParam("limit") limit: Long = 1000
    ): ResponseEntity<List<PostDto>>? {
        val posts = postService.getFeed(
            personId = personId,
            offset = offset,
            limit = limit
        )
        return ResponseEntity.ok(posts.map {
            PostDto(
                id = it.id,
                text = it.text,
                authorId = it.authorId
            )
        })
    }
}