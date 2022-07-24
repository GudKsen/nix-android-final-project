package com.example.coffeemachine.core.entities

enum class Price (val number: Float, val currency: String) {
    ESPRESSO(4f, "USD"),
    LATTE(7f, "USD"),
    CAPPUCCINO(6f, "USD")
}

