package ru.rsavin.socialnetwork.security

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecurityFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        if (req.servletPath in allowedUrls) {
            filterChain.doFilter(request, response)
            return
        }
        val authHeader = request.getHeader("authorization")
        if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
            res.status = HttpStatus.UNAUTHORIZED.value()
            return
        }
        val userId = SecurityContextHolder.get(authHeader)
        if (userId.isNullOrBlank()) {
            res.status = HttpStatus.UNAUTHORIZED.value()
            return
        }
        filterChain.doFilter(request, response)
    }
    companion object {
        private val allowedUrls = setOf("/login", "/register")
    }
}