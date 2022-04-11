package com.example.gb_coroutinekoin.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_coroutinekoin.R
import com.example.gb_coroutinekoin.databinding.ActivityMainBinding
import com.example.gb_coroutinekoin.model.data.AppState
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.data.Meanings
import com.example.gb_coroutinekoin.view.base.BaseActivity
import com.example.gb_coroutinekoin.view.description.DescriptionActivity
import com.example.gb_coroutinekoin.view.main.adapter.MainAdapter
import com.example.gb_coroutinekoin.view.search.SearchDialogFragment
import com.example.gb_coroutinekoin.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<AppState>() {

    private lateinit var vb: ActivityMainBinding
    private var adapter: MainAdapter? = null
    private val viewModel: MainViewModel by inject()

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
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
                        viewModel.getData(wordToSearch = searchWord)
                    }
                }
            )
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.state.collect { appState ->
                appState?.let {
                    renderData(it)
                }
            }
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

    fun convertMeaningsToString(meanings: List<Meanings>): String {
        var meaningsSeparatedByComma = String()
        for ((index, meaning) in meanings.withIndex()) {
            meaningsSeparatedByComma += if (index + 1 != meanings.size) {
                String.format("%s%s", meaning.translation?.text, ", ")
            } else {
                meaning.translation?.text
            }
        }
        return meaningsSeparatedByComma
    }


    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

}