package com.example.gb_coroutinekoin.view.main

import com.example.core.viewModel.Interactor

import com.example.model.AppState
import com.example.model.DataModel
import com.example.repo.repo.Repository
import com.example.repo.repo.RepositoryLocal

class MainInteractor(
    private val remoteRepo: Repository<List<DataModel>>,
    private val localRepo: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): AppState {
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