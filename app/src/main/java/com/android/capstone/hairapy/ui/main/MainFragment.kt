package com.android.capstone.hairapy.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.adapter.ArticleAdapter
import com.android.capstone.hairapy.data.model.Article
import com.android.capstone.hairapy.databinding.FragmentHomeBinding

class MainFragment: Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val articleAdapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvArticle.adapter = articleAdapter

        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = articleAdapter
        }

        articleAdapter.submitList(getMockData())

        binding.tvSeeAll.setOnClickListener {
            val intent = Intent(requireContext(), ArticleActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getMockData(): List<Article> {
        return listOf(
            Article("1", "Title", getString(R.string.lorem_ipsum_short), R.drawable.card1 ),
            Article("2", "Title", getString(R.string.lorem_ipsum_short),R.drawable.card1),
            Article("3", "Title", getString(R.string.lorem_ipsum_short),R.drawable.card1 ),
            Article("4", "Title", getString(R.string.lorem_ipsum_short),R.drawable.card1 ),
        //    Article("5", "Title", getString(R.string.lorem_ipsum_short),"" ),
         //   Article("6", "Title", getString(R.string.lorem_ipsum_short),"" ),
         //   Article("7", "Title", getString(R.string.lorem_ipsum_short),"" ),

            )
    }


}