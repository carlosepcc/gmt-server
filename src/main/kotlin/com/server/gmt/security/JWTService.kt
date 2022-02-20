package com.server.gmt.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

interface JWTService {
    val secretKey: String
    fun extractUserName(token: String): String
    fun generateToken(userDetails: UserDetails): String
    fun isTokenExpired(token: String): Boolean
    fun validateToken(token: String, userDetails: UserDetails): Boolean
    fun extractExpiration(token: String): Date
    fun extractClaim(token: String, claimsResolved: Function<Claims, Any>): Any
    fun extractAllClaims(token: String): Claims
    fun createToken(claims: Map<String, Any>, subject: String): String
}

@Service
class JWTServiceImplementation : JWTService {

    override val secretKey: String get() = "GMT"

    override fun extractUserName(token: String): String {
        return extractClaim(token, Claims::getSubject) as String
    }

    override fun generateToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, Any> = HashMap()
        val subject = userDetails.username
        return createToken(claims, subject)
    }

    override fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    override fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUserName(token)
        return (userName == userDetails.username && !isTokenExpired(token))
    }

    override fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration) as Date
    }

    override fun extractClaim(token: String, claimsResolved: Function<Claims, Any>): Any {
        val claims: Claims = extractAllClaims(token)
        return claimsResolved.apply(claims)
    }

    override fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secretKey.encodeToByteArray()).parseClaimsJws(token).body
    }

    override fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + (1000 * 60 * 60) * 10))
            .signWith(SignatureAlgorithm.HS256, secretKey.encodeToByteArray()).compact()
    }
}