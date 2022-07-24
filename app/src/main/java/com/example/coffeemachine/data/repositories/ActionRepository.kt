package com.example.coffeemachine.data.repositories

import com.example.coffeemachine.core.entities.CoffeeRecipe
import com.example.coffeemachine.core.entities.Resources
import com.example.coffeemachine.core.entities.Response

interface ActionRepository {
    fun calculateIngredients(coffee: CoffeeRecipe): String
    fun isEnoughIngredients(waterNeed: Int, milkNeed:Int, coffeeBeansNeed: Int): String
    fun getResources(): String
    fun fill(res: Resources)
    fun nullMoney(): Response
}