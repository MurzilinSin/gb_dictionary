package com.example.gb_coroutinekoin.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.gb_coroutinekoin.model.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    abstract override fun renderData(appState: AppState)
    abstract val viewModel: BaseViewModel<T>
}