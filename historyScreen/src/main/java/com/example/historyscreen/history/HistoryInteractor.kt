package com.example.historyscreen.history

import com.example.model.AppState
import com.example.model.DataModel
import com.example.repo.repo.Repository
import com.example.repo.repo.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : com.example.core.viewModel.Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}