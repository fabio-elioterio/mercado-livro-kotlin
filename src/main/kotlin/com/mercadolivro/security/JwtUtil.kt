package com.mercadolivro.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${spring.jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${spring.jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: String): String {
        return Jwts.builder()
                .setSubject(id)
                .setExpiration(Date(System.currentTimeMillis() + expiration!!))
                .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
                .compact()
    }

}