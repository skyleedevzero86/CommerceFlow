package com.sleekydz86.store.product.domain.vo

data class Quantity(var value: Int) {

    init {
        require(value >= 0) { "Quantity cannot be negative." }
    }

    fun decrease() {
        this.value--
    }
}
