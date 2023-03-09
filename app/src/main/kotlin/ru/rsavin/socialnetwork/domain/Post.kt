package ru.rsavin.socialnetwork.domain

import java.sql.Timestamp

data class Post(
    var id: String? = null,
    var text: String?,
    var authorId: String?,
    var ts: Timestamp? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Post) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}