package com.android.capstone.hairapy.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.data.adapter.ArticleAdapter
import com.android.capstone.hairapy.data.model.Article
import com.android.capstone.hairapy.databinding.ActivityArticleBinding
import com.bumptech.glide.Glide

class ArticleActivity:AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE)

        if (article != null) {
            binding.apply {
                tvTitle.text = article.title
                tvDesc.text = article.content
                Glide.with(this@ArticleActivity)
                    .load(article.imageUrl)
                    .into(imgArticle)
            }
        }
    }

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
}