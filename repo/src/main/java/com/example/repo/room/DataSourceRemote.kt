package com.example.repo.room

import com.example.model.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<com.example.model.DataModel> = remoteProvider.getData(word)
}