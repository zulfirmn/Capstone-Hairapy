package com.android.capstone.hairapy.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.adapter.HistoryAdapter
import com.android.capstone.hairapy.data.model.Article
import com.android.capstone.hairapy.data.model.History
import com.android.capstone.hairapy.databinding.FragmentHistoryBinding

class HistoryFragment: Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvHistory.adapter = historyAdapter

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = historyAdapter
        }

        historyAdapter.submitList(getMockData())

    }

    private fun getMockData(): List<History> {
        return listOf(
            History("1", "Title", R.drawable.shampoo_product ),
            History("2", "Title", R.drawable.shampoo_product ),
            History("3", "Title", R.drawable.shampoo_product  ),
            History("4", "Title", R.drawable.shampoo_product  ),
            //    Article("5", "Title", getString(R.string.lorem_ipsum_short),"" ),
            //   Article("6", "Title", getString(R.string.lorem_ipsum_short),"" ),
            //   Article("7", "Title", getString(R.string.lorem_ipsum_short),"" ),

        )
    }

}