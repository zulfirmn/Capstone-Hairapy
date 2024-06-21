package com.android.capstone.hairapy.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.db.HistoryPrediction
import com.android.capstone.hairapy.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class HistoryAdapter : ListAdapter<HistoryPrediction, HistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK){

    class HistoryViewHolder(private val binding: ItemHistoryBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoryPrediction) {
            with(binding) {
                // Assuming article.imageResId is the drawable resource ID
                Glide.with(itemView.context)
                    .load(history.uri)
                    .placeholder(R.drawable.bg_image_loading)
                    .into(imgHistory)

                tvHistoryResult.text = history.result
                tvHistoryDate.text = history.date
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HistoryPrediction> =
            object : DiffUtil.ItemCallback<HistoryPrediction>() {

                override fun areItemsTheSame(oldItem: HistoryPrediction, newItem: HistoryPrediction): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: HistoryPrediction, newItem: HistoryPrediction): Boolean {
                    return oldItem == newItem
                }
            }
    }

}