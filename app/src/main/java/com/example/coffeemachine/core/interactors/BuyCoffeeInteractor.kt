package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.CoffeeRecipe
import com.example.coffeemachine.core.entities.OptionForBuyingCoffee
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.data.repositories.ActionRepository

class BuyCoffeeInteractor(private val repository: ActionRepository) {
    fun call(obj: OptionForBuyingCoffee): Response {
        var msg = ""

        if (obj.option == "1" || obj.option == "2" || obj.option == "3" || obj.option == "back") {
            when (obj.option) {
                "1" -> {
                    msg = repository.calculateIngredients(CoffeeRecipe.ESPRESSO)
                    if (msg == "I have enough resources, making you a coffee!") msg = "The user bought espresso"
                }

                "2" -> {
                    msg = repository.calculateIngredients(CoffeeRecipe.LATTE)
                    if (msg == "I have enough resources, making you a coffee!") msg = "The user bought a latte"
                }

                "3" -> {
                    msg = repository.calculateIngredients(CoffeeRecipe.CAPPUCCINO)
                    if (msg == "I have enough resources, making you a coffee!") msg = "The user bought a cappuccino"
                }
            }
        } else {
            msg = "Invalid option"
        }
        return Response(msg)
    }
}