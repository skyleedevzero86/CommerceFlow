package com.sleekydz86.store.product.adapter.`in`.request

import com.sleekydz86.store.product.application.port.`in`.command.ProductCreateCommand
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class ProductCreateRequest(
    @Schema(description = "상품명", example = "방어회")
    @NotBlank
    val title: String,

    @Schema(description = "상품 소개", example = "방어회는 참 기름져요")
    @NotBlank
    val content: String,

    @Schema(description = "가격", example = "10000")
    @NotNull
    val price: Int,

    @Schema(description = "수량", example = "10")
    @NotNull
    val quantity: Int
) {

    fun toCommand() = ProductCreateCommand(
        title,
        content,
        price,
        quantity
    )
}
