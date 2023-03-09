package ru.rsavin.socialnetwork.service.cache

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.rsavin.socialnetwork.domain.Post
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.ConcurrentSkipListSet
import java.util.stream.Collectors
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Component
class PostCache(
    private val dataSource: DataSource
) {

    fun putPost(personId: String, post: Post) {
        var insertedPost = post
        if (posts.contains(post)) {
            insertedPost = posts.find { it == post }!!
        } else {
            posts.add(post)
        }
        cache.getOrPut(personId) {
            ConcurrentSkipListSet(comparator)
        }.let { set ->
            if (set.size == 1000) {
                set.remove(set.last())
            }
            set.add(insertedPost)
        }
    }

    fun updatePost(post: Post) {
        posts.find { it == post }?.let {
            it.text = post.text
        }
    }

    fun updateCacheForPerson(personId: String) {
        val template = JdbcTemplate(dataSource)
        val query = """
            select 
                p.id,
                p."text",
                p.author_id,
                p.ts
            from post p 
            where p.author_id in (SELECT friend_to FROM friendship WHERE person_id = ${personId})
        """.trimIndent()
        template.query(query) { resultSet ->
            val post = Post(
                id = resultSet.getString(1),
                text = resultSet.getString(2),
                authorId = resultSet.getString(3),
                ts = resultSet.getTimestamp(4)
            )
            this.putPost(personId, post)
        }
    }

    fun getPosts(personId: String, limit: Long, offset: Long) =
        cache.getOrDefault(personId, emptyList()).stream().skip(offset).limit(limit).collect(Collectors.toList())

    @PostConstruct
    fun updateCache() {
        cache = ConcurrentHashMap()
        posts = HashSet()
        val template = JdbcTemplate(dataSource)
        val query = """
            select 
                p.id,
                p."text",
                p.author_id,
                p.ts,
                f.friend_to 
            from post p 
                left join friendship f on p.author_id = f.person_id where f.friend_to is not null
        """.trimIndent()
        template.query(query) { resultSet ->
            val post = Post(
                id = resultSet.getString(1),
                text = resultSet.getString(2),
                authorId = resultSet.getString(3),
                ts = resultSet.getTimestamp(4)
            )
            val personId = resultSet.getString(5)
            this.putPost(personId, post)
        }
    }

    companion object {
        private val comparator: Comparator<Post?> = nullsLast(compareByDescending<Post> { it.ts }.thenBy { it.id })
        private var cache: ConcurrentMap<String, MutableSet<Post>> = ConcurrentHashMap()
        private var posts: MutableSet<Post> = HashSet()
    }
}