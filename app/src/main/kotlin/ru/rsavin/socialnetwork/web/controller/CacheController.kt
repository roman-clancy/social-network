package ru.rsavin.socialnetwork.web.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.rsavin.socialnetwork.service.cache.PostCache

@RestController
class CacheController(
    private val cache: PostCache
) {
    @PostMapping("/post/cache/reload")
    fun reloadCache() {
        cache.updateCache()
    }
}