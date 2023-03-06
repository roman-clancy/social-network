package ru.rsavin.socialnetwork.security

object SecurityContextHolder {
    private val context: MutableMap<String, String> = HashMap()
    fun add(token: String, id: String) = context.put(token, id)
    fun get(authHeader: String): String? = context[authHeader]
}