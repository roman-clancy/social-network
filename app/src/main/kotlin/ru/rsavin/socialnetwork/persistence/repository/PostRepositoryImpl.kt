package ru.rsavin.socialnetwork.persistence.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Service
import ru.rsavin.socialnetwork.domain.Post
import ru.rsavin.socialnetwork.persistence.Constants
import ru.rsavin.socialnetwork.persistence.PostRowMapper
import ru.rsavin.socialnetwork.service.PostRepository
import java.util.*
import javax.sql.DataSource

@Service
class PostRepositoryImpl(
    dataSource: DataSource
) : PostRepository {
    private var jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)
    private var jdbcInsert: SimpleJdbcInsert = SimpleJdbcInsert(dataSource).withTableName(Constants.POST_TABLE)

    override fun save(post: Post): String {
        val id = UUID.randomUUID().toString()
        val params: Map<String, String?> = with(post) {
            mapOf(
                "id" to id,
                Constants.POST_AUTHOR_ID_COL to authorId,
                Constants.POST_TEXT_COL to text,
            )
        }
        jdbcInsert.executeBatch(params)
        return id
    }

    override fun update(post: Post): Boolean {
        val query = "UPDATE post SET text = ? WHERE id = ? and author_id = ?"
        return jdbcTemplate.update(query, post.text, post.id, post.authorId) > 0
    }

    override fun delete(post: Post): Boolean {
        val query = "DELETE FROM post WHERE id = ? and author_id = ?"
        return jdbcTemplate.update(query, post.id, post.authorId) > 0
    }

    override fun get(postId: String): Post {
        val query = "SELECT * FROM post WHERE id = ?"
        return jdbcTemplate.queryForObject(query, PostRowMapper(), postId)!!
    }

    override fun findAllByAuthor(authorId: String): List<Post> {
        val query = "SELECT * FROM post WHERE authorId = ?"
        return jdbcTemplate.query(query, PostRowMapper(), authorId)
    }
}