package com.example.gb_coroutinekoin.view.base

import com.example.gb_coroutinekoin.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}