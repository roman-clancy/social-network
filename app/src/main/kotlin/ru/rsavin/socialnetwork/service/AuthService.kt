package ru.rsavin.socialnetwork.service

interface AuthService {
    fun authenticate(id: String, password: String): String
}