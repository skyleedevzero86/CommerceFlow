package com.sleekydz86.store.product.application.port.`in`

import com.sleekydz86.store.product.application.port.`in`.command.ProductCreateCommand

interface ProductCommandUseCase {
    fun createProduct(command: ProductCreateCommand): Long
}
