package com.example.repo.repo

interface Repository<T> {
    suspend fun getData(word: String): T
}