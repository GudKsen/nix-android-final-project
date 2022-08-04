package com.example.coffeemachine.core.entities

data class Payment(
    val amount: Float,
    val currency: String,
    var currencyResult: String
)
