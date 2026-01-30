package com.example.descuentos.model

data class DescuentosState(
    val priceInput: String = "",
    val discountInput: String = "",
    val total: Double = 0.0,
    val discount: Double = 0.0,
    val validDiscount: Boolean? = null
)
