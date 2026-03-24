package com.sleekydz86.store.auth.application

import org.springframework.stereotype.Service
import com.sleekydz86.store.auth.application.port.out.*
import com.sleekydz86.store.auth.domain.service.AuthPasswordEncryptor
import com.sleekydz86.store.auth.application.port.`in`.AuthUseCase
import com.sleekydz86.store.auth.application.port.`in`.command.*
import com.sleekydz86.store.auth.domain.Auth
import com.sleekydz86.store.global.exceptions.*

@Service
class AuthService(
    private val authRepositoryPort: AuthRepositoryPort,
    private val authPasswordEncryptor: AuthPasswordEncryptor,
    private val tokenProviderPort: TokenProviderPort
) : AuthUseCase {

    override fun signUp(command: SignUpCommand): String {
        throwWhen(authRepositoryPort.existsByUsername(command.username)) {
            CustomException(AuthExceptionType.USERNAME_ALREADY_EXISTS_EXCEPTION)
        }

        val savedAuth = authRepositoryPort.save(
            Auth.signUpWithEncryption(
                username = command.username,
                password = command.password,
                authPasswordEncryptor = authPasswordEncryptor
            )
        )

        return tokenProviderPort.create(savedAuth.id)
    }

    override fun signIn(command: SignInCommand): String {
        val auth = authRepositoryPort.findByUsername(command.username)
            ?: throw CustomException(AuthExceptionType.AUTH_NOT_FOUND_EXCEPTION)

        require(auth.matches(command.password, authPasswordEncryptor)) {
            PASSWORD_INVALID_EXCEPTION
        }

        return tokenProviderPort.create(auth.id)
    }
}
