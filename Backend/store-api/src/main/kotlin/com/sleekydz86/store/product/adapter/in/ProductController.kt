package com.sleekydz86.store.product.adapter.`in`

import com.sleekydz86.store.global.annotation.AuthMember
import com.sleekydz86.store.product.adapter.`in`.request.ProductCreateRequest
import com.sleekydz86.store.product.adapter.`in`.response.ProductResponse
import com.sleekydz86.store.product.adapter.`in`.response.ProductsPagingResponse
import com.sleekydz86.store.product.application.port.`in`.ProductCommandUseCase
import com.sleekydz86.store.product.application.port.`in`.ProductQueryUseCase
import com.sleekydz86.store.product.application.port.`in`.command.ProductPagingCommand
import com.sleekydz86.store.product.application.port.`in`.command.ProductQueryCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/products")
@RestController
class ProductController(
    private val productCommandUseCase: ProductCommandUseCase,
    private val productQueryUseCase: ProductQueryUseCase
) : ProductApi {

    @PostMapping
    override fun createProduct(
        @AuthMember memberId: Long,
        @RequestBody request: ProductCreateRequest
    ): ResponseEntity<Unit> {
        val savedProductId = productCommandUseCase.createProduct(request.toCommand())
        return ResponseEntity.created(URI.create("/product/${savedProductId}"))
            .build()
    }

    @GetMapping
    override fun findAllProduct(
        @AuthMember memberId: Long,
        @RequestParam(value = "offset", required = true) offset: Int?,
        @RequestParam(value = "limit", required = true, defaultValue = "20") limit: Int
    ): ResponseEntity<ProductsPagingResponse> {
        val result = productQueryUseCase.findAllProductWithPaging(ProductPagingCommand(offset, limit))
        return ResponseEntity.ok(ProductsPagingResponse.from(result))
    }

    override fun findProduct(
        @AuthMember memberId: Long,
        @PathVariable productId: Long
    ): ResponseEntity<ProductResponse> {
        val product = productQueryUseCase.findProduct(ProductQueryCommand(productId))
        return ResponseEntity.ok(ProductResponse.from(product))
    }
}
