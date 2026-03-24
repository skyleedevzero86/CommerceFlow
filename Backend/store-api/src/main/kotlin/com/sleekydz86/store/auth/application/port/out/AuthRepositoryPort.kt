package com.sleekydz86.store.auth.application.port.out

import com.sleekydz86.store.auth.domain.Auth

interface AuthRepositoryPort {
    fun save(auth: Auth): Auth
    fun findByUsername(username: String): Auth?
    fun existsByUsername(username: String): Boolean
}
