package com.sleekydz86.store.auth.adapter.`in`.response

import io.swagger.v3.oas.annotations.media.Schema

data class AuthResponse(
    @Schema(
        description = "Access token",
        example = "tokeninfo123123...asdvio3"
    )
    val accessToken: String
) {
}
