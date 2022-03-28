package com.example.gb_coroutinekoin.view.main

import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.repo.Repository
import com.example.gb_coroutinekoin.presenter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepo: Repository<List<DataModel>>,
    private val localRepo: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepo.getData(word).map { AppState.Success(it) }
        } else {
            localRepo.getData(word).map { AppState.Success(it) }
        }
    }
}