package com.example.repo.room

interface DataSource<T> {
    suspend fun getData(word: String): T
}