package com.sleekydz86.store.product.adapter.out.persistence.entity

import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductJpaEntity, Long> {
}
