package com.sleekydz86.store.product.application.port.out

import com.sleekydz86.store.product.domain.Product

interface ProductRepositoryPort {
    fun findById(id: Long): Product?
    fun save(product: Product): Product
    fun findAllWithPaging(offset: Int?, limit: Int): List<Product>
}
