package com.example.gb_coroutinekoin.viewmodels

import com.example.core.viewModel.BaseViewModel
import com.example.gb_coroutinekoin.util.parseOnlineSearchResults
import com.example.gb_coroutinekoin.view.main.MainInteractor
import com.example.model.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainInteractor: MainInteractor) :
    BaseViewModel<AppState>() {
    val state: StateFlow<AppState?> get() = _state

    fun getData(wordToSearch: String) {
        _state.value = AppState.Loading(null)
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            _state.tryEmit(parseOnlineSearchResults(mainInteractor.getData(wordToSearch, true)))
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        _state.value = AppState.Loading(null)
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            _state.tryEmit(mainInteractor.getData(word, true))
        }
    }

    override fun handleError(error: Throwable) {
        _state.tryEmit(AppState.Error(error))
    }

    override fun onCleared() {
        _state.value = com.example.model.AppState.Success(null)
        super.onCleared()
    }

}