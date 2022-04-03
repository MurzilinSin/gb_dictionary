package com.example.gb_coroutinekoin.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_coroutinekoin.R
import com.example.gb_coroutinekoin.databinding.ActivityMainBinding
import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.view.base.BaseActivity
import com.example.gb_coroutinekoin.view.main.adapter.MainAdapter
import com.example.gb_coroutinekoin.view.search.SearchDialogFragment
import com.example.gb_coroutinekoin.viewmodels.MainActivityViewModel
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity<AppState>() {

    private lateinit var vb: ActivityMainBinding
    private var adapter: MainAdapter? = null


    override val viewModel: MainActivityViewModel by inject()

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(
                    this@MainActivity, data.meanings?.get(0)?.translation?.text ?: "",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(
                object : SearchDialogFragment.OnSearchClickListener {
                    override fun onClick(searchWord: String) {
                        viewModel.getData(word = searchWord)
                    }
                }
            )
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
        viewModel.subscribe().observe(this@MainActivity) {
            renderData(it)
        }

    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        vb.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        vb.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel, this)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    vb.progressBarHorizontal.visibility = VISIBLE
                    vb.progressBarRound.visibility = GONE
                    vb.progressBarHorizontal.progress = appState.progress
                } else {
                    vb.progressBarHorizontal.visibility = GONE
                    vb.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }

    }

    private fun showErrorScreen(message: String?) {
        showViewError()
        vb.errorTextview.text = message ?: getString(R.string.undefined_error)
        vb.reloadButton.setOnClickListener { viewModel.getData("Error") }
    }

    private fun showViewSuccess() {
        vb.successLinearLayout.visibility = VISIBLE
        vb.loadingFrameLayout.visibility = GONE
        vb.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        vb.successLinearLayout.visibility = GONE
        vb.loadingFrameLayout.visibility = VISIBLE
        vb.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        vb.successLinearLayout.visibility = GONE
        vb.loadingFrameLayout.visibility = GONE
        vb.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}