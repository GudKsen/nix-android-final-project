package com.example.coffeemachine.data.mappers

import com.example.coffeemachine.core.entities.Payment
import com.example.coffeemachine.data.network.NetworkPayment

class NetworkPaymentToPaymentMapper {
    fun toDomain(networkPayment: NetworkPayment): Payment = with(networkPayment) {
        Payment(
            currency = targetCurrency,
            amount = conversionResult.toFloat(),
            currencyResult = targetCurrency
        )
    }
}