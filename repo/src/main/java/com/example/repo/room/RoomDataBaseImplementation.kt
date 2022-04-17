package com.example.repo.room

import com.example.model.AppState
import com.example.model.DataModel
import com.example.repo.room.db.HistoryDao

class RoomDataBaseImplementation(private val dao: HistoryDao) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(dao.getAll())
    }

    override suspend fun saveToDb(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            dao.insert(it)
        }
    }
}