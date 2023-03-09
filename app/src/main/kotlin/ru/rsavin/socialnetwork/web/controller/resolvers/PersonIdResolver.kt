package ru.rsavin.socialnetwork.web.controller.resolvers

import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import ru.rsavin.socialnetwork.security.PersonId
import ru.rsavin.socialnetwork.security.SecurityContextHolder

class PersonIdResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.getParameterAnnotation(PersonId::class.java) != null


    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val header = webRequest.getHeader("Authorization")!!
        return SecurityContextHolder.get(header)
    }
}