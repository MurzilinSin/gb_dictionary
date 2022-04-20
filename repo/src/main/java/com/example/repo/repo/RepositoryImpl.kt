package com.example.repo.repo

class RepositoryImpl(private val dataSource: com.example.repo.room.DataSource<List<com.example.model.DataModel>>) :
    Repository<List<com.example.model.DataModel>> {

    override suspend fun getData(word: String): List<com.example.model.DataModel> {
        return dataSource.getData(word = word)
    }
}