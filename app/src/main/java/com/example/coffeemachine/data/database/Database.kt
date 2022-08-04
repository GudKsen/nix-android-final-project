package com.example.coffeemachine.data.database

import android.content.Context
import androidx.room.Room

object Database {

    private const val DB_NAME = "coffeemachine_database"

    fun provideDao(context: Context): PaymentDao = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    )
        .build()
        .paymentDao()
}