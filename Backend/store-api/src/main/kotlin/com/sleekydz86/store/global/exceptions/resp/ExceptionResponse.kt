package com.sleekydz86.store.global.exceptions.resp

import org.springframework.http.HttpStatusCode

data class ExceptionResponse(
    val name: String,
    val customCode: HttpStatusCode,
    val message: String
)
