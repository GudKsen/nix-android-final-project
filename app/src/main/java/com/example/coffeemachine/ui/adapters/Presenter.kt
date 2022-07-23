package com.example.coffeemachine.ui.adapters

import com.example.coffeemachine.core.entities.OptionForBuyingCoffee
import com.example.coffeemachine.core.entities.Resources
import com.example.coffeemachine.core.interactors.TakeMoneyInteractor
import com.example.coffeemachine.core.interactors.BuyCoffeeInteractor
import com.example.coffeemachine.core.interactors.FillResourcesInteractor
import com.example.coffeemachine.core.interactors.ShowInfoInteractor

class Presenter(private val modelBuy: BuyCoffeeInteractor,
                private val modelTake: TakeMoneyInteractor,
                private val modelFill: FillResourcesInteractor,
                private val modelShowInfo: ShowInfoInteractor): Contract.Presenter {
    private var view: Contract.View? = null

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun buyCoffee(option: OptionForBuyingCoffee) {
        view?.showData(modelBuy.call(option))
    }

    fun fillResources(res: Resources) {
        view?.showData(modelFill.call(res))
    }

    fun takeMoney() {
        val res = modelTake.call()
        view?.showData(res)
    }

    fun remaining() {
        view?.showData(modelShowInfo.call())
    }
}