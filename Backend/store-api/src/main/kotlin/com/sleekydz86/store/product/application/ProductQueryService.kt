package com.sleekydz86.store.product.application

import com.sleekydz86.store.product.application.port.`in`.ProductQueryUseCase
import com.sleekydz86.store.product.application.port.`in`.command.ProductPagingCommand
import com.sleekydz86.store.product.application.port.`in`.command.ProductQueryCommand
import com.sleekydz86.store.product.application.port.out.ProductRepositoryPort
import com.sleekydz86.store.product.domain.Product
import org.springframework.stereotype.Service

@Service
class ProductQueryService(
    private val productRepositoryPort: ProductRepositoryPort
) : ProductQueryUseCase {

    override fun findAllProductWithPaging(command: ProductPagingCommand): List<Product> {
        val response = productRepositoryPort.findAllWithPaging(
            offset = command.offset,
            limit = command.limit
        )

        return response
    }

    override fun findProduct(command: ProductQueryCommand): Product {
        val product = (productRepositoryPort.findById(command.productId)
            ?: throw IllegalArgumentException("Product with id ${command.productId} not found"))

        product.view()
        productRepositoryPort.save(product)
        return product
    }
}
