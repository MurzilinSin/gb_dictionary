package com.example.gb_coroutinekoin.model.repo

import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.datasource.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word = word)
    }
}