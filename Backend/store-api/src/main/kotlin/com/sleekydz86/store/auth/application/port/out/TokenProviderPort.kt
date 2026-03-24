package com.sleekydz86.store.auth.application.port.out

interface TokenProviderPort {
    fun create(id: Long): String
    fun extract(token: String): Long
}
