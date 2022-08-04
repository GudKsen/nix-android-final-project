package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.Payment
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.data.repositories.PaymentRepository

class ExchangeCurrencyInteractor(
    private val paymentRepository: PaymentRepository
) {

    suspend operator fun invoke(payment: Payment): Response {
        val exchangePayment = if (payment.currency != payment.currencyResult) {
            println("in function invoke")
            paymentRepository.makeNetworkExchange(payment)
        } else {
            payment
        }

        return with(exchangePayment) {
            Response(
                message = "$amount $currency"
            )
        }
    }

}