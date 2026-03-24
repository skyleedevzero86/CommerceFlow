package com.sleekydz86.store.auth.application

import com.sleekydz86.store.auth.application.port.`in`.command.SignInCommand
import com.sleekydz86.store.auth.application.port.`in`.command.SignUpCommand
import com.sleekydz86.store.auth.application.port.out.AuthRepositoryPort
import com.sleekydz86.store.auth.application.port.out.TokenProviderPort
import com.sleekydz86.store.auth.domain.Auth
import com.sleekydz86.store.auth.domain.service.AuthPasswordEncryptor
import com.sleekydz86.store.global.exceptions.AuthExceptionType
import com.sleekydz86.store.global.exceptions.CustomException
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThatThrownBy

class AuthServiceTest : BehaviorSpec({

    val authRepositoryPort: AuthRepositoryPort = mockk()
    val authPasswordEncryptor: AuthPasswordEncryptor = mockk()
    val tokenProviderPort: TokenProviderPort = mockk()

    val authService = AuthService(authRepositoryPort, authPasswordEncryptor, tokenProviderPort)

    val signUpCommand = SignUpCommand(username = "username", password = "password")
    val signInCommand = SignInCommand(username = "username", password = "password")
    val existingAuth = Auth(username = "username", password = "password")

    Given("회원 가입을 할 때") {
        When("닉네임이 이미 존재하면") {
            every { authRepositoryPort.existsByUsername(signUpCommand.username) } returns true

            Then("예외를 발생시킨다") {
                assertThatThrownBy {
                    authService.signUp(signUpCommand)
                }.isInstanceOf(CustomException::class.java)
                    .hasMessageContaining(AuthExceptionType.USERNAME_ALREADY_EXISTS_EXCEPTION.message)
            }
        }

        When("닉네임이 존재하지 않으면") {
            every { authRepositoryPort.existsByUsername(signUpCommand.username) } returns false
            every { authPasswordEncryptor.encrypt(signUpCommand.password) } returns "password"
            every {
                authRepositoryPort.save(
                    match { it.username == signUpCommand.username && it.password == "password" }
                )
            } returns existingAuth
            every { tokenProviderPort.create(existingAuth.id) } returns "token"

            Then("정상 가입이 되고 토큰을 반환한다") {
                val response = authService.signUp(signUpCommand)
                response shouldBe "token"
            }
        }
    }

    Given("로그인을 진행할 때") {
        When("아이디가 존재하지 않으면") {
            every { authRepositoryPort.findByUsername(signInCommand.username) } returns null

            Then("예외를 발생시킨다") {
                assertThatThrownBy {
                    authService.signIn(signInCommand)
                }.isInstanceOf(CustomException::class.java)
                    .hasMessageContaining(AuthExceptionType.AUTH_NOT_FOUND_EXCEPTION.message)
            }
        }

        When("아이디가 존재하고") {
            every { authRepositoryPort.findByUsername(signInCommand.username) } returns existingAuth

            Then("패스워드가 일치하지 않으면 예외를 발생시킨다") {
                every {
                    authPasswordEncryptor.matches(signInCommand.password, existingAuth.password)
                } returns false
                assertThatThrownBy {
                    authService.signIn(signInCommand)
                }.isInstanceOf(IllegalArgumentException::class.java)
                    .hasMessageContaining(AuthExceptionType.PASSWORD_INVALID_EXCEPTION.message)
            }

            Then("패스워드가 일치한다면 토큰을 반환한다") {
                every {
                    authPasswordEncryptor.matches(signInCommand.password, existingAuth.password)
                } returns true
                every { tokenProviderPort.create(existingAuth.id) } returns "token"

                val response = authService.signIn(signInCommand)
                response shouldBe "token"
            }
        }
    }
})
