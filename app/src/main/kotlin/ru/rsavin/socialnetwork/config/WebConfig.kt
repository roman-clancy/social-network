package ru.rsavin.socialnetwork.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ru.rsavin.socialnetwork.web.controller.resolvers.PersonIdResolver

@Configuration
open class WebConfig : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(PersonIdResolver())
    }
}