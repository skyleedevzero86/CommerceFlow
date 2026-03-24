package com.sleekydz86.store.product.adapter.`in`

import com.sleekydz86.store.global.annotation.AuthMember
import com.sleekydz86.store.product.adapter.`in`.request.ProductCreateRequest
import com.sleekydz86.store.product.adapter.`in`.response.ProductResponse
import com.sleekydz86.store.product.adapter.`in`.response.ProductsPagingResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Product API")
interface ProductApi {

    @SecurityRequirement(name = "jwt")
    @Operation(summary = "상품 등록")
    @PostMapping
    fun createProduct(
        @AuthMember memberId: Long,
        @RequestBody request: ProductCreateRequest
    ): ResponseEntity<Unit>

    @SecurityRequirement(name = "jwt")
    @Operation(summary = "상품 전체 조회")
    @GetMapping
    fun findAllProduct(
        @AuthMember memberId: Long,
        @RequestParam(value = "offset", required = true) offset: Int?,
        @RequestParam(value = "limit", required = true, defaultValue = "20") limit: Int
    ): ResponseEntity<ProductsPagingResponse>

    @SecurityRequirement(name = "jwt")
    @Operation(summary = "상품 단건 조회")
    @GetMapping("/{productId}")
    fun findProduct(
        @AuthMember memberId: Long,
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "product id",
            example = "1",
            required = true,
        )
        @PathVariable productId: Long,
    ): ResponseEntity<ProductResponse>
}
