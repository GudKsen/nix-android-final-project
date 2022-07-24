package com.example.coffeemachine.data.repositories

import com.example.coffeemachine.core.entities.Payment

interface PaymentRepository {
    suspend fun makeNetworkExchange(payment: Payment): Payment

    fun savePayment(payment: Payment)

    fun loadPayment(id: Long): Payment?
}