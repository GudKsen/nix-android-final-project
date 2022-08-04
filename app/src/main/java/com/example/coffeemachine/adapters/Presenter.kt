package com.example.coffeemachine.adapters

import com.example.coffeemachine.core.entities.OptionForBuyingCoffee
import com.example.coffeemachine.core.entities.Resources
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.core.interactors.Model

class Presenter(private val model: Model): Contract.Presenter {
    private var view: Contract.View? = null

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun buyCoffee(option: OptionForBuyingCoffee) {
        view?.showData(model.buy(option))
    }

    fun fillResources(res: Resources) {
        view?.showData(model.fill(res))
    }

    fun takeMoney() {
        val res = model.take()
        view?.showData(res)
    }

    fun remaining() {
        view?.showData(model.showIngredients())
    }
}