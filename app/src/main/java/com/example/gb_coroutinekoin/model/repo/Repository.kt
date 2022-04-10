package com.example.gb_coroutinekoin.model.repo

interface Repository<T> {
    suspend fun getData(word: String): T
}