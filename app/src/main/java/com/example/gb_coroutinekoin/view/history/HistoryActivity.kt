package com.example.gb_coroutinekoin.view.history

import android.os.Bundle
import com.example.gb_coroutinekoin.databinding.ActivityHistoryBinding
import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.view.base.BaseActivity
import com.example.gb_coroutinekoin.view.history.adapter.HistoryAdapter
import com.example.gb_coroutinekoin.viewmodels.HistoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
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
            viewModel.state.collect { appState ->
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
