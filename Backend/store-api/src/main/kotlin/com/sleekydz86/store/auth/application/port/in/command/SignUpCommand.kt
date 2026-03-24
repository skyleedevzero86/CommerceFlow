package com.sleekydz86.store.auth.application.port.`in`.command

import com.sleekydz86.store.auth.adapter.`in`.request.SignUpRequest

data class SignUpCommand(
    val username: String,
    val password: String
) {

    companion object {
        fun from(signUpRequest: SignUpRequest): SignUpCommand {
            return SignUpCommand(
                signUpRequest.username,
                signUpRequest.password
            )
        }
    }
}
