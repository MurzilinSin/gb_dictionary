package com.example.gb_coroutinekoin.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}