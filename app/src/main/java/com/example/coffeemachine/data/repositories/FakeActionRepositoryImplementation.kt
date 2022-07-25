package com.example.coffeemachine.data.repositories

import com.example.coffeemachine.core.entities.CoffeeRecipe
import com.example.coffeemachine.core.entities.Resources
import com.example.coffeemachine.core.entities.Response

class FakeActionRepositoryImplementation: ActionRepository {
     object  coffeeMachine {
        var water = 0
        var milk = 0
        var coffeeBeans = 0
        var cups = 0
        var money = 0
    }

    override fun calculateIngredients(coffee: CoffeeRecipe): String {
        val msg: String = isEnoughIngredients(coffee.water, coffee.milk, coffee.coffeeBeans)
        if (msg == "I have enough resources, making you a coffee!") {
            coffeeMachine.water -= coffee.water
            coffeeMachine.milk -= coffee.milk
            coffeeMachine.coffeeBeans -= coffee.coffeeBeans
            coffeeMachine.money += coffee.price
            coffeeMachine.cups -= 1
        }
        return msg
    }

    override fun isEnoughIngredients(waterNeed: Int, milkNeed:Int, coffeeBeansNeed: Int): String {
        val message: String
        var counterForCups = 0

        var waterTmp = coffeeMachine.water
        var milkTmp = coffeeMachine.milk
        var coffeeBeansTmp = coffeeMachine.coffeeBeans
        var cupsTmp = coffeeMachine.cups

        while (true) {
            waterTmp -= waterNeed
            milkTmp -= milkNeed
            coffeeBeansTmp -= coffeeBeansNeed
            cupsTmp -= 1
            if (waterTmp >= 0 && milkTmp >= 0 && coffeeBeansTmp >= 0 && cupsTmp >= 0) {
                counterForCups++
            }
            else {
                break
            }
        }

        message = if (counterForCups > 0) {
            "I have enough resources, making you a coffee!"
        } else  {
            when {
                waterTmp <= 0 -> {
                    "Sorry, not enough water!"
                }
                milkTmp <= 0 -> {
                    "Sorry, not enough milk!"
                }
                coffeeBeansTmp <= 0 -> {
                    "Sorry, not enough coffee beans!"
                }
                else -> {
                    "Sorry, not enough cups!"
                }
            }
        }
        return message
    }

    override fun fill(res: Resources) {
        coffeeMachine.water += res.water
        coffeeMachine.milk += res.milk
        coffeeMachine.coffeeBeans += res.coffeeBeans
        coffeeMachine.cups += res.cups
    }

    override fun getResources(): String {
        var str = ""
        str += "${coffeeMachine.water} of water\n"
        str += "${coffeeMachine.milk} of milk\n"
        str += "${coffeeMachine.coffeeBeans} of coffee beans\n"
        str += "${coffeeMachine.cups} of cups\n"
        str += "${coffeeMachine.money} of money\n"
        return str
    }

    override fun nullMoney(): Response {
        val str = Response("I gave you ${coffeeMachine.money}")
        coffeeMachine.money = 0
        return str
    }
}