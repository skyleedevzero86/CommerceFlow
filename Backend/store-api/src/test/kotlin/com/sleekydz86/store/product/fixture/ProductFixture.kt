package com.sleekydz86.store.product.fixture

import com.sleekydz86.store.product.domain.Product

class ProductFixture {

    companion object {
        fun defaultProduct(): Product = Product.fromDefaultRule(
            title = "title",
            content = "content",
            price = 100,
            quantity = 100
        )
    }
}
