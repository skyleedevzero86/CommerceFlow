package com.sleekydz86.store.auth.adapter.out.persistence.entity

import org.springframework.stereotype.Component
import com.sleekydz86.store.auth.domain.Auth

@Component
class AuthPersistenceMapper {

    fun toEntity(auth: Auth): AuthJpaEntity {
        return AuthJpaEntity(
            id = auth.id,
            username = auth.username,
            password = auth.password,
        )
    }

    fun toDomain(entity: AuthJpaEntity): Auth {
        return Auth(
            id = entity.id,
            username = entity.username,
            password = entity.password,
        )
    }
}
