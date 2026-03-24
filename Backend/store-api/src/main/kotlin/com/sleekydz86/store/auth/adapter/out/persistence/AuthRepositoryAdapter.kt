package com.sleekydz86.store.auth.adapter.out.persistence

import com.sleekydz86.store.auth.domain.Auth
import com.sleekydz86.store.auth.adapter.out.persistence.entity.AuthJpaRepository
import com.sleekydz86.store.auth.application.port.out.AuthRepositoryPort
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import com.sleekydz86.store.auth.adapter.out.persistence.entity.AuthPersistenceMapper

@Transactional(readOnly = true)
@Repository
class AuthRepositoryAdapter(
    private val authJpaRepository: AuthJpaRepository,
    private val authPersistenceMapper: AuthPersistenceMapper
) : AuthRepositoryPort {

    @Transactional
    override fun save(auth: Auth): Auth {
        val authEntity = authPersistenceMapper.toEntity(auth)
        val savedAuth = authJpaRepository.save(authEntity)
        return authPersistenceMapper.toDomain(savedAuth)
    }

    override fun findByUsername(username: String): Auth? {
        val authEntity = authJpaRepository.findByUsername(username)
        return authEntity?.let { authPersistenceMapper.toDomain(it) }
    }

    override fun existsByUsername(username: String): Boolean {
        return authJpaRepository.existsAuthByUsername(username)
    }
}
