package com.sleekydz86.store.product.application.port.`in`

import com.sleekydz86.store.product.application.port.`in`.command.ProductPagingCommand
import com.sleekydz86.store.product.application.port.`in`.command.ProductQueryCommand
import com.sleekydz86.store.product.domain.Product

interface ProductQueryUseCase {

    fun findAllProductWithPaging(command: ProductPagingCommand): List<Product>
    fun findProduct(command: ProductQueryCommand): Product
}
