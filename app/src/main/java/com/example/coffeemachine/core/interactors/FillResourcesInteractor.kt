package com.example.coffeemachine.core.interactors

import com.example.coffeemachine.core.entities.Resources
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.data.repositories.ActionRepository
import com.example.coffeemachine.data.repositories.FakeActionRepositoryImplementation

class FillResourcesInteractor(private val repository: ActionRepository) {

    fun call(res: Resources): Response {
        repository.fill(res)
        return Response((repository.getResources()))
    }
}