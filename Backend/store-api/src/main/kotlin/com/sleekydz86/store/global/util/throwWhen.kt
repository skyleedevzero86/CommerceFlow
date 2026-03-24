package com.sleekydz86.store.global.util

inline fun throwWhen(
    condition: Boolean,
    supplier: () -> RuntimeException,
) {
    if (condition) throw supplier()
}
