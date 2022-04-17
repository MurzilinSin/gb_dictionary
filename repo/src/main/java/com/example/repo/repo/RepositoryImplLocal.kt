package com.example.repo.repo

class RepositoryImplLocal(private val dataSource: com.example.repo.room.DataSourceLocal<List<com.example.model.DataModel>>) :
    RepositoryLocal<List<com.example.model.DataModel>> {

    override suspend fun getData(word: String): List<com.example.model.DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDb(appState: com.example.model.AppState) {
        dataSource.saveToDb(appState = appState)
    }
}