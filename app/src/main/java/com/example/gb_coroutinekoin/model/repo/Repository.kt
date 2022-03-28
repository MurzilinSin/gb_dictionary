package com.example.gb_coroutinekoin.model.repo

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}