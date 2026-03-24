package com.sleekydz86.store.product.adapter.out.persistence.entity

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.sleekydz86.store.product.adapter.out.persistence.entity.QProductJpaEntity.productJpaEntity
import org.springframework.stereotype.Repository

@Repository
class ProductQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {

    fun findAllWithPaging(offset: Int?, limit: Int): List<ProductJpaEntity> {
        return jpaQueryFactory.selectFrom(productJpaEntity)
            .where(ltProductId(offset))
            .orderBy(productJpaEntity.id.asc())
            .limit(limit.toLong())
            .fetch()
    }

    private fun ltProductId(offset: Int?): BooleanExpression? {
        return offset?.let {
            productJpaEntity.id.gt(it)
        }
    }
}
