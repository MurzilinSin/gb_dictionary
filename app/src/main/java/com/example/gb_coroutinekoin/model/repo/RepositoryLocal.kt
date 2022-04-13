package com.example.gb_coroutinekoin.model.repo

import com.example.gb_coroutinekoin.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDb(appState: AppState)
}