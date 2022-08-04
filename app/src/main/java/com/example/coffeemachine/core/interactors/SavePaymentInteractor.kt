package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.Payment
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.data.repositories.PaymentRepository

class SavePaymentInteractor(private val repository: PaymentRepository) {
    operator fun invoke(obj: Payment): Response {
        val pay = repository.savePayment(obj)
        return Response("saved")
    }
}