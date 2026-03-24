package com.sleekydz86.store.product.adapter.`in`.response

import com.sleekydz86.store.product.domain.Product

data class ProductResponse(
    val id: Long,
    val title: String,
    val content: String,
    val price: Int,
    val quantity: Int,
    val viewCount: Int
) {
    companion object {
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id,
                title = product.title,
                content = product.content,
                price = product.price.value,
                quantity = product.quantity.value,
                viewCount = product.viewCount.value
            )
        }
    }
}