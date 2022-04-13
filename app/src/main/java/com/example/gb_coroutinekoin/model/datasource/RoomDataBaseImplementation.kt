package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.datasource.db.HistoryDao
import com.example.gb_coroutinekoin.util.convertDataModelSuccessToEntity
import com.example.gb_coroutinekoin.util.mapHistoryEntityToSearchResult

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