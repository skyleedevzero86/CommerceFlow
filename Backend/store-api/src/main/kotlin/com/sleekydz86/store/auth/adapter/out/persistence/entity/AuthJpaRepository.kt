package com.sleekydz86.store.auth.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository

interface AuthJpaRepository : JpaRepository<AuthJpaEntity, Long> {
    fun existsAuthByUsername(username: String): Boolean
    fun findByUsername(username: String): AuthJpaEntity?
}
