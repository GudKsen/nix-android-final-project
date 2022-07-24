package com.example.coffeemachine.data.mappers

import com.example.coffeemachine.core.entities.Payment
import com.example.coffeemachine.data.database.DatabasePayment

class DatabasePaymentToPaymentMapper {
    fun toDomain(databasePayment: DatabasePayment): Payment = with(databasePayment) {
        Payment(
            currency = currency,
            amount = amount,
            currencyResult = "0f"
        )
    }
}