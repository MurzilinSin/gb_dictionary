package com.example.gb_coroutinekoin.view.main

import com.example.gb_coroutinekoin.interactor.Interactor
import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.repo.Repository
import com.example.gb_coroutinekoin.model.repo.RepositoryLocal

class MainInteractor(
    private val remoteRepo: Repository<List<DataModel>>,
    private val localRepo: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepo.getData(word))
            localRepo.saveToDb(appState)
        } else {
            appState = AppState.Success(localRepo.getData(word))
        }

        return appState
    }
}