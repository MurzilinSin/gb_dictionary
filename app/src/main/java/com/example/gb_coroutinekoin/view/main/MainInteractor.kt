package com.example.gb_coroutinekoin.view.main

import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.repo.Repository
import com.example.gb_coroutinekoin.interactor.Interactor

class MainInteractor(
    private val remoteRepo: Repository<List<DataModel>>,
    private val localRepo: Repository<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepo.getData(word)
            } else {
                localRepo.getData(word)
            }
        )
    }
}