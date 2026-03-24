package com.sleekydz86.store.product.domain.vo

data class ViewCount(
    var value: Int
) {
    fun view() {
        this.value++;
    }

    companion object {
        private const val DEFAULT_VIEW_COUNT = 0

        fun createDefault() = ViewCount(DEFAULT_VIEW_COUNT)
    }
}

