package com.android.capstone.hairapy.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.model.Article
import com.android.capstone.hairapy.databinding.ItemArticleListBinding
import com.bumptech.glide.Glide

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DIFF_CALLBACK) {

    class ArticleViewHolder(private val binding: ItemArticleListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            with(binding) {
                // Assuming article.imageResId is the drawable resource ID
                Glide.with(itemView.context)
                    .load(article.image)
                    .placeholder(R.drawable.bg_image_loading)
                    .into(imgArticle)

                tvArticleTitle.text = article.title
                tvContentArticle.text = article.content


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Article> =
            object : DiffUtil.ItemCallback<Article>() {

                override fun areItemsTheSame(oldItem: Article, storyItem: Article): Boolean {
                    return oldItem.id == storyItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Article, storyItem: Article): Boolean {
                    return oldItem == storyItem
                }
            }
    }
}
