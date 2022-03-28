package com.example.gb_coroutinekoin.presenter

import com.example.gb_coroutinekoin.view.base.View
import com.example.gb_coroutinekoin.model.data.AppState

interface Presenter<T: AppState, V: View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean = true)
}