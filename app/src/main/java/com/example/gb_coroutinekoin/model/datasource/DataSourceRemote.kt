package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}