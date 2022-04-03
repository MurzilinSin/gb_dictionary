package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.DataModel

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()
) :  DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}