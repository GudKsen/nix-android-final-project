package com.example.coffeemachine.core.entities

enum class CoffeeRecipe(val water: Int, val milk:Int, val coffeeBeans:Int, val price:Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

data class Resources (var water: Int = 0,
                      var milk: Int = 0,
                      var coffeeBeans: Int = 0,
                      var cups: Int = 0,
                      var money: Int = 0)
