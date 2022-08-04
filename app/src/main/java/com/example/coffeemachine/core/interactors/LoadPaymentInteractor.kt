package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.OrderCoffeeData
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.data.repositories.PaymentRepository

class LoadPaymentInteractor(private val repository: PaymentRepository) {
    operator fun invoke(data: OrderCoffeeData): Response {
        val payment = repository.loadPayment(data.price.toLong())
        return Response(message = "Action saved in DB: ${payment?.amount ?: "None"} " +
                (payment?.currency ?: "Unknown")
        )
    }
}