package com.example.repo.repo

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDb(appState: com.example.model.AppState)
}