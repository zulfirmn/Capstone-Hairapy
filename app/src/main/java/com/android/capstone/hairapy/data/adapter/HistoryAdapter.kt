package com.android.capstone.hairapy.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.model.History
import com.android.capstone.hairapy.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class HistoryAdapter : ListAdapter<History, HistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK){

    class HistoryViewHolder(private val binding: ItemHistoryBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(article: History) {
            with(binding) {
                // Assuming article.imageResId is the drawable resource ID
                Glide.with(itemView.context)
                    .load(article.image)
                    .placeholder(R.drawable.bg_image_loading)
                    .into(imgHistory)

                tvHistoryTitle.text = article.title



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
        val DIFF_CALLBACK: DiffUtil.ItemCallback<History> =
            object : DiffUtil.ItemCallback<History>() {

                override fun areItemsTheSame(oldItem: History, storyItem: History): Boolean {
                    return oldItem.id == storyItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: History, storyItem: History): Boolean {
                    return oldItem == storyItem
                }
            }
    }

}