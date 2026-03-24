package com.sleekydz86.store.product.adapter.out.persistence.entity

import com.sleekydz86.store.product.domain.Product
import com.sleekydz86.store.product.domain.vo.Price
import com.sleekydz86.store.product.domain.vo.Quantity
import com.sleekydz86.store.product.domain.vo.ViewCount
import org.springframework.stereotype.Component

@Component
class ProductPersistenceMapper {

    fun toEntity(product: Product): ProductJpaEntity {
        return ProductJpaEntity(
            id = product.id,
            title = product.title,
            content = product.content,
            price = product.price.value,
            quantity = product.quantity.value,
            viewCount = product.viewCount.value,
        )
    }

    fun toDomain(entity: ProductJpaEntity): Product {
        return Product(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            price = Price(entity.price),
            quantity = Quantity(entity.quantity),
            viewCount = ViewCount(entity.viewCount)
        )
    }
}