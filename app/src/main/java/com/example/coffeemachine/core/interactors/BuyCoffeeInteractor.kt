package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.CoffeeRecipe
import com.example.coffeemachine.core.entities.OptionForBuyingCoffee

import com.example.coffeemachine.core.entities.Price
import com.example.coffeemachine.core.entities.ResponseDataBuying
import com.example.coffeemachine.data.repositories.ActionRepository

class BuyCoffeeInteractor(private val repository: ActionRepository) {
    fun call(obj: OptionForBuyingCoffee): ResponseDataBuying? {
        var msg = ""
        var result: ResponseDataBuying? = null

        if (obj.option == "1" || obj.option == "2" || obj.option == "3" || obj.option == "back") {
            when (obj.option) {
                "1" -> {
                    msg = repository.calculateIngredients(CoffeeRecipe.ESPRESSO)
                    if (msg == "I have enough resources, making you a coffee!")
                        result = ResponseDataBuying("The user bought espresso", Price.ESPRESSO.number, Price.ESPRESSO.currency)

                }

                "2" -> {
                    msg = repository.calculateIngredients(CoffeeRecipe.LATTE)

                    if (msg == "I have enough resources, making you a coffee!")
                        result = ResponseDataBuying("The user bought latte", Price.LATTE.number, Price.LATTE.currency)

                }

                "3" -> {
                    msg = repository.calculateIngredients(CoffeeRecipe.CAPPUCCINO)

                    if (msg == "I have enough resources, making you a coffee!")
                        result = ResponseDataBuying("The user bought cappuccino", Price.CAPPUCCINO.number, Price.CAPPUCCINO.currency)
                }
            }
        } else {
            result = ResponseDataBuying("Invalid option", 0f, "")
        }
        return result

    }
}