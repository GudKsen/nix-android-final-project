package com.example.coffeemachine.data.mappers

import com.example.coffeemachine.core.entities.Payment
import com.example.coffeemachine.data.database.DatabasePayment

class PaymentToDatabasePaymentMapper {

    fun toData(payment: Payment): DatabasePayment = with(payment) {
        DatabasePayment(
            currency = currency,
            amount = amount
        )
    }
}