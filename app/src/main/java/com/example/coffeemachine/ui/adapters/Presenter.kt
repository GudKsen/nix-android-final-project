package com.example.coffeemachine.ui.adapters

import com.example.coffeemachine.core.entities.*
import com.example.coffeemachine.core.interactors.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Presenter(
    private val modelBuy: BuyCoffeeInteractor,
    private val modelTake: TakeMoneyInteractor,
    private val modelFill: FillResourcesInteractor,
    private val modelShowInfo: ShowInfoInteractor,
    private val modelExchangeCurrency: ExchangeCurrencyInteractor,
    private val modelGetPricesCoffee: GetPricesCoffee,
    private val modelLoadPaymentInteractor: LoadPaymentInteractor,
    private val modelSavePaymentInteractor: SavePaymentInteractor

) : Contract.Presenter, CoroutineScope {
    private var view: Contract.View? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun buyCoffee(option: OptionForBuyingCoffee) {
        val res = modelBuy.call(option)
        if (res != null) {
            savePayment(Payment(res.price, res.currency, res.currency))
            loadPayment(OrderCoffeeData(res.price, res.currency))
            view?.showData(Response(res.message))
        }
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

     fun getPrice(pay: OptionForBuyingCoffee, curr: String) {
        launch {
            val price = modelGetPricesCoffee.getPrice(pay, curr)
            println("$price price")
            val k = modelExchangeCurrency(price)
            println("$k change")
            withContext(Dispatchers.Main){
                when (pay.option) {
                    "1" -> {
                        view?.showPriceEspresso(k)
                    }
                    "2" -> {
                        view?.showPriceLatte(k)
                    }
                    "3" -> {
                        view?.showPriceCappuccino(k)
                    }
                }
            }
        }
    }

    fun loadPayment(data: OrderCoffeeData) {
        launch {
            val response = modelLoadPaymentInteractor(data)
            withContext(Dispatchers.Main) {
                view?.showData(response)
            }
        }
    }

    fun savePayment(data: Payment) {
        launch {
            val res = modelSavePaymentInteractor(data)
            withContext(Dispatchers.Main) {
                view?.showData(res)
            }
        }
    }

}