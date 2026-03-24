package com.sleekydz86.store.global.exceptions

open class CustomException(
    private val customExceptionType: CustomExceptionType
) : RuntimeException("[${customExceptionType.subject}]: ${customExceptionType.message}") {

    fun getExceptionType(): CustomExceptionType {
        return customExceptionType
    }
}
