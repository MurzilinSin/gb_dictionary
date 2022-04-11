package com.example.gb_coroutinekoin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.view.main.MainInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainInteractor: MainInteractor) : ViewModel() {
    private val _state: MutableStateFlow<AppState?> = MutableStateFlow(null)
    val state: StateFlow<AppState?> get() = _state

    fun getData(wordToSearch: String) {
        _state.value = AppState.Loading(null)
        viewModelScope.launch {
            _state.tryEmit(mainInteractor.getData(wordToSearch, true))
        }
    }

}