package com.example.coffeemachine.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PaymentDao {

    @Insert
    fun add(databasePayment: DatabasePayment)

    @Delete
    fun delete(databasePayment: DatabasePayment)

    @Query("SELECT * FROM payments p WHERE p.id = :paymentId")
    fun getPaymentById(paymentId: Long): DatabasePayment?
}