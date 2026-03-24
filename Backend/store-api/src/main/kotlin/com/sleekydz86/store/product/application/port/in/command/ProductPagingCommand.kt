package com.sleekydz86.store.product.application.port.`in`.command

data class ProductPagingCommand(
    val offset: Int?,
    val limit: Int
)
