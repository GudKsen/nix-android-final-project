package com.example.coffeemachine

class Controller(private val model: Model) {
    private lateinit var view: MainActivity

    fun attachView(_view: MainActivity) {
        view = _view
    }

    fun buyCoffee(obj:OptionForBuyingCoffee): String {
        val response = model.buy(obj)
        return response.message
    }

    fun fillResources(res: Resources):String {
        return  model.fill(res)
    }

    fun remaining(): String {
        return model.showIngredients()
    }

    fun takeMoney(): String {
        return model.take()
    }
}