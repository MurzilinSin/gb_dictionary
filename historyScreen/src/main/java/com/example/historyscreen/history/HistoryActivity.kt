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

    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: HistoryViewModel by inject()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
