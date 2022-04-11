package com.example.gb_coroutinekoin.interactor

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean = true): T
}