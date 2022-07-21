package com.example.coffeemachine

data class Resources (var water: Int = 0,
                      var milk: Int = 0,
                      var coffeeBeans: Int = 0,
                      var cups: Int = 0,
                      var money: Int = 0)

data class OptionForBuyingCoffee(var option: String = "")

data class Response(var message: String)

enum class CoffeeRecipe(val water: Int, val milk:Int, val coffeeBeans:Int, val price:Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

class Model {
    private var resources = Resources()

    private fun isEnoughIngredients(waterNeed: Int, milkNeed:Int, coffeeBeansNeed: Int): String {
        val message: String
        var counterForCups = 0

        var waterTmp = resources.water
        var milkTmp = resources.milk
        var coffeeBeansTmp = resources.coffeeBeans
        var cupsTmp = resources.cups

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

    fun showIngredients(): String {
        var str = ""
        str += "${resources.water} of water\n"
        str += "${resources.milk} of milk\n"
        str += "${resources.coffeeBeans} of coffee beans\n"
        str += "${resources.cups} of cups\n"
        str += "${resources.money} of money\n"
        return str
    }

    private fun calculateIngredients(coffee: CoffeeRecipe):String {
        val msg: String = isEnoughIngredients(coffee.water, coffee.milk, coffee.coffeeBeans)
        if (msg == "I have enough resources, making you a coffee!") {
            resources.water -= coffee.water
            resources.milk -= coffee.milk
            resources.coffeeBeans -= coffee.coffeeBeans
            resources.money += coffee.price
            resources.cups -= 1
        }
        return msg
    }

    fun buy(obj: OptionForBuyingCoffee): Response {
        var msg = ""

        if (obj.option == "1" || obj.option == "2" || obj.option == "3" || obj.option == "back") {
            when (obj.option) {
                "1" -> {
                    msg = calculateIngredients(CoffeeRecipe.ESPRESSO)
                    if (msg == "I have enough resources, making you a coffee!") msg = "The user bought espresso"
                }

                "2" -> {
                    msg = calculateIngredients(CoffeeRecipe.LATTE)
                    if (msg == "I have enough resources, making you a coffee!") msg = "The user bought a latte"
                }

                "3" -> {
                    msg = calculateIngredients(CoffeeRecipe.CAPPUCCINO)
                    if (msg == "I have enough resources, making you a coffee!") msg = "The user bought a cappuccino"
                }
            }
        } else {
            msg = "Invalid option"
        }
        return Response(msg)
    }

    fun fill(res: Resources):String {
        resources.water += res.water
        resources.milk += res.milk
        resources.coffeeBeans += res.coffeeBeans
        resources.cups += res.cups
        return showIngredients()
    }

    fun take():String {
        val msg = "I gave you ${resources.money}"
        resources.money = 0
        return msg
    }
}