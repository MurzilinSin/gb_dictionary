package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDb(appState: AppState)
}