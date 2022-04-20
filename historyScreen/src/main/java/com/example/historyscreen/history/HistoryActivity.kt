package com.example.historyscreen.history

import android.os.Bundle
import com.example.core.BaseActivity
import com.example.historyscreen.databinding.ActivityHistoryBinding
import com.example.historyscreen.history.adapter.HistoryAdapter
import com.example.model.AppState
import com.example.model.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private val viewModel: HistoryViewModel by inject()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }
    private val recycler viewById<RecyclerView>(R.id.history_activity_recyclerview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        CoroutineScope(Dispatchers.Main).launch {
            viewModel.state.collectLatest { appState ->
                appState?.let { renderData(appState = it) }
            }
        }
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData(word = "", isOnline = false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }


    private fun initViews() {
        recycler.adapter = adapter
    }
}
