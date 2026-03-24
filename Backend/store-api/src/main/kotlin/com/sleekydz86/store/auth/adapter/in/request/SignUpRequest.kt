package com.sleekydz86.store.auth.adapter.`in`.request

import io.swagger.v3.oas.annotations.media.Schema

data class SignUpRequest(
    @Schema(
        description = "회원가입 id",
        example = "root",
    )
    val username: String,

    @Schema(
        description = "회원가입 패스워드",
        example = "root",
    )
    val password: String
)
