package com.example.gb_coroutinekoin.viewmodels

import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.util.parseLocalSearchResults
import com.example.gb_coroutinekoin.view.history.HistoryInteractor
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) : BaseViewModel<AppState>() {

    val state: StateFlow<AppState?> get() = _state

    override fun getData(word: String, isOnline: Boolean) {
        _state.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word = word, isOnline = isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _state.tryEmit(
            parseLocalSearchResults(interactor.getData(word = word, fromRemoteSource = isOnline))
        )
    }

    override fun handleError(error: Throwable) {
        _state.tryEmit(
            AppState.Error(error = error)
        )
    }

    override fun onCleared() {
        _state.tryEmit(
            AppState.Success(null)
        )
        super.onCleared()
    }
}