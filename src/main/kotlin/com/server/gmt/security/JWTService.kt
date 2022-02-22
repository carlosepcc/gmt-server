package com.server.gmt.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

interface JWTService {

    fun extractUserName(token: String): String
    fun validateToken(token: String, userDetails: UserDetails): Boolean
    fun generateToken(userDetails: UserDetails): String

}

@Service
class JWTServiceImplementation : JWTService {

    private val secretKey: String get() = "GMT"

    override fun extractUserName(token: String): String {
        return extractClaim(token, Claims::getSubject) as String
    }

    override fun generateToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, Any> = HashMap()
        val subject = userDetails.username
        claims["roles"] = userDetails.authorities.map { it.authority }
        return createToken(claims, subject)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    override fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUserName(token)
        return (userName == userDetails.username && !isTokenExpired(token))
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration) as Date
    }

    private fun extractClaim(token: String, claimsResolved: Function<Claims, Any>): Any {
        val claims: Claims = extractAllClaims(token)
        return claimsResolved.apply(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secretKey.encodeToByteArray()).parseClaimsJws(token).body
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + (1000 * 60 * 60) * 10))
            .signWith(SignatureAlgorithm.HS256, secretKey.encodeToByteArray()).compact()
    }
}