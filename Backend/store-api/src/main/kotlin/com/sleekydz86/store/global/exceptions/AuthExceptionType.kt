package com.sleekydz86.store.global.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class AuthExceptionType : CustomExceptionType {

    AUTH_NOT_FOUND_EXCEPTION {
        override val subject: String = "AUTH_EXCEPTION"
        override val message: String = "인증 정보를 찾을 수 없습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.NOT_FOUND
    },

    USERNAME_ALREADY_EXISTS_EXCEPTION {
        override val subject: String = "AUTH_EXCEPTION"
        override val message: String = "이미 존재하는 username입니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.CONFLICT
    },

    PASSWORD_INVALID_EXCEPTION {
        override val subject: String = "AUTH_EXCEPTION"
        override val message: String = "패스워드가 잘못 됐습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.BAD_REQUEST
    },
}
