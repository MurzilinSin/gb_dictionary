package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }
}