package com.android.capstone.hairapy.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.data.adapter.ArticleAdapter
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


    }
}