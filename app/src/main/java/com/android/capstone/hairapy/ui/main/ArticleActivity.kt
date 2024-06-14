package com.android.capstone.hairapy.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.adapter.ArticleAdapter
import com.android.capstone.hairapy.data.model.Article
import com.android.capstone.hairapy.databinding.ActivityArticleBinding

class ArticleActivity:AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private val articleAdapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvArticle.adapter = articleAdapter

        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(this@ArticleActivity)
            setHasFixedSize(true)
            adapter = articleAdapter
        }



        articleAdapter.submitList(getMockData())



    }

    private fun getMockData(): List<Article> {
        return listOf(
            Article("1", "Title", getString(R.string.lorem_ipsum_short), R.drawable.card1 ),
            Article("2", "Title", getString(R.string.lorem_ipsum_short), R.drawable.card1),
            Article("3", "Title", getString(R.string.lorem_ipsum_short), R.drawable.card1 ),
            Article("4", "Title", getString(R.string.lorem_ipsum_short), R.drawable.card1 ),
            Article("5", "Title", getString(R.string.lorem_ipsum_short),R.drawable.card1 ),
            Article("6", "Title", getString(R.string.lorem_ipsum_short),R.drawable.card1 ),
            Article("7", "Title", getString(R.string.lorem_ipsum_short),R.drawable.card1 ),

        )
    }


}