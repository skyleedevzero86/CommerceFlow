package com.sleekydz86.store.global.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class TokenExceptionType : CustomExceptionType {

    TOKEN_SIGNATURE_INVALID_EXCEPTION {
        override val subject: String = "JWT_EXCEPTION"
        override val message: String = "토큰의 서명이 잘못 되었습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.UNAUTHORIZED
    },

    TOKEN_MALFORMED_EXCEPTION {
        override val subject: String = "JWT_EXCEPTION"
        override val message: String = "토큰의 형식이 올바르지 않습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.UNAUTHORIZED
    },

    TOKEN_EXPIRED_EXCEPTION {
        override val subject: String = "JWT_EXCEPTION"
        override val message: String = "토큰이 만료되었습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.UNAUTHORIZED
    },

    TOKEN_UNSUPPORTED_EXCEPTION {
        override val subject: String = "JWT_EXCEPTION"
        override val message: String = "지원하지 않는 토큰의 형식입니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.UNAUTHORIZED
    },

    TOKEN_INVALID_EXCEPTION {
        override val subject: String = "JWT_EXCEPTION"
        override val message: String = "토큰의 값이 유효하지 않습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.UNAUTHORIZED
    },
}
