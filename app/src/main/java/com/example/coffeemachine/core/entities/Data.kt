package com.example.coffeemachine.core.entities

enum class CoffeeRecipe(val water: Int, val milk:Int, val coffeeBeans:Int, val price:Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

data class Resources (val water: Int = 0,
                      val milk: Int = 0,
                      val coffeeBeans: Int = 0,
                      val cups: Int = 0,
                      val money: Int = 0)
