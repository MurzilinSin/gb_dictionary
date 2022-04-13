package com.example.gb_coroutinekoin.model.repo

import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.datasource.DataSourceLocal

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDb(appState: AppState) {
        dataSource.saveToDb(appState = appState)
    }
}