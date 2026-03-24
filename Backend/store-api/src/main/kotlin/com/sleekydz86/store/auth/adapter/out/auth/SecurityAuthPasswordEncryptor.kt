package com.sleekydz86.store.auth.adapter.out.auth

import com.sleekydz86.store.auth.domain.service.AuthPasswordEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityAuthPasswordEncryptor(
    private val passwordEncoder: PasswordEncoder
) : AuthPasswordEncryptor {

    override fun encrypt(password: String): String {
        return requireNotNull(passwordEncoder.encode(password)) {
            "비밀번호 인코딩 결과가 비어 있습니다."
        }
    }

    override fun matches(password: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(password, encodedPassword)
    }
}
