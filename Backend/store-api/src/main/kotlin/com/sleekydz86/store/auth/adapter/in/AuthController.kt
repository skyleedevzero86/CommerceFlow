package com.sleekydz86.store.auth.adapter.`in`

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.sleekydz86.store.auth.adapter.`in`.request.SignUpRequest
import com.sleekydz86.store.auth.adapter.`in`.request.SignInRequest
import com.sleekydz86.store.auth.adapter.`in`.response.AuthResponse
import com.sleekydz86.store.auth.application.port.`in`.command.SignInCommand
import com.sleekydz86.store.auth.application.port.`in`.command.SignUpCommand
import com.sleekydz86.store.auth.application.port.`in`.AuthUseCase

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authUseCase: AuthUseCase,
) : AuthApi {

    @PostMapping("/sign-up")
    override fun signUp(request: SignUpRequest): ResponseEntity<AuthResponse> {
        val token = authUseCase.signUp(SignUpCommand.from(request))
        return status(HttpStatus.CREATED)
            .body(AuthResponse(token))
    }

    @GetMapping("/sign-in")
    override fun signIn(request: SignInRequest): ResponseEntity<AuthResponse> {
        val token = authUseCase.signIn(SignInCommand.from(request))
        return ok(AuthResponse(token))
    }
}
