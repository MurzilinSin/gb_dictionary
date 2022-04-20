package com.example.gb_coroutinekoin.view.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gb_coroutinekoin.R
import com.example.gb_coroutinekoin.databinding.DialogSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment: BottomSheetDialogFragment() {
    private var onSearchClickListener: OnSearchClickListener? = null
    private lateinit var searchEditText: TextInputEditText viewById<TextInputEditText>(R.id.search_edit_text)
    private lateinit var clearTextImageView: ImageView viewById<ImageView>(R.id.clear_text_imageview)
    private lateinit var searchButtonTextView: TextView viewById<TextView>(R.id.search_button_textview)
    
    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (searchEditText.text != null && !searchEditText.text.toString().isEmpty()) {
                searchButtonTextView.isEnabled = true
                clearTextImageView.visibility = View.VISIBLE
            } else {
                searchButtonTextView.isEnabled = false
                clearTextImageView.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener = View.OnClickListener {
        onSearchClickListener?.onClick(searchEditText.text.toString())
        dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButtonTextView.setOnClickListener(onSearchButtonClickListener)
        searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }
    private fun addOnClearClickListener() {
        clearTextImageView.setOnClickListener {
            searchEditText.setText("")
            searchButtonTextView.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }

}