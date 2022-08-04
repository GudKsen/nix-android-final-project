package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.CoffeeRecipe
import com.example.coffeemachine.core.entities.OptionForBuyingCoffee
import com.example.coffeemachine.core.entities.Payment

class GetPricesCoffee {
        fun getPrice(coffee: OptionForBuyingCoffee, currencyRes: String): Payment {
            if (coffee.option == "1") {
                return Payment(CoffeeRecipe.ESPRESSO.price.toFloat(), "USD", currencyRes)
            } else if (coffee.option == "2") {
                return Payment(CoffeeRecipe.LATTE.price.toFloat(), "USD", currencyRes)
            } else if (coffee.option =="3") {
                return Payment(CoffeeRecipe.CAPPUCCINO.price.toFloat(), "USD", currencyRes)
            }
            return Payment(0f, "null", "null")
        }
}