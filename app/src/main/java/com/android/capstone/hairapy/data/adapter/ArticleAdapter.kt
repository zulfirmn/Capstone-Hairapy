package com.android.capstone.hairapy.data.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.adapter.HistoryAdapter.Companion.DIFF_CALLBACK
import com.android.capstone.hairapy.data.api.response.ArticleItem
import com.android.capstone.hairapy.data.model.Article
import com.android.capstone.hairapy.databinding.ItemArticleListBinding
import com.android.capstone.hairapy.ui.main.ArticleActivity
import com.bumptech.glide.Glide

class ArticleAdapter : ListAdapter<ArticleItem, ArticleAdapter.ArticleViewHolder>(DIFF_CALLBACK) {

    class ArticleViewHolder(val binding: ItemArticleListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleItem) {
            with(binding) {
                // Assuming article.imageResId is the drawable resource ID
                Glide.with(itemView.context)
                    .load(article.imageUrl)
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
        val article = getItem(position)

        holder.bind(article)

        val articleItem = Article(
            article.imageUrl,
            article.title,
            article.content
        )

        holder.itemView.setOnClickListener {
            val moveToDetail = Intent(holder.itemView.context, ArticleActivity::class.java)
            moveToDetail.putExtra(ArticleActivity.EXTRA_ARTICLE, articleItem)

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.binding.imgArticle, "image"),
                    Pair(holder.binding.tvArticleTitle, "title"),
                    Pair(holder.binding.tvContentArticle, "desc"),
                )
            holder.itemView.context.startActivity(moveToDetail, optionsCompat.toBundle())
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ArticleItem> =
            object : DiffUtil.ItemCallback<ArticleItem>() {

                override fun areItemsTheSame(oldItem: ArticleItem, storyItem: ArticleItem): Boolean {
                    return oldItem.id == storyItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: ArticleItem, storyItem: ArticleItem): Boolean {
                    return oldItem == storyItem
                }
            }
    }
}
