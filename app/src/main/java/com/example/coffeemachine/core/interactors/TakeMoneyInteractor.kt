package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.data.repositories.ActionRepository
import com.example.coffeemachine.data.repositories.FakeActionRepositoryImplementation

class TakeMoneyInteractor(private var repository: ActionRepository) {
    fun call(): Response {
        return repository.nullMoney()
    }
}