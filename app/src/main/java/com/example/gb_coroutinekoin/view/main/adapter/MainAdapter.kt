package com.example.gb_coroutinekoin.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_coroutinekoin.R
import com.example.gb_coroutinekoin.model.data.DataModel

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>,
    private val context: Context // TODO: inject With Koin
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    private fun openInNewWindow(listItem: DataModel) {
        onListItemClickListener.onItemClick(listItem)
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text
                itemView.findViewById<TextView>(R.id.transcription_textview_recycler_item).text =
                    context.getString(R.string.translation, data.meanings?.get(0)?.transcription)


                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }

    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}