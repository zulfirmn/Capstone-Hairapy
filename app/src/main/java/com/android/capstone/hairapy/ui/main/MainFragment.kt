package com.android.capstone.hairapy.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.data.adapter.ArticleAdapter
import com.android.capstone.hairapy.databinding.FragmentHomeBinding
import com.android.capstone.hairapy.ui.ViewModelFactory

class MainFragment: Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val articleAdapter = ArticleAdapter()

    private val viewModel by activityViewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = articleAdapter
        }

        viewModel.articles()

        viewModel.listArticles.observe(viewLifecycleOwner) {
            articleAdapter.submitList(it)
        }

        binding.tvSeeAll.setOnClickListener {
            val intent = Intent(requireContext(), ArticleActivity::class.java)
            startActivity(intent)
        }
    }
}