package com.example.repo.room

import com.example.model.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDb(appState: AppState)
}