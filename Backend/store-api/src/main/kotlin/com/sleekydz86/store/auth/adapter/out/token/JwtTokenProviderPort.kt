package com.sleekydz86.store.auth.adapter.out.token

import com.sleekydz86.store.auth.application.port.out.TokenProviderPort
import com.sleekydz86.store.global.exceptions.TokenExceptionType
import com.sleekydz86.store.global.exceptions.CustomException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProviderPort(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration-period}")
    private val expirationPeriod: Long,
) : TokenProviderPort {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))

    override fun create(id: Long): String {
        val claims = Jwts.claims()
            .id(id.toString())
            .build()

        return Jwts.builder()
            .claims(claims)
            .issuedAt(issuedAt())
            .expiration(expiredAt())
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact()
    }

    private fun issuedAt(): Date {
        val now = LocalDateTime.now()
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant())
    }

    private fun expiredAt(): Date {
        val now = LocalDateTime.now()
        return Date.from(now.plusHours(expirationPeriod).atZone(ZoneId.systemDefault()).toInstant())
    }

    override fun extract(token: String): Long {
        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .body.id.toLong()
        } catch (exception: JwtException) {
            throw handleTokenException(exception)
        } catch (exception: IllegalArgumentException) {
            throw CustomException(TokenExceptionType.TOKEN_INVALID_EXCEPTION)
        }
    }

    private fun handleTokenException(exception: JwtException): IllegalArgumentException {
        return when (exception) {
            is MalformedJwtException -> throw CustomException(TokenExceptionType.TOKEN_MALFORMED_EXCEPTION)
            is ExpiredJwtException -> throw CustomException(TokenExceptionType.TOKEN_EXPIRED_EXCEPTION)
            is UnsupportedJwtException -> throw CustomException(TokenExceptionType.TOKEN_UNSUPPORTED_EXCEPTION)
            is SecurityException -> throw CustomException(TokenExceptionType.TOKEN_SIGNATURE_INVALID_EXCEPTION)
            else -> throw CustomException(TokenExceptionType.TOKEN_INVALID_EXCEPTION)
        }
    }
}
