package ru.rsavin.socialnetwork.persistence

import org.springframework.jdbc.core.RowMapper
import ru.rsavin.socialnetwork.domain.Post
import java.sql.ResultSet

class PostRowMapper : RowMapper<Post> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Post = Post(
        id = rs.getString(Constants.TABLE_ID_COL),
        authorId = rs.getString(Constants.POST_AUTHOR_ID_COL),
        text = rs.getString(Constants.POST_TEXT_COL),
        ts = rs.getTimestamp(Constants.POST_TS_COL)
    )
}