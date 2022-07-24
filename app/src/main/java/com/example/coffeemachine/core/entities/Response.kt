package com.example.coffeemachine.core.entities

data class Response(val message: String)
data class ResponseDataBuying(val message: String, val price:Float, val currency: String)
data class OptionForBuyingCoffee(val option: String = "")