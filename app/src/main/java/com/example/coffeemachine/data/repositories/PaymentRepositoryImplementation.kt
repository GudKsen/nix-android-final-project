package com.example.coffeemachine.data.repositories

import com.example.coffeemachine.core.entities.Payment
import com.example.coffeemachine.data.database.PaymentDao
import com.example.coffeemachine.data.mappers.DatabasePaymentToPaymentMapper
import com.example.coffeemachine.data.mappers.NetworkPaymentToPaymentMapper
import com.example.coffeemachine.data.mappers.PaymentToDatabasePaymentMapper
import com.example.coffeemachine.data.network.ExchangeServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PaymentRepositoryImplementation (
    private val exchangeServiceAPI: ExchangeServiceAPI,
    private val networkPaymentToPaymentMapper: NetworkPaymentToPaymentMapper,
    private val paymentDao: PaymentDao,
    private val databasePaymentToPaymentMapper: DatabasePaymentToPaymentMapper,
    private val paymentToDatabasePaymentMapper: PaymentToDatabasePaymentMapper
    ): PaymentRepository {

    override suspend fun makeNetworkExchange(payment: Payment): Payment {
        println("tyt?")
        val networkPayment = withContext(Dispatchers.IO) {
            exchangeServiceAPI.exchangeCurrency(
                "${payment.currencyResult}/USD/${payment.amount}"
            )
        }
        return networkPaymentToPaymentMapper.toDomain(networkPayment)
    }

    override fun savePayment(payment: Payment) {
        val databasePayment = paymentToDatabasePaymentMapper.toData(payment)
        paymentDao.add(databasePayment)
    }

    override fun loadPayment(id: Long): Payment? {
        val databasePayment = paymentDao.getPaymentById(id)
        return databasePayment?.let {
            databasePaymentToPaymentMapper.toDomain(it)
        }
    }

}